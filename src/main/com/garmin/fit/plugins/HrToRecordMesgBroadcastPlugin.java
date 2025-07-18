/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright 2025 Garmin International, Inc.
// Licensed under the Flexible and Interoperable Data Transfer (FIT) Protocol License; you
// may not use this file except in compliance with the Flexible and Interoperable Data
// Transfer (FIT) Protocol License.
/////////////////////////////////////////////////////////////////////////////////////////////
// ****WARNING****  This file is auto-generated!  Do NOT edit this file.
// Profile Version = 21.176.0Release
// Tag = production/release/21.176.0-0-ga30c8ed
/////////////////////////////////////////////////////////////////////////////////////////////


package com.garmin.fit.plugins;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import com.garmin.fit.DateTime;
import com.garmin.fit.File;
import com.garmin.fit.FileIdMesg;
import com.garmin.fit.FitRuntimeException;
import com.garmin.fit.HrMesg;
import com.garmin.fit.Mesg;
import com.garmin.fit.MesgBroadcastPlugin;
import com.garmin.fit.MesgNum;
import com.garmin.fit.RecordMesg;

/**
 * Provides functionality to backfill record messages with hr data from HR
 * mesgs.
 * <p>
 * The plugin matches the timestamp of record messages with the timestamps hr
 * data contained in the HR mesg.
 * <p>
 * Requirements for correct operation: - HR data must be in the order of
 * increasing timestamp - Record data must be in the order of increasing
 * timestamp - The order of incoming HR and record mesgs may be independent of
 * each other - The first HR mesg must contain a timestamp (with optional
 * fractional_timestamp) which, in combination with the event_timestamps that
 * mark the bpm data, provide the time that is used to match record data. -
 * There must be an equal number of filtered_bpm fields and event_timestamp
 * fields in each HR mesg; this number may change from message to message.
 *
 */
public class HrToRecordMesgBroadcastPlugin implements MesgBroadcastPlugin {
    private final HeartRateList heartrates = new HeartRateList();
    private boolean isActivityFile = false;

    public HrToRecordMesgBroadcastPlugin() {
    }

    /**
     * Peeks at messages as they are being added to the buffer
     *
     * @param mesg the message that has just been buffered by
     *             BufferedMesgBroadcaster
     */
    public void onIncomingMesg(final Mesg mesg) {
        switch (mesg.getNum()) {
            case MesgNum.FILE_ID:
                // Check to see if we are processing an activity file.
                final FileIdMesg fileIdMesg = new FileIdMesg(mesg);
                if (fileIdMesg.getType() == File.ACTIVITY) {
                    isActivityFile = true;
                }
                break;
            case MesgNum.HR:
                heartrates.addHrMesssage(new HrMesg(mesg));
                break;
            default:
                break;
        }
    }

    /**
     * Matches record time ranges with all time matching HR mesgs and updates the
     * message stream for later broadcast to listeners.
     *
     * @param mesgs the message list that is about to be broadcast to all
     *              MesgListeners. \ Note: The List is 'final' but the references
     *              within the list are not, \ therefore editing Mesg objects within
     *              mesgs will alter the messages \ that are broadcast to listeners.
     *
     *              DO NOT add or remove any messages to mesgs
     */
    public void onBroadcast(final List<Mesg> mesgs) {
        // Check if we have an activity file and have received HR messages
        if (isActivityFile && heartrates.size() > 0) {
            int heartrateIndex = 0;
            DateTime recordRangeStartTime = null;

            for (int mesgCounter = 0; mesgCounter < mesgs.size(); ++mesgCounter) {
                Mesg mesg = mesgs.get(mesgCounter);

                // Process record messages as they are encountered
                if (mesg.getNum() == MesgNum.RECORD) {
                    long hrSum = 0;
                    long hrSumCount = 0;

                    RecordMesg recordMesg = new RecordMesg(mesg);
                    DateTime recordRangeEndTime = new DateTime(recordMesg.getTimestamp());

                    if (recordRangeStartTime == null) {
                        recordRangeStartTime = new DateTime(recordMesg.getTimestamp().getTimestamp());
                    }

                    if (recordRangeStartTime.compareTo(recordRangeEndTime) == 0) {
                        recordRangeStartTime.add(-1);
                        heartrateIndex = (heartrateIndex >= 1) ? heartrateIndex - 1 : 0;
                    }

                    boolean findingInRangeHrMesgs = true;
                    while (findingInRangeHrMesgs && (heartrateIndex < heartrates.size())) {

                        HeartRate heartrate = heartrates.get(heartrateIndex);

                        // Check if the heartrate timestamp is gt record start time
                        // and if the heartrate timestamp is lte to record end time
                        if ((heartrate.timestamp.compareTo(recordRangeStartTime) > 0)
                                && (heartrate.timestamp.compareTo(recordRangeEndTime) <= 0)) {
                            hrSum += heartrate.value;
                            hrSumCount++;
                        }
                        // Check if the heartrate timestamp exceeds the record time
                        else if (heartrate.timestamp.compareTo(recordRangeEndTime) > 0) {
                            findingInRangeHrMesgs = false;

                            if (hrSumCount > 0) {
                                // Update record's heart rate value
                                short avgHR = (short) Math.round((((float) hrSum) / hrSumCount));
                                recordMesg.setHeartRate(avgHR);
                                mesgs.set(mesgCounter, (Mesg) recordMesg);
                            }
                            // Reset HR average accumulators
                            hrSum = 0;
                            hrSumCount = 0;

                            recordRangeStartTime = new DateTime(recordRangeEndTime);

                            // Breaks out of findingInRangeHrMesgs while loop w/o incrementing
                            // heartrateIndex
                            break;
                        }

                        heartrateIndex++;
                    }
                }
            }
        }
    }

    private static class HeartRate {
        public DateTime timestamp;
        public short value;

        HeartRate(HeartRate other) {
            timestamp = new DateTime(other.timestamp);
            value = other.value;
        }

        HeartRate(DateTime dateTime, short value) {
            this.timestamp = new DateTime(dateTime);
            this.value = value;
        }
    }

    @SuppressWarnings("serial")
    private class HeartRateList extends ArrayList<HeartRate> {
        final private long GAP_INCREMENT_MILLISECONDS = 250;
        final private float GAP_INCREMENT_SECONDS = GAP_INCREMENT_MILLISECONDS / 1000.0f;
        final private long GAP_MAX_MILLISECONDS = 5000;
        final private long GAP_MAX_STEPS = GAP_MAX_MILLISECONDS / GAP_INCREMENT_MILLISECONDS;

        private Float anchorEventTimestamp = 0.0f;
        private DateTime anchorTimestamp = null;

        HeartRateList() {
        }

        public void addHrMesssage(HrMesg hrMesg) {
            if (hrMesg == null) {
                throw new FitRuntimeException("FIT HrToRecordMesgBroadcastPlugin Error: HR mesg must not be null");
            }
            // Update HR timestamp anchor, if present
            if (hrMesg.getTimestamp() != null) {
                anchorTimestamp = new DateTime(hrMesg.getTimestamp());

                if (hrMesg.getFractionalTimestamp() != null)
                    anchorTimestamp.add(hrMesg.getFractionalTimestamp());

                if (hrMesg.getNumEventTimestamp() == 1) {
                    anchorEventTimestamp = hrMesg.getEventTimestamp(0);
                } else {
                    throw new FitRuntimeException(
                            "FIT HrToRecordMesgBroadcastPlugin Error: Anchor HR mesg must have 1 event_timestamp");
                }
            }

            if (anchorTimestamp == null) {
                // We cannot process any HR messages if we have not received a timestamp anchor
                throw new FitRuntimeException(
                        "FIT HrToRecordMesgBroadcastPlugin Error: No anchor timestamp received in a HR mesg before diff HR mesgs");
            } else if (hrMesg.getNumEventTimestamp() != hrMesg.getNumFilteredBpm()) {
                throw new FitRuntimeException(
                        "FIT HrToRecordMesgBroadcastPlugin Error: HR mesg with mismatching event timestamp and filtered bpm");
            }

            for (int i = 0; i < hrMesg.getNumEventTimestamp(); i++) {
                Float eventTimestamp = hrMesg.getEventTimestamp(i);

                // Check to see if the event timestamp rolled over
                if (eventTimestamp < anchorEventTimestamp) {
                    if ((anchorEventTimestamp - eventTimestamp) > (1 << 21)) {
                        eventTimestamp += (1 << 22);
                    } else {
                        throw new FitRuntimeException(
                                "FIT HrToRecordMesgBroadcastPlugin Error: Anchor event_timestamp is greater than subsequent event_timestamp. This does not allow for correct delta calculation.");
                    }
                }

                HeartRate currentHr = new HeartRate(anchorTimestamp, hrMesg.getFilteredBpm(i));
                currentHr.timestamp.add(eventTimestamp - anchorEventTimestamp);

                // Carry the previous HR value forward across the gap to the current
                // HR value for up to 5 Seconds (5000ms) in 250ms increments
                if (!isEmpty()) {
                    HeartRate previousHR = get(size() - 1);
                    long gapInMilliseconds = Math
                            .abs(currentHr.timestamp.getDate().getTime() - previousHR.timestamp.getDate().getTime());
                    long step = 1;
                    while (gapInMilliseconds > GAP_INCREMENT_MILLISECONDS && step <= GAP_MAX_STEPS) {
                        HeartRate gapHR = new HeartRate(previousHR);
                        gapHR.timestamp.add(GAP_INCREMENT_SECONDS * step);
                        add(gapHR);

                        gapInMilliseconds -= GAP_INCREMENT_MILLISECONDS;
                        step++;
                    }
                }

                add(currentHr);
            }
        }
    }
}

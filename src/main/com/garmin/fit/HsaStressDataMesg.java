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


package com.garmin.fit;



public class HsaStressDataMesg extends Mesg   {

    
    public static final int TimestampFieldNum = 253;
    
    public static final int ProcessingIntervalFieldNum = 0;
    
    public static final int StressLevelFieldNum = 1;
    

    protected static final  Mesg hsaStressDataMesg;
    static {
        // hsa_stress_data
        hsaStressDataMesg = new Mesg("hsa_stress_data", MesgNum.HSA_STRESS_DATA);
        hsaStressDataMesg.addField(new Field("timestamp", TimestampFieldNum, 134, 1, 0, "", false, Profile.Type.DATE_TIME));
        
        hsaStressDataMesg.addField(new Field("processing_interval", ProcessingIntervalFieldNum, 132, 1, 0, "s", false, Profile.Type.UINT16));
        
        hsaStressDataMesg.addField(new Field("stress_level", StressLevelFieldNum, 1, 1, 0, "s", false, Profile.Type.SINT8));
        
    }

    public HsaStressDataMesg() {
        super(Factory.createMesg(MesgNum.HSA_STRESS_DATA));
    }

    public HsaStressDataMesg(final Mesg mesg) {
        super(mesg);
    }


    /**
     * Get timestamp field
     *
     * @return timestamp
     */
    public DateTime getTimestamp() {
        return timestampToDateTime(getFieldLongValue(253, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD));
    }

    /**
     * Set timestamp field
     *
     * @param timestamp The new timestamp value to be set
     */
    public void setTimestamp(DateTime timestamp) {
        setFieldValue(253, 0, timestamp.getTimestamp(), Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get processing_interval field
     * Units: s
     * Comment: Processing interval length in seconds
     *
     * @return processing_interval
     */
    public Integer getProcessingInterval() {
        return getFieldIntegerValue(0, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set processing_interval field
     * Units: s
     * Comment: Processing interval length in seconds
     *
     * @param processingInterval The new processingInterval value to be set
     */
    public void setProcessingInterval(Integer processingInterval) {
        setFieldValue(0, 0, processingInterval, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    public Byte[] getStressLevel() {
        
        return getFieldByteValues(1, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        
    }

    /**
     * @return number of stress_level
     */
    public int getNumStressLevel() {
        return getNumFieldValues(1, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get stress_level field
     * Units: s
     * Comment: Stress Level: [0,100] Off wrist: -1 Excess motion: -2 Not enough data: -3 Recovering from exercise: -4 Unidentified: -5 Blank: -16
     *
     * @param index of stress_level
     * @return stress_level
     */
    public Byte getStressLevel(int index) {
        return getFieldByteValue(1, index, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set stress_level field
     * Units: s
     * Comment: Stress Level: [0,100] Off wrist: -1 Excess motion: -2 Not enough data: -3 Recovering from exercise: -4 Unidentified: -5 Blank: -16
     *
     * @param index of stress_level
     * @param stressLevel The new stressLevel value to be set
     */
    public void setStressLevel(int index, Byte stressLevel) {
        setFieldValue(1, index, stressLevel, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

}

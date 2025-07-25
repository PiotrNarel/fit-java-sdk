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


public enum File  {
    DEVICE((short)1),
    SETTINGS((short)2),
    SPORT((short)3),
    ACTIVITY((short)4),
    WORKOUT((short)5),
    COURSE((short)6),
    SCHEDULES((short)7),
    WEIGHT((short)9),
    TOTALS((short)10),
    GOALS((short)11),
    BLOOD_PRESSURE((short)14),
    MONITORING_A((short)15),
    ACTIVITY_SUMMARY((short)20),
    MONITORING_DAILY((short)28),
    MONITORING_B((short)32),
    SEGMENT((short)34),
    SEGMENT_LIST((short)35),
    EXD_CONFIGURATION((short)40),
    MFG_RANGE_MIN((short)0xF7),
    MFG_RANGE_MAX((short)0xFE),
    INVALID((short)255);

    protected short value;

    private File(short value) {
        this.value = value;
    }

    public static File getByValue(final Short value) {
        for (final File type : File.values()) {
            if (value == type.value)
                return type;
        }

        return File.INVALID;
    }

    /**
     * Retrieves the String Representation of the Value
     * @param value The enum constant
     * @return The name of this enum constant
     */
    public static String getStringFromValue( File value ) {
        return value.name();
    }

    public short getValue() {
        return value;
    }


}

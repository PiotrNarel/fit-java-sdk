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


public enum BpStatus  {
    NO_ERROR((short)0),
    ERROR_INCOMPLETE_DATA((short)1),
    ERROR_NO_MEASUREMENT((short)2),
    ERROR_DATA_OUT_OF_RANGE((short)3),
    ERROR_IRREGULAR_HEART_RATE((short)4),
    INVALID((short)255);

    protected short value;

    private BpStatus(short value) {
        this.value = value;
    }

    public static BpStatus getByValue(final Short value) {
        for (final BpStatus type : BpStatus.values()) {
            if (value == type.value)
                return type;
        }

        return BpStatus.INVALID;
    }

    /**
     * Retrieves the String Representation of the Value
     * @param value The enum constant
     * @return The name of this enum constant
     */
    public static String getStringFromValue( BpStatus value ) {
        return value.name();
    }

    public short getValue() {
        return value;
    }


}

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


public enum TimerTrigger  {
    MANUAL((short)0),
    AUTO((short)1),
    FITNESS_EQUIPMENT((short)2),
    INVALID((short)255);

    protected short value;

    private TimerTrigger(short value) {
        this.value = value;
    }

    public static TimerTrigger getByValue(final Short value) {
        for (final TimerTrigger type : TimerTrigger.values()) {
            if (value == type.value)
                return type;
        }

        return TimerTrigger.INVALID;
    }

    /**
     * Retrieves the String Representation of the Value
     * @param value The enum constant
     * @return The name of this enum constant
     */
    public static String getStringFromValue( TimerTrigger value ) {
        return value.name();
    }

    public short getValue() {
        return value;
    }


}

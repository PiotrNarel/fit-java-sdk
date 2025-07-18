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


public enum StrokeType  {
    NO_EVENT((short)0),
    OTHER((short)1),
    SERVE((short)2),
    FOREHAND((short)3),
    BACKHAND((short)4),
    SMASH((short)5),
    INVALID((short)255);

    protected short value;

    private StrokeType(short value) {
        this.value = value;
    }

    public static StrokeType getByValue(final Short value) {
        for (final StrokeType type : StrokeType.values()) {
            if (value == type.value)
                return type;
        }

        return StrokeType.INVALID;
    }

    /**
     * Retrieves the String Representation of the Value
     * @param value The enum constant
     * @return The name of this enum constant
     */
    public static String getStringFromValue( StrokeType value ) {
        return value.name();
    }

    public short getValue() {
        return value;
    }


}

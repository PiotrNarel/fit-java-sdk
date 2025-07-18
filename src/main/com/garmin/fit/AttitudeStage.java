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


public enum AttitudeStage  {
    FAILED((short)0),
    ALIGNING((short)1),
    DEGRADED((short)2),
    VALID((short)3),
    INVALID((short)255);

    protected short value;

    private AttitudeStage(short value) {
        this.value = value;
    }

    public static AttitudeStage getByValue(final Short value) {
        for (final AttitudeStage type : AttitudeStage.values()) {
            if (value == type.value)
                return type;
        }

        return AttitudeStage.INVALID;
    }

    /**
     * Retrieves the String Representation of the Value
     * @param value The enum constant
     * @return The name of this enum constant
     */
    public static String getStringFromValue( AttitudeStage value ) {
        return value.name();
    }

    public short getValue() {
        return value;
    }


}

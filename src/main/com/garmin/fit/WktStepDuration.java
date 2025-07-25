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


public enum WktStepDuration  {
    TIME((short)0),
    DISTANCE((short)1),
    HR_LESS_THAN((short)2),
    HR_GREATER_THAN((short)3),
    CALORIES((short)4),
    OPEN((short)5),
    REPEAT_UNTIL_STEPS_CMPLT((short)6),
    REPEAT_UNTIL_TIME((short)7),
    REPEAT_UNTIL_DISTANCE((short)8),
    REPEAT_UNTIL_CALORIES((short)9),
    REPEAT_UNTIL_HR_LESS_THAN((short)10),
    REPEAT_UNTIL_HR_GREATER_THAN((short)11),
    REPEAT_UNTIL_POWER_LESS_THAN((short)12),
    REPEAT_UNTIL_POWER_GREATER_THAN((short)13),
    POWER_LESS_THAN((short)14),
    POWER_GREATER_THAN((short)15),
    TRAINING_PEAKS_TSS((short)16),
    REPEAT_UNTIL_POWER_LAST_LAP_LESS_THAN((short)17),
    REPEAT_UNTIL_MAX_POWER_LAST_LAP_LESS_THAN((short)18),
    POWER_3S_LESS_THAN((short)19),
    POWER_10S_LESS_THAN((short)20),
    POWER_30S_LESS_THAN((short)21),
    POWER_3S_GREATER_THAN((short)22),
    POWER_10S_GREATER_THAN((short)23),
    POWER_30S_GREATER_THAN((short)24),
    POWER_LAP_LESS_THAN((short)25),
    POWER_LAP_GREATER_THAN((short)26),
    REPEAT_UNTIL_TRAINING_PEAKS_TSS((short)27),
    REPETITION_TIME((short)28),
    REPS((short)29),
    TIME_ONLY((short)31),
    INVALID((short)255);

    protected short value;

    private WktStepDuration(short value) {
        this.value = value;
    }

    public static WktStepDuration getByValue(final Short value) {
        for (final WktStepDuration type : WktStepDuration.values()) {
            if (value == type.value)
                return type;
        }

        return WktStepDuration.INVALID;
    }

    /**
     * Retrieves the String Representation of the Value
     * @param value The enum constant
     * @return The name of this enum constant
     */
    public static String getStringFromValue( WktStepDuration value ) {
        return value.name();
    }

    public short getValue() {
        return value;
    }


}

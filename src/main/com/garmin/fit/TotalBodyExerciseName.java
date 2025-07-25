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

import java.util.HashMap;
import java.util.Map;

public class TotalBodyExerciseName  {
    public static final int BURPEE = 0;
    public static final int WEIGHTED_BURPEE = 1;
    public static final int BURPEE_BOX_JUMP = 2;
    public static final int WEIGHTED_BURPEE_BOX_JUMP = 3;
    public static final int HIGH_PULL_BURPEE = 4;
    public static final int MAN_MAKERS = 5;
    public static final int ONE_ARM_BURPEE = 6;
    public static final int SQUAT_THRUSTS = 7;
    public static final int WEIGHTED_SQUAT_THRUSTS = 8;
    public static final int SQUAT_PLANK_PUSH_UP = 9;
    public static final int WEIGHTED_SQUAT_PLANK_PUSH_UP = 10;
    public static final int STANDING_T_ROTATION_BALANCE = 11;
    public static final int WEIGHTED_STANDING_T_ROTATION_BALANCE = 12;
    public static final int BARBELL_BURPEE = 13;
    public static final int BURPEE_BOX_JUMP_OVER_YES_LITERALLY_JUMPING_OVER_THE_BOX = 15;
    public static final int BURPEE_BOX_JUMP_STEP_UP_OVER = 16;
    public static final int LATERAL_BARBELL_BURPEE = 17;
    public static final int TOTAL_BODY_BURPEE_OVER_BAR = 18;
    public static final int BURPEE_BOX_JUMP_OVER = 19;
    public static final int BURPEE_WHEELCHAIR = 20;
    public static final int INVALID = Fit.UINT16_INVALID;

    private static final Map<Integer, String> stringMap;

    static {
        stringMap = new HashMap<Integer, String>();
        stringMap.put(BURPEE, "BURPEE");
        stringMap.put(WEIGHTED_BURPEE, "WEIGHTED_BURPEE");
        stringMap.put(BURPEE_BOX_JUMP, "BURPEE_BOX_JUMP");
        stringMap.put(WEIGHTED_BURPEE_BOX_JUMP, "WEIGHTED_BURPEE_BOX_JUMP");
        stringMap.put(HIGH_PULL_BURPEE, "HIGH_PULL_BURPEE");
        stringMap.put(MAN_MAKERS, "MAN_MAKERS");
        stringMap.put(ONE_ARM_BURPEE, "ONE_ARM_BURPEE");
        stringMap.put(SQUAT_THRUSTS, "SQUAT_THRUSTS");
        stringMap.put(WEIGHTED_SQUAT_THRUSTS, "WEIGHTED_SQUAT_THRUSTS");
        stringMap.put(SQUAT_PLANK_PUSH_UP, "SQUAT_PLANK_PUSH_UP");
        stringMap.put(WEIGHTED_SQUAT_PLANK_PUSH_UP, "WEIGHTED_SQUAT_PLANK_PUSH_UP");
        stringMap.put(STANDING_T_ROTATION_BALANCE, "STANDING_T_ROTATION_BALANCE");
        stringMap.put(WEIGHTED_STANDING_T_ROTATION_BALANCE, "WEIGHTED_STANDING_T_ROTATION_BALANCE");
        stringMap.put(BARBELL_BURPEE, "BARBELL_BURPEE");
        stringMap.put(BURPEE_BOX_JUMP_OVER_YES_LITERALLY_JUMPING_OVER_THE_BOX, "BURPEE_BOX_JUMP_OVER_YES_LITERALLY_JUMPING_OVER_THE_BOX");
        stringMap.put(BURPEE_BOX_JUMP_STEP_UP_OVER, "BURPEE_BOX_JUMP_STEP_UP_OVER");
        stringMap.put(LATERAL_BARBELL_BURPEE, "LATERAL_BARBELL_BURPEE");
        stringMap.put(TOTAL_BODY_BURPEE_OVER_BAR, "TOTAL_BODY_BURPEE_OVER_BAR");
        stringMap.put(BURPEE_BOX_JUMP_OVER, "BURPEE_BOX_JUMP_OVER");
        stringMap.put(BURPEE_WHEELCHAIR, "BURPEE_WHEELCHAIR");
    }


    /**
     * Retrieves the String Representation of the Value
     * @param value The enum constant
     * @return The name of this enum contsant
     */
    public static String getStringFromValue( Integer value ) {
        if( stringMap.containsKey( value ) ) {
            return stringMap.get( value );
        }

        return "";
    }

    /**
     * Returns the enum constant with the specified name.
     * @param value The enum string value
     * @return The enum constant or INVALID if unknown
     */
    public static Integer getValueFromString( String value ) {
        for( Map.Entry<Integer, String> entry : stringMap.entrySet() ) {
            if( entry.getValue().equals( value ) ) {
                return entry.getKey();
            }
        }

        return INVALID;
    }

}

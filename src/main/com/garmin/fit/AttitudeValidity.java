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

public class AttitudeValidity  {
    public static final int TRACK_ANGLE_HEADING_VALID = 0x0001;
    public static final int PITCH_VALID = 0x0002;
    public static final int ROLL_VALID = 0x0004;
    public static final int LATERAL_BODY_ACCEL_VALID = 0x0008;
    public static final int NORMAL_BODY_ACCEL_VALID = 0x0010;
    public static final int TURN_RATE_VALID = 0x0020;
    public static final int HW_FAIL = 0x0040;
    public static final int MAG_INVALID = 0x0080;
    public static final int NO_GPS = 0x0100;
    public static final int GPS_INVALID = 0x0200;
    public static final int SOLUTION_COASTING = 0x0400;
    public static final int TRUE_TRACK_ANGLE = 0x0800;
    public static final int MAGNETIC_HEADING = 0x1000;
    public static final int INVALID = Fit.UINT16_INVALID;

    private static final Map<Integer, String> stringMap;

    static {
        stringMap = new HashMap<Integer, String>();
        stringMap.put(TRACK_ANGLE_HEADING_VALID, "TRACK_ANGLE_HEADING_VALID");
        stringMap.put(PITCH_VALID, "PITCH_VALID");
        stringMap.put(ROLL_VALID, "ROLL_VALID");
        stringMap.put(LATERAL_BODY_ACCEL_VALID, "LATERAL_BODY_ACCEL_VALID");
        stringMap.put(NORMAL_BODY_ACCEL_VALID, "NORMAL_BODY_ACCEL_VALID");
        stringMap.put(TURN_RATE_VALID, "TURN_RATE_VALID");
        stringMap.put(HW_FAIL, "HW_FAIL");
        stringMap.put(MAG_INVALID, "MAG_INVALID");
        stringMap.put(NO_GPS, "NO_GPS");
        stringMap.put(GPS_INVALID, "GPS_INVALID");
        stringMap.put(SOLUTION_COASTING, "SOLUTION_COASTING");
        stringMap.put(TRUE_TRACK_ANGLE, "TRUE_TRACK_ANGLE");
        stringMap.put(MAGNETIC_HEADING, "MAGNETIC_HEADING");
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

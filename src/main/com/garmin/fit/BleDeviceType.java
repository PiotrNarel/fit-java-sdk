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

public class BleDeviceType  {
    public static final short CONNECTED_GPS = 0; // GPS that is provided over a proprietary bluetooth service
    public static final short HEART_RATE = 1;
    public static final short BIKE_POWER = 2;
    public static final short BIKE_SPEED_CADENCE = 3;
    public static final short BIKE_SPEED = 4;
    public static final short BIKE_CADENCE = 5;
    public static final short FOOTPOD = 6;
    public static final short BIKE_TRAINER = 7; // Indoor-Bike FTMS protocol
    public static final short INVALID = Fit.UINT8_INVALID;

    private static final Map<Short, String> stringMap;

    static {
        stringMap = new HashMap<Short, String>();
        stringMap.put(CONNECTED_GPS, "CONNECTED_GPS");
        stringMap.put(HEART_RATE, "HEART_RATE");
        stringMap.put(BIKE_POWER, "BIKE_POWER");
        stringMap.put(BIKE_SPEED_CADENCE, "BIKE_SPEED_CADENCE");
        stringMap.put(BIKE_SPEED, "BIKE_SPEED");
        stringMap.put(BIKE_CADENCE, "BIKE_CADENCE");
        stringMap.put(FOOTPOD, "FOOTPOD");
        stringMap.put(BIKE_TRAINER, "BIKE_TRAINER");
    }


    /**
     * Retrieves the String Representation of the Value
     * @param value The enum constant
     * @return The name of this enum contsant
     */
    public static String getStringFromValue( Short value ) {
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
    public static Short getValueFromString( String value ) {
        for( Map.Entry<Short, String> entry : stringMap.entrySet() ) {
            if( entry.getValue().equals( value ) ) {
                return entry.getKey();
            }
        }

        return INVALID;
    }

}

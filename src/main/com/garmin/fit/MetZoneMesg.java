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



public class MetZoneMesg extends Mesg   {

    
    public static final int MessageIndexFieldNum = 254;
    
    public static final int HighBpmFieldNum = 1;
    
    public static final int CaloriesFieldNum = 2;
    
    public static final int FatCaloriesFieldNum = 3;
    

    protected static final  Mesg metZoneMesg;
    static {
        // met_zone
        metZoneMesg = new Mesg("met_zone", MesgNum.MET_ZONE);
        metZoneMesg.addField(new Field("message_index", MessageIndexFieldNum, 132, 1, 0, "", false, Profile.Type.MESSAGE_INDEX));
        
        metZoneMesg.addField(new Field("high_bpm", HighBpmFieldNum, 2, 1, 0, "", false, Profile.Type.UINT8));
        
        metZoneMesg.addField(new Field("calories", CaloriesFieldNum, 132, 10, 0, "kcal / min", false, Profile.Type.UINT16));
        
        metZoneMesg.addField(new Field("fat_calories", FatCaloriesFieldNum, 2, 10, 0, "kcal / min", false, Profile.Type.UINT8));
        
    }

    public MetZoneMesg() {
        super(Factory.createMesg(MesgNum.MET_ZONE));
    }

    public MetZoneMesg(final Mesg mesg) {
        super(mesg);
    }


    /**
     * Get message_index field
     *
     * @return message_index
     */
    public Integer getMessageIndex() {
        return getFieldIntegerValue(254, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set message_index field
     *
     * @param messageIndex The new messageIndex value to be set
     */
    public void setMessageIndex(Integer messageIndex) {
        setFieldValue(254, 0, messageIndex, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get high_bpm field
     *
     * @return high_bpm
     */
    public Short getHighBpm() {
        return getFieldShortValue(1, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set high_bpm field
     *
     * @param highBpm The new highBpm value to be set
     */
    public void setHighBpm(Short highBpm) {
        setFieldValue(1, 0, highBpm, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get calories field
     * Units: kcal / min
     *
     * @return calories
     */
    public Float getCalories() {
        return getFieldFloatValue(2, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set calories field
     * Units: kcal / min
     *
     * @param calories The new calories value to be set
     */
    public void setCalories(Float calories) {
        setFieldValue(2, 0, calories, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get fat_calories field
     * Units: kcal / min
     *
     * @return fat_calories
     */
    public Float getFatCalories() {
        return getFieldFloatValue(3, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set fat_calories field
     * Units: kcal / min
     *
     * @param fatCalories The new fatCalories value to be set
     */
    public void setFatCalories(Float fatCalories) {
        setFieldValue(3, 0, fatCalories, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

}

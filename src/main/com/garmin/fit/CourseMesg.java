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



public class CourseMesg extends Mesg   {

    
    public static final int SportFieldNum = 4;
    
    public static final int NameFieldNum = 5;
    
    public static final int CapabilitiesFieldNum = 6;
    
    public static final int SubSportFieldNum = 7;
    

    protected static final  Mesg courseMesg;
    static {
        // course
        courseMesg = new Mesg("course", MesgNum.COURSE);
        courseMesg.addField(new Field("sport", SportFieldNum, 0, 1, 0, "", false, Profile.Type.SPORT));
        
        courseMesg.addField(new Field("name", NameFieldNum, 7, 1, 0, "", false, Profile.Type.STRING));
        
        courseMesg.addField(new Field("capabilities", CapabilitiesFieldNum, 140, 1, 0, "", false, Profile.Type.COURSE_CAPABILITIES));
        
        courseMesg.addField(new Field("sub_sport", SubSportFieldNum, 0, 1, 0, "", false, Profile.Type.SUB_SPORT));
        
    }

    public CourseMesg() {
        super(Factory.createMesg(MesgNum.COURSE));
    }

    public CourseMesg(final Mesg mesg) {
        super(mesg);
    }


    /**
     * Get sport field
     *
     * @return sport
     */
    public Sport getSport() {
        Short value = getFieldShortValue(4, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return Sport.getByValue(value);
    }

    /**
     * Set sport field
     *
     * @param sport The new sport value to be set
     */
    public void setSport(Sport sport) {
        setFieldValue(4, 0, sport.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get name field
     *
     * @return name
     */
    public String getName() {
        return getFieldStringValue(5, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set name field
     *
     * @param name The new name value to be set
     */
    public void setName(String name) {
        setFieldValue(5, 0, name, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get capabilities field
     *
     * @return capabilities
     */
    public Long getCapabilities() {
        return getFieldLongValue(6, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set capabilities field
     *
     * @param capabilities The new capabilities value to be set
     */
    public void setCapabilities(Long capabilities) {
        setFieldValue(6, 0, capabilities, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get sub_sport field
     *
     * @return sub_sport
     */
    public SubSport getSubSport() {
        Short value = getFieldShortValue(7, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        if (value == null) {
            return null;
        }
        return SubSport.getByValue(value);
    }

    /**
     * Set sub_sport field
     *
     * @param subSport The new subSport value to be set
     */
    public void setSubSport(SubSport subSport) {
        setFieldValue(7, 0, subSport.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

}

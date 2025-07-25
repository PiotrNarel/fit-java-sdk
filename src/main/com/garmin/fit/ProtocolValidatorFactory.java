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

public class ProtocolValidatorFactory {

    private ProtocolValidatorFactory() {
    }

    /**
     * Factory method to create a ProtocolValidator object
     *
     * @param protocolVersion the protocol version
     * @return a ProtocolValidator object
     */
    public static ProtocolValidator getProtocolValidator(Fit.ProtocolVersion protocolVersion) {
        switch (protocolVersion) {
            case V1_0:
                return new V1Validator();

            default:
                return new V2Validator();
        }
    }
}

class V1Validator implements ProtocolValidator {

    private boolean hasDeveloperData(MesgDefinition defn) {
        return defn.developerFields.size() > 0;
    }

    @Override
    public boolean validateMesgDefn(MesgDefinition mesgDefinition) {
        if(hasDeveloperData(mesgDefinition)) {
            return false;
        }

        for (FieldDefinition def : mesgDefinition.getFields()) {
            int typeNum = def.getType() & Fit.BASE_TYPE_NUM_MASK;
            if (typeNum > Fit.BASE_TYPE_BYTE) {
                // Byte was the last type added to 1.0
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean validateMesg(Mesg mesg) {
        if (hasDeveloperData(new MesgDefinition(mesg))) {
            return false;
        }

        for (Field fld : mesg.getFields()) {
            int typeNum = fld.getType() & Fit.BASE_TYPE_NUM_MASK;
            if (typeNum > Fit.BASE_TYPE_BYTE) {
                // Byte was the last type added to 1.0
                return false;
            }
        }

        return true;
    }
}

class V2Validator implements ProtocolValidator {

    @Override
    public boolean validateMesgDefn(MesgDefinition defn) {
        return true;
    }

    @Override
    public boolean validateMesg(Mesg mesg) {
        return true;
    }
}

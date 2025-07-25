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

import java.nio.ByteBuffer;
import java.util.UUID;

public class DeveloperField extends FieldBase {
    private DeveloperFieldDefinition fieldDefinition;

    public DeveloperField(DeveloperFieldDefinition def) {
        super();
        this.fieldDefinition = def;
    }

    public DeveloperField(FieldDescriptionMesg descriptionMesg, DeveloperDataIdMesg developerDataIdMesg) {
        super();
        this.fieldDefinition = new DeveloperFieldDefinition(descriptionMesg, developerDataIdMesg);
    }

    public DeveloperField(final DeveloperField other) {
        super(other);
        this.fieldDefinition = other.fieldDefinition;
    }

    public boolean isDefined() {
        return fieldDefinition.isDefined();
    }

    public int getNum() {
        return fieldDefinition.getNum();
    }

    @Override
    public String getUnits() {
        return fieldDefinition.getUnits();
    }

    @Override
    public int getType() {
        return fieldDefinition.getType();
    }

    @Override
    protected double getOffset() {
        return fieldDefinition.getOffset();
    }

    @Override
    protected double getScale() {
        return fieldDefinition.getScale();
    }

    @Override
    protected String getFieldName() {
        return fieldDefinition.getFieldName();
    }

    public short getDeveloperDataIndex() {
        return fieldDefinition.getDeveloperDataIndex();
    }

    public long getAppVersion() {
        return fieldDefinition.getAppVersion();
    }

    public Byte[] getAppId() {
        return fieldDefinition.getAppId();
    }

    public UUID getAppUUID() {
        Byte[] appId = fieldDefinition.getAppId();
        byte[] primativeId = new byte[appId.length];

        for (byte i = 0; i < appId.length; i++) {
            primativeId[i] = appId[i];
        }

        ByteBuffer bb = ByteBuffer.wrap(primativeId);
        long high = bb.getLong();
        long low = bb.getLong();

        return new UUID(high, low);
    }

    @Override
    protected SubField getSubField(String subFieldName) {
        // Developer fields do not support sub-fields
        return null;
    }

    @Override
    protected SubField getSubField(int subFieldIndex) {
        // Developer fields do not support sub-fields
        return null;
    }

    DeveloperFieldDefinition getFieldDefinition() {
        return fieldDefinition;
    }

    /**
     * Get the Native override of the field
     *
     * @return The Field Number of the Overridden Field, {@link Fit#UINT8_INVALID} otherwise.
     */
    public short getNativeOverride() {
        return fieldDefinition.getNativeOverride();
    }
}

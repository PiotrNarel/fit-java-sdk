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

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Mesg {
    protected String name;
    protected int num;
    protected int localNum;
    protected ArrayList<Field> fields;
    protected ArrayList<DeveloperField> developerFields;
    protected long systemTimeOffset;
    private int decoderMesgIndex;

    public Mesg(final Mesg mesg) {
        this.fields = new ArrayList<Field>();
        this.developerFields = new ArrayList<DeveloperField>();

        if (mesg == null) {
            this.name = "unknown";
            this.num = MesgNum.INVALID;
            this.systemTimeOffset = 0;
            return;
        }

        this.name = mesg.name;
        this.num = mesg.num;
        this.localNum = mesg.localNum;
        this.systemTimeOffset = mesg.systemTimeOffset;
        this.decoderMesgIndex = mesg.decoderMesgIndex;

        for (Field field : mesg.fields) {
            if (field.getNumValues() > 0) {
                this.fields.add(new Field(field));
            }
        }

        for (DeveloperField field : mesg.developerFields) {
            if (field.getNumValues() > 0) {
                this.developerFields.add(new DeveloperField(field));
            }
        }
    }

    protected Mesg(String name, int num) {
        this.name = name;
        this.num = num;
        this.localNum = 0;
        this.fields = new ArrayList<Field>();
        this.developerFields = new ArrayList<DeveloperField>();
        this.systemTimeOffset = 0;
    }

    /**
     * Removes all fields from this message that have been generated through
     * component expansion while decoding the source .FIT file.
     */
    public void removeExpandedFields() {
        ArrayList<Field> fieldsToRemove = new ArrayList<Field>();

        for (Field field : fields) {
            if (field.getIsExpanded()) {
                fieldsToRemove.add(field);
            }
        }

        fields.removeAll(fieldsToRemove);
    }

    public void write(OutputStream out) {
        write(out, null);
    }

    public void write(OutputStream out, MesgDefinition mesgDef) {
        try {
            int headerByte = localNum & Fit.HDR_TYPE_MASK;

            new DataOutputStream(out).writeByte(headerByte); // Message record header.
        } catch ( java.io.IOException e ) {
            throw new FitRuntimeException(e);
        }

        if (mesgDef == null) {
            mesgDef = new MesgDefinition(this);
        }

        for (FieldDefinition fieldDef : mesgDef.fields) {
            Field field = this.getField(fieldDef.num);

            if (field == null) {
                field = Factory.createField(num, fieldDef.num);
            }

            field.write(out, fieldDef);
        }

        for (DeveloperFieldDefinition fieldDef : mesgDef.developerFields) {
            DeveloperField field = getDeveloperField(fieldDef.getDeveloperDataIndex(), fieldDef.getNum());

            if (field == null) {
                // Get Default from Definition
                field = fieldDef.getDefaultField();
            }

            field.write(out, fieldDef);
        }
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public boolean hasField(int num) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get( i ).num == num) {
                return true;
            }
        }

        return false;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void addDeveloperField(DeveloperField field) {
        for (int i = 0; i < developerFields.size(); i++) {
            DeveloperField fieldToCompare = developerFields.get( i );
            if ((fieldToCompare.getNum() == field.getNum()) &&
                 (fieldToCompare.getDeveloperDataIndex() == field.getDeveloperDataIndex())) {
                developerFields.set(i, field);
                return;
            }
        }

        developerFields.add(field);
    }

    public void setField(Field field) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get( i ).num == field.num) {
                fields.set(i, field);
                return;
            }
        }

        fields.add(field);
    }

    public int getNumFields() {
        return fields.size();
    }

    private DeveloperField getDeveloperField(short developerIndex, int num) {
        for (DeveloperField field : developerFields) {
            if ((field.getDeveloperDataIndex() == developerIndex) &&
                 (field.getNum() == num)) {
                return field;
            }
        }

        return null;
    }

    public Field getField(int num) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get( i ).num == num) {
                return fields.get(i);
            }
        }

        return null;
    }

    public Field getField(String name) {
        return getField(name, true);
    }

    public Field getField(String name, boolean checkMesgSupportForSubFields) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).name.equals(name)) {
                return fields.get(i);
            }

            for (int j = 0; j < fields.get( i ).subFields.size(); j++) {
                if ((fields.get(i).subFields.get(j).name.equals(name)) && (!checkMesgSupportForSubFields || (fields.get(i).subFields.get(j).canMesgSupport(this)))) {
                    return fields.get(i);
                }
            }
        }

        return null;
    }

    /**
     * Returns the active subfield index of a given field for this message.
     *
     * @param num The field number for the field to be checked
     * @return the subfield index used for the field in this message
     */
    public int getActiveSubFieldIndex(int num) {
        final Field testField = Factory.createField(this.num, num);

        if (testField == null) {
            return Fit.SUBFIELD_INDEX_MAIN_FIELD;
        }

        for (int i = 0; i < testField.subFields.size(); i++) {
            if (testField.subFields.get(i).canMesgSupport(this)) {
                return i;
            }
        }

        return Fit.SUBFIELD_INDEX_MAIN_FIELD;
    }

    /**
     * Returns the active subfield name of a given field for this message.
     *
     * @param num The field number for the field to be checked
     * @return the subfield name used for the field in this message
     */
    public String getActiveSubFieldName(int num) {
        final Field testField = Factory.createField(this.num, num);

        if (testField == null) {
            return Fit.SUBFIELD_NAME_MAIN_FIELD;
        }

        for (int i = 0; i < testField.subFields.size(); i++) {
            if (testField.subFields.get(i).canMesgSupport(this)) {
                return testField.subFields.get(i).getName();
            }
        }

        return Fit.SUBFIELD_NAME_MAIN_FIELD;
    }

    public int getNumFieldValues(int num) {
        return getNumFieldValues(num, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public int getNumFieldValues(int num, int subFieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return 0;
        }

        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            return field.getNumValues();
        }

        final SubField subField = field.getSubField(subFieldIndex);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getNumValues();
        } else {
            return 0;
        }
    }

    public int getNumFieldValues(int num, String subFieldName) {
        final Field field = getField(num);

        if (field == null) {
            return 0;
        }

        final SubField subField = field.getSubField(subFieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getNumValues();
        } else {
            return 0;
        }
    }

    public int getNumFieldValues(String name) {
        final Field field = getField(name, false);

        if (field == null) {
            return 0;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getNumValues();
        } else {
            return 0;
        }
    }

    public Object getFieldValue(int num) {
        return getFieldValue(num, 0, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Object getFieldValue(int num, int fieldArrayIndex) {
        return getFieldValue(num, fieldArrayIndex, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Object getFieldValue(int num, int fieldArrayIndex, int subFieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            return field.getValue(fieldArrayIndex, getActiveSubFieldIndex(num));
        } else {
            final SubField subField = field.getSubField(subFieldIndex);

            if ((subField == null) || (subField.canMesgSupport(this))) {
                return field.getValue(fieldArrayIndex, subFieldIndex);
            } else {
                return null;
            }
        }
    }

    public Object getFieldValue(int num, int fieldArrayIndex, String subFieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subFieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getValue(fieldArrayIndex, subFieldName);
        } else {
            return null;
        }
    }

    public Object getFieldValue(String name) {
        return getFieldValue(name, 0);
    }

    public Object getFieldValue(String name, int fieldArrayIndex) {
        final Field field = getField(name, false);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getValue(fieldArrayIndex, name);
        } else {
            return null;
        }
    }

    public boolean getIsFieldAccumulated(int num) {
        Field field = getField(num);
        if (field != null) {
            return field.getIsAccumulated();
        }
        return false;
    }

    public void setFieldValue(int num, Object value) {
        setFieldValue(num, 0, value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    public void setFieldValue(int num, int fieldArrayIndex, Object value) {
        setFieldValue(num, fieldArrayIndex, value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    public void setFieldValue(int num, int fieldArrayIndex, Object value, int subFieldIndex) {
        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            subFieldIndex = getActiveSubFieldIndex( num );
        }

        Field field = getField(num);

        if (field == null) {
            field = Factory.createField(this.num, num);
            addField(field);
        }

        field.setValue(fieldArrayIndex, value, subFieldIndex);
    }

    public void setFieldValue(int num, int fieldArrayIndex, Object value, String subFieldName) {
        Field field = getField(num);

        if (field == null) {
            field = Factory.createField(this.num, num);
            addField(field);
        }

        field.setValue(fieldArrayIndex, value, subFieldName);
    }

    public void setFieldValue(String name, Object value) {
        setFieldValue(name, 0, value);
    }

    public void setFieldValue(String name, int fieldArrayIndex, Object value) {
        Field field = getField(name, false);

        if (field == null) {
            field = Factory.createField(this.num, name);
            addField(field);
        }

        field.setValue(fieldArrayIndex, value, name);
    }

    public Long getFieldBitsValue(int num, int offset, int bits, boolean signed) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        return field.getBitsValue(offset, bits, signed);
    }

    public Long getFieldBitsValue(String name, int offset, int bits, boolean signed) {
        final Field field = getField(name, false);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getBitsValue(offset, bits, signed);
        } else {
            return null;
        }
    }

    public Byte[] getFieldByteValues(int num) {
        return getFieldByteValues(num, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Byte[] getFieldByteValues(int num, int subfieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldIndex);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getByteValues(subfieldIndex);
        } else {
            return null;
        }
    }

    public Byte[] getFieldByteValues(int num, String subfieldName) {
        final Field field = getField(num);

        if ( field == null ) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getByteValues(subfieldName);
        } else {
            return null;
        }
    }

    public Byte getFieldByteValue(int num) {
        return getFieldByteValue(num, 0, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Byte getFieldByteValue(int num, int fieldArrayIndex) {
        return getFieldByteValue(num, fieldArrayIndex, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Byte getFieldByteValue(int num, int fieldArrayIndex, int subFieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            return field.getByteValue(fieldArrayIndex, getActiveSubFieldIndex(num));
        } else {
            final SubField subField = field.getSubField(subFieldIndex);

            if ((subField == null) || (subField.canMesgSupport(this))) {
                return field.getByteValue(fieldArrayIndex, subFieldIndex);
            } else {
                return null;
            }
        }
    }

    public Byte getFieldByteValue(int num, int fieldArrayIndex, String subFieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subFieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getByteValue(fieldArrayIndex, subFieldName);
        } else {
            return null;
        }
    }

    public Byte getFieldByteValue(String name) {
        return getFieldByteValue(name, 0);
    }

    public Byte getFieldByteValue(String name, int fieldArrayIndex) {
        final Field field = getField(name, false);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getByteValue(fieldArrayIndex, name);
        } else {
            return null;
        }
    }

    public Short[] getFieldShortValues(int num) {
        return getFieldShortValues(num, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Short[] getFieldShortValues(int num, int subfieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldIndex);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getShortValues(subfieldIndex);
        } else {
            return null;
        }
    }

    public Short[] getFieldShortValues(int num, String subfieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getShortValues(subfieldName);
        } else {
            return null;
        }
    }

    public Short getFieldShortValue(int num) {
        return getFieldShortValue(num, 0, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Short getFieldShortValue(int num, int fieldArrayIndex) {
        return getFieldShortValue(num, fieldArrayIndex, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Short getFieldShortValue(int num, int fieldArrayIndex, int subFieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            return field.getShortValue(fieldArrayIndex, getActiveSubFieldIndex(num));
        } else {
            final SubField subField = field.getSubField(subFieldIndex);

            if ((subField == null) || (subField.canMesgSupport(this))) {
                return field.getShortValue(fieldArrayIndex, subFieldIndex);
            } else {
                return null;
            }
        }
    }

    public Short getFieldShortValue(int num, int fieldArrayIndex, String subFieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subFieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getShortValue(fieldArrayIndex, subFieldName);
        } else {
            return null;
        }
    }

    public Short getFieldShortValue(String name) {
        return getFieldShortValue(name, 0);
    }

    public Short getFieldShortValue(String name, int fieldArrayIndex) {
        final Field field = getField(name, false);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getShortValue(fieldArrayIndex, name);
        } else {
            return null;
        }
    }

    public Integer[] getFieldIntegerValues(int num) {
        return getFieldIntegerValues(num, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Integer[] getFieldIntegerValues(int num, int subfieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldIndex);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getIntegerValues(subfieldIndex);
        } else {
            return null;
        }
    }

    public Integer[] getFieldIntegerValues(int num, String subfieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getIntegerValues(subfieldName);
        } else {
            return null;
        }
    }

    public Integer getFieldIntegerValue(int num) {
        return getFieldIntegerValue(num, 0, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Integer getFieldIntegerValue(int num, int fieldArrayIndex) {
        return getFieldIntegerValue(num, fieldArrayIndex, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Integer getFieldIntegerValue(int num, int fieldArrayIndex, int subFieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            return field.getIntegerValue(fieldArrayIndex, getActiveSubFieldIndex(num));
        } else {
            final SubField subField = field.getSubField(subFieldIndex);

            if ((subField == null) || (subField.canMesgSupport(this))) {
                return field.getIntegerValue(fieldArrayIndex, subFieldIndex);
            } else {
                return null;
            }
        }
    }

    public Integer getFieldIntegerValue(int num, int fieldArrayIndex, String subFieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subFieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getIntegerValue(fieldArrayIndex, subFieldName);
        } else {
            return null;
        }
    }

    public Integer getFieldIntegerValue(String name) {
        return getFieldIntegerValue(name, 0);
    }

    public Integer getFieldIntegerValue(String name, int fieldArrayIndex) {
        final Field field = getField(name, false);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getIntegerValue(fieldArrayIndex, name);
        } else {
            return null;
        }
    }

    public Long[] getFieldLongValues(int num) {
        return getFieldLongValues(num, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Long[] getFieldLongValues(int num, int subfieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldIndex);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getLongValues(subfieldIndex);
        } else {
            return null;
        }
    }

    public Long[] getFieldLongValues(int num, String subfieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getLongValues(subfieldName);
        } else {
            return null;
        }
    }

    public Long getFieldLongValue(int num) {
        return getFieldLongValue(num, 0, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Long getFieldLongValue(int num, int fieldArrayIndex) {
        return getFieldLongValue(num, fieldArrayIndex, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Long getFieldLongValue(int num, int fieldArrayIndex, int subFieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            return field.getLongValue(fieldArrayIndex, getActiveSubFieldIndex(num));
        } else {
            final SubField subField = field.getSubField(subFieldIndex);

            if ((subField == null) || (subField.canMesgSupport(this))) {
                return field.getLongValue(fieldArrayIndex, subFieldIndex);
            } else {
                return null;
            }
        }
    }

    public Long getFieldLongValue(int num, int fieldArrayIndex, String subFieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subFieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getLongValue(fieldArrayIndex, subFieldName);
        } else {
            return null;
        }
    }

    public Long getFieldLongValue(String name) {
        return getFieldLongValue(name, 0);
    }

    public Long getFieldLongValue(String name, int fieldArrayIndex) {
        final Field field = getField(name, false);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getLongValue(fieldArrayIndex, name);
        } else {
            return null;
        }
    }

    public Float[] getFieldFloatValues(int num) {
        return getFieldFloatValues(num, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Float[] getFieldFloatValues(int num, int subfieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldIndex);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getFloatValues(subfieldIndex);
        } else {
            return null;
        }
    }

    public Float[] getFieldFloatValues(int num, String subfieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getFloatValues(subfieldName);
        } else {
            return null;
        }
    }

    public Float getFieldFloatValue(int num) {
        return getFieldFloatValue(num, 0, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Float getFieldFloatValue(int num, int fieldArrayIndex) {
        return getFieldFloatValue(num, fieldArrayIndex, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Float getFieldFloatValue(int num, int fieldArrayIndex, int subFieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            return field.getFloatValue(fieldArrayIndex, getActiveSubFieldIndex(num));
        } else {
            final SubField subField = field.getSubField(subFieldIndex);

            if ((subField == null) || (subField.canMesgSupport(this))) {
                return field.getFloatValue(fieldArrayIndex, subFieldIndex);
            } else {
                return null;
            }
        }
    }

    public Float getFieldFloatValue(int num, int fieldArrayIndex, String subFieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subFieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getFloatValue(fieldArrayIndex, subFieldName);
        } else {
            return null;
        }
    }

    public Float getFieldFloatValue(String name) {
        return getFieldFloatValue(name, 0);
    }

    public Float getFieldFloatValue(String name, int fieldArrayIndex) {
        final Field field = getField(name, false);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getFloatValue(fieldArrayIndex, name);
        } else {
            return null;
        }
    }

    public Double[] getFieldDoubleValues(int num) {
        return getFieldDoubleValues(num, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Double[] getFieldDoubleValues(int num, int subfieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldIndex);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getDoubleValues(subfieldIndex);
        } else {
            return null;
        }
    }

    public Double[] getFieldDoubleValues(int num, String subfieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getDoubleValues(subfieldName);
        } else {
            return null;
        }
    }

    public Double getFieldDoubleValue(int num) {
        return getFieldDoubleValue(num, 0, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Double getFieldDoubleValue(int num, int fieldArrayIndex) {
        return getFieldDoubleValue(num, fieldArrayIndex, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public Double getFieldDoubleValue(int num, int fieldArrayIndex, int subFieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            return field.getDoubleValue(fieldArrayIndex, getActiveSubFieldIndex(num));
        } else {
            final SubField subField = field.getSubField(subFieldIndex);

            if ((subField == null) || (subField.canMesgSupport(this))) {
                return field.getDoubleValue(fieldArrayIndex, subFieldIndex);
            } else {
                return null;
            }
        }
    }

    public Double getFieldDoubleValue(int num, int fieldArrayIndex, String subFieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subFieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getDoubleValue(fieldArrayIndex, subFieldName);
        } else {
            return null;
        }
    }

    public Double getFieldDoubleValue(String name) {
        return getFieldDoubleValue(name, 0);
    }

    public Double getFieldDoubleValue(String name, int fieldArrayIndex) {
        final Field field = getField(name, false);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getDoubleValue(fieldArrayIndex, name);
        } else {
            return null;
        }
    }

    public String[] getFieldStringValues(int num) {
        return getFieldStringValues(num, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public String[] getFieldStringValues(int num, int subfieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldIndex);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getStringValues(subfieldIndex);
        } else {
            return null;
        }
    }

    public String[] getFieldStringValues(int num, String subfieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getStringValues(subfieldName);
        } else {
            return null;
        }
    }

    public String getFieldStringValue(int num) {
        return getFieldStringValue(num, 0, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public String getFieldStringValue(int num, int fieldArrayIndex) {
        return getFieldStringValue(num, fieldArrayIndex, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public String getFieldStringValue(int num, int fieldArrayIndex, int subFieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            return field.getStringValue(fieldArrayIndex, getActiveSubFieldIndex(num));
        } else {
            final SubField subField = field.getSubField(subFieldIndex);

            if ((subField == null) || (subField.canMesgSupport(this))) {
                return field.getStringValue(fieldArrayIndex, subFieldIndex);
            } else {
                return null;
            }
        }
    }

    public String getFieldStringValue(int num, int fieldArrayIndex, String subFieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subFieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getStringValue(fieldArrayIndex, subFieldName);
        } else {
            return null;
        }
    }

    public String getFieldStringValue(String name) {
        return getFieldStringValue(name, 0);
    }

    public String getFieldStringValue(String name, int fieldArrayIndex) {
        final Field field = getField(name, false);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getStringValue(fieldArrayIndex, name);
        } else {
            return null;
        }
    }

    public BigInteger[] getFieldBigIntegerValues(int num) {
        return getFieldBigIntegerValues(num, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public BigInteger[] getFieldBigIntegerValues(int num, int subfieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldIndex);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getBigIntegerValues(subfieldIndex);
        } else {
            return null;
        }
    }

    public BigInteger[] getFieldBigIntegerValues(int num, String subfieldName) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subfieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getBigIntegerValues(subfieldName);
        } else {
            return null;
        }
    }

    public BigInteger getFieldBigIntegerValue(int num) {
        return getFieldBigIntegerValue(num, 0, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public BigInteger getFieldBigIntegerValue(int num, int fieldArrayIndex) {
        return getFieldBigIntegerValue(num, fieldArrayIndex, Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD);
    }

    public BigInteger getFieldBigIntegerValue(int num, int fieldArrayIndex, int subFieldIndex) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        if (subFieldIndex == Fit.SUBFIELD_INDEX_ACTIVE_SUBFIELD) {
            return field.getBigIntegerValue(fieldArrayIndex, getActiveSubFieldIndex(num));
        } else {
            final SubField subField = field.getSubField(subFieldIndex);

            if ((subField == null) || (subField.canMesgSupport(this))) {
                return field.getBigIntegerValue(fieldArrayIndex, subFieldIndex);
            } else {
                return null;
            }
        }
    }

    public BigInteger getFieldBigIntegerValue(int num, int fieldArrayIndex, String subFieldName ) {
        final Field field = getField(num);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(subFieldName);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getBigIntegerValue(fieldArrayIndex, subFieldName);
        } else {
            return null;
        }
    }

    public BigInteger getFieldBigIntegerValue(String name) {
        return getFieldBigIntegerValue(name, 0);
    }

    public BigInteger getFieldBigIntegerValue(String name, int fieldArrayIndex) {
        final Field field = getField(name, false);

        if (field == null) {
            return null;
        }

        final SubField subField = field.getSubField(name);

        if ((subField == null) || (subField.canMesgSupport(this))) {
            return field.getBigIntegerValue(fieldArrayIndex, name);
        } else {
            return null;
        }
    }

    public Collection<Field> getFields() {
        return Collections.unmodifiableCollection(fields);
    }

    public void setFields(Mesg mesg) {
        if (mesg.num != num) {
            return;
        }

        for (Field field : mesg.fields) {
            setField(field);
        }
    }

    public DateTime timestampToDateTime(Long timestamp) {
        DateTime dateTime;

        if (timestamp == null) {
            return null;
        }

        dateTime = new DateTime(timestamp);
        dateTime.convertSystemTimeToUTC(systemTimeOffset);

        return dateTime;
    }

    public int getLocalNum() {
        return localNum;
    }

    public void setLocalNum(int localNum) {
        if (localNum >= Fit.MAX_LOCAL_MESGS) {
            throw new FitRuntimeException("Invalid local message number " + localNum + ".  Local message number must be < " + Fit.MAX_LOCAL_MESGS + ".");
        }

        this.localNum = localNum;
    }

    protected void setDecoderMessageIndex(int decoderMesgIndex) {
        this.decoderMesgIndex = decoderMesgIndex;
    }

    public int getDecoderMesgIndex() {
        return decoderMesgIndex;
    }

    public boolean removeField(Field f) {
        boolean removedItem = false;
        if (this.fields.contains(f)) {
            this.fields.remove(f);
            removedItem = true;
        }
        return removedItem;
    }

    /**
     * Retrieve all developer fields in the message
     *
     * @return {@link Iterable} of all {@link DeveloperField}s in the message
     */
    public Iterable<DeveloperField> getDeveloperFields() {
        return developerFields;
    }

    /**
     * Retrieve all fields that are equivalent to the requested field number
     *
     * @param fieldNum The Field Number from the Profile that we are looking for
     * @return {@link Iterable} of {@link FieldBase}s that are equivalent to the field number provided
     */
    public Iterable<FieldBase> getOverrideField(short fieldNum) {
        LinkedList<FieldBase> fields = new LinkedList<FieldBase>();

        Field nativeField = getField(fieldNum);

        if (null != nativeField) {
            fields.add(nativeField);
        }

        for (DeveloperField devField : developerFields) {
            if (devField.getNativeOverride() == fieldNum) {
                fields.add(devField);
            }
        }

        return fields;
    }
}

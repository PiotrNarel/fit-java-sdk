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

import java.io.OutputStream;
import java.util.ArrayList;

public class MesgDefinition {
    protected int num;
    protected int localNum;
    protected int arch;
    protected ArrayList<FieldDefinition> fields;
    protected ArrayList<DeveloperFieldDefinition> developerFields;

    protected MesgDefinition() {
        num = MesgNum.INVALID;
        localNum = 0;
        arch = Fit.ARCH_ENDIAN_BIG;
        fields = new ArrayList<FieldDefinition>();
        developerFields = new ArrayList<DeveloperFieldDefinition>();
    }

    public MesgDefinition(final Mesg mesg) {
        num = mesg.num;
        localNum = mesg.localNum;
        arch = Fit.ARCH_ENDIAN_BIG;

        if (localNum >= Fit.MAX_LOCAL_MESGS) {
            throw new FitRuntimeException("Invalid local message number " + localNum + ".  Local message number must be < " + Fit.MAX_LOCAL_MESGS + ".");
        }

        fields = new ArrayList<FieldDefinition>();
        developerFields = new ArrayList<DeveloperFieldDefinition>();

        for (Field field : mesg.fields) {
            fields.add(new FieldDefinition(field));
        }

        for(DeveloperField field : mesg.developerFields) {
            developerFields.add(new DeveloperFieldDefinition(field));
        }
    }

    public int getNum() {
        return num;
    }

    public int getLocalNum() {
        return localNum;
    }

    public int getArch() {
        return arch;
    }

    public ArrayList<FieldDefinition> getFields() {
        return fields;
    }

    public FieldDefinition getField(int num) {
        for (FieldDefinition field : fields) {
            if (field.num == num) {
                return field;
            }
        }

        return null;
    }

    public void addField(FieldDefinition fieldDef) {
        fields.add(fieldDef);
    }

    public void addDeveloperField(DeveloperFieldDefinition fieldDef) {
        developerFields.add(fieldDef);
    }

    public int getDeveloperFieldTotalSize() {
        int rv = 0;

        for( DeveloperFieldDefinition devField : developerFields ) {
            rv += devField.getSize();
        }

        return rv;
    }

    public void write(OutputStream out) {
        try {
            int headerByte = Fit.HDR_TYPE_DEF_BIT | (localNum & Fit.HDR_TYPE_MASK);
            if(!developerFields.isEmpty()) {
                headerByte |= Fit.HDR_DEV_FIELDS_BIT;
            }

            out.write(headerByte); // Message definition record header.
            out.write(0); // Reserved
            out.write(Fit.ARCH_ENDIAN_BIG);
            out.write(num >> 8);
            out.write(num);
            out.write(fields.size());

            for (FieldDefinition field : fields) {
                field.write(out);
            }

            if(!developerFields.isEmpty()) {
                out.write(developerFields.size());

                for(DeveloperFieldDefinition field : developerFields){
                    field.write(out);
                }
            }
        } catch (java.io.IOException e) {
            throw new FitRuntimeException(e);
        }

    }

    public boolean supports(Mesg mesg) {
        return supports(new MesgDefinition(mesg));
    }

    public boolean supports(MesgDefinition mesgDef) {
        if (mesgDef == null) {
            return false;
        }

        if (num != mesgDef.num) {
            return false;
        }

        if (localNum != mesgDef.localNum) {
            return false;
        }

        for (FieldDefinition fieldDef : mesgDef.fields) {
            FieldDefinition supportedFieldDef = getField(fieldDef.num);

            if (supportedFieldDef == null) {
                return false;
            }

            if (fieldDef.size > supportedFieldDef.size) {
                return false;
            }
        }

        // Check to make sure that all field developer fields are defined
        for(DeveloperFieldDefinition fieldDef : mesgDef.developerFields) {
            DeveloperFieldDefinition supportedDef = getDeveloperField(fieldDef.getDeveloperDataIndex(), fieldDef.getNum());

            // There is a Field Definition that we don't have a description for
            if(supportedDef == null) {
                return false;
            }

            // The definition is a larger size that we dont support
            if(fieldDef.getSize() > supportedDef.getSize()) {
                return false;
            }
        }

        return true;
    }

    private DeveloperFieldDefinition getDeveloperField(short developerIndex, int num) {
        for (DeveloperFieldDefinition field : developerFields) {
            if ((field.getNum() == num) && (field.getDeveloperDataIndex() == developerIndex)) {
                return field;
            }
        }

        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MesgDefinition)) {
            return false;
        }

        MesgDefinition other = (MesgDefinition)o;

        if (num != other.num) {
            return false;
        }

        if (localNum != other.localNum) {
            return false;
        }

        if (fields.size() != other.fields.size()) {
            return false;
        }

        for (int i=0; i<fields.size(); i++) {
            if (!fields.get(i).equals(other.fields.get(i))) {
                return false;
            }
        }

        return true;
    }

    public int hashCode() {
        int hashCode = 1;

        hashCode = (hashCode * 31) + new Integer(num).hashCode();
        hashCode = (hashCode * 47) + new Integer(localNum).hashCode();
        hashCode = (hashCode * 19) + fields.hashCode();

        return hashCode;
    }

    public Iterable<DeveloperFieldDefinition> getDeveloperFields() {
        return developerFields;
    }
}

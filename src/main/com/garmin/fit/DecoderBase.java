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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;


abstract class DecoderBase implements MesgSource {

    protected final int CompressedHeaderMask = Fit.HDR_TIME_REC_BIT;
    protected final int MesgDefinitionMask = Fit.HDR_TYPE_DEF_BIT;
    protected final int DevDataMask = Fit.HDR_DEV_FIELDS_BIT;
    protected final int MesgHeaderMask = 0x00;
    protected final int LocalMesgNumMask = Fit.HDR_TYPE_MASK;
    protected final MesgDefinition[] localMesgDefs = new MesgDefinition[Fit.MAX_LOCAL_MESGS];
    protected final byte[] fieldData = new byte[Fit.MAX_FIELD_SIZE];
    protected final ArrayList<MesgListener> mesgListeners = new ArrayList<>();
    protected final Accumulator accumulator = new Accumulator();
    protected ByteArrayDataInputStream stream = null;
    protected ArrayList<MesgDefinitionListener> mesgDefListeners = new ArrayList<MesgDefinitionListener>();
    protected ArrayList<DeveloperFieldDescriptionListener> devFieldDescListeners = new ArrayList<DeveloperFieldDescriptionListener>();
    private CRC16 crc = null;
    protected int decoderMesgIndex = 0;

    protected DecoderBase() {
    }

    protected DecoderBase(final byte[] bytes) {
        stream = new ByteArrayDataInputStream(bytes);
    }

    /**
     * Reads the FIT file header from the input stream and checks that the input
     * is a valid .FIT file. The position of the input stream is not changed.
     *
     * @param stream representing the FIT file to be checked
     * @return true if the input is a FIT file, false if it is not
     */
    public static boolean isFIT(final ByteArrayInputStream stream) {
        try {
            stream.mark(0);

            byte[] fileHeader = readHeader(stream);
            String dotFIT = new String(fileHeader, 8, 4);

            return dotFIT.equals(".FIT");
        } catch (Exception e) {
            return false;
        } finally {
            stream.reset();
        }
    }

    /**
     * Reads the FIT file header from the input stream and checks that the input
     * is a valid .FIT file. The position of the input stream is not changed.
     *
     * @param bytes representing the FIT file to be checked
     * @return true if the input is a FIT file, false if it is not
     */
    public static boolean isFIT(final byte[] bytes) {
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        return isFIT(stream);
    }

    /**
     * Reads the FIT file header from the input stream and checks that the input
     * is a valid .FIT file and checks the integrity file.
     *
     * @param stream representing the FIT file to be checked
     * @return true if the integrity of the FIT file is good, false if it is not
     */
    private static boolean checkIntegrity(final ByteArrayDataInputStream stream) {
        try {
            while (stream.available() > 0) {
                if (!isFIT(stream)) {
                    throw new Exception();
                }

                stream.mark(0);

                byte[] fileHeader = readHeader(stream);
                stream.reset();

                int headerSize = fileHeader[0];
                int dataSize = ByteBuffer.wrap(fileHeader, 4, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();

                if (stream.available() < headerSize + dataSize + Fit.CRC_SIZE) {
                    throw new Exception();
                }

                CRC16 crc = new CRC16();

                byte[] data = new byte[headerSize + dataSize];
                stream.read(data, 0, headerSize + dataSize);
                crc.update(data, 0, data.length);

                int fileCrc = stream.readUShort();
                if (fileCrc != crc.getValue()) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            stream.reset();
        }
    }

    /**
     * Reads the FIT file header from the input stream and checks that the input
     * is a valid .FIT file and checks the integrity file.
     *
     * @param bytes representing the FIT file to be checked
     * @return true if the integrity of the FIT file is good, false if it is not
     */
    public static boolean checkIntegrity(final byte[] bytes) {
        ByteArrayDataInputStream stream = new ByteArrayDataInputStream(bytes);
        return checkIntegrity(stream);
    }

    /**
     * Reads the FIT file header from the input stream and returns the byte array containing the header.
     *
     * @param stream representing the FIT file to read the header from
     * @return a byte array containing the bytes of the header
     * @throws Exception if an error occurs while trying to read the header
     * @throws FitRuntimeException if the file header is invalid
     */
    protected static byte[] readHeader(final InputStream stream) throws Exception {
        byte[] fileHeader = new byte[14];

        fileHeader[0] = (byte) stream.read();
        byte headerSize = fileHeader[0];

        if (headerSize != 12 && headerSize != 14) {
            throw new FitRuntimeException("Invalid Header Size");
        }

        int bytesRead = stream.read(fileHeader, 1, headerSize - 1);

        if (bytesRead != headerSize - 1) {
            throw new FitRuntimeException("Invalid Header - File Size less than Header Size");
        }

        return fileHeader;
    }

    protected byte[] readHeader() throws Exception {
        byte[] fileHeader = readHeader(stream);
        byte headerSize = fileHeader[0];

        crc.update(fileHeader, 0, headerSize);

        return fileHeader;
    }

    public int readByte() {
        int value = stream.read();
        crc.update(value);

        return value;
    }

    public int readUShort() {
        int value = stream.readUShort();
        crc.update(value & 0xFF);
        crc.update((value >> 8) & 0xFF);

        return value;
    }

    public int readBytes(byte[] bytes, int off, int len) {
        int bytesRead = stream.read(bytes, off, len);

        crc.update(bytes, off, len);

        return bytesRead;
    }

    protected long getCrcValue() {
        return crc.getValue();
    }

    protected void resetCrc() {
        crc = new CRC16();
    }

    /**
     * Add a MesgListener observer
     *
     * @param mesgListener to add as an observer
     */
    @Override
    public void addListener(MesgListener mesgListener) {
        if ((mesgListener != null) && !mesgListeners.contains(mesgListener)) {
            mesgListeners.add(mesgListener);
        }
    }

    @SuppressWarnings("overloads")
    public void addListener(MesgDefinitionListener mesgDefinitionListener) {
        if ((mesgDefinitionListener != null) && !mesgDefListeners.contains(mesgDefinitionListener)) {
            mesgDefListeners.add(mesgDefinitionListener);
        }
    }

    @SuppressWarnings("overloads")
    public void addListener(DeveloperFieldDescriptionListener listener) {
        if ((listener != null) && !devFieldDescListeners.contains(listener)) {
            devFieldDescListeners.add(listener);
        }
    }

    protected void expandComponents(Mesg mesg, Field containingField, ArrayList<FieldComponent> componentList) {
        int offset = 0;
        int i;

        for (i = 0; i < componentList.size(); i++) {
            FieldComponent component = componentList.get(i);

            if (component.fieldNum != Fit.FIELD_NUM_INVALID) {
                Field componentField = Factory.createField(mesg.num, component.fieldNum);
                int subFieldIndex = mesg.getActiveSubFieldIndex(component.fieldNum);
                SubField subField = componentField.getSubField(subFieldIndex);
                Long bitsValue;
                Double value;

                // Mark that this field has been generated through expansion
                componentField.setIsExpanded(true);

                // Get raw bits value
                bitsValue = containingField.getBitsValue(offset, component.bits, componentField.isSignedInteger());

                if (bitsValue == null) {
                    break; // No more data for components.
                }

                if (component.accumulate) {
                    bitsValue = accumulator.accumulate(mesg.num, component.fieldNum, bitsValue, component.bits);
                }

                // If the component field itself has *one* component apply the scale and offset of the componentField's
                // (nested) component
                if (componentField.components.size() == 1) {
                    value = (((bitsValue / component.scale) - component.offset) + componentField.components.get(0).offset) * componentField.components.get(0).scale;
                    if (mesg.hasField(componentField.num)) {
                        mesg.getField(componentField.num).addRawValue(value);
                    }
                    else {
                        componentField.addRawValue(value);
                        mesg.addField(componentField);
                    }
                }
                // The component field is itself a composite field (more than one component).  Don't use scale/offset, containing
                // field data must already be encoded.  Add elements to it until we have added bitsvalue
                else if (componentField.components.size() > 1) {
                    int bitsAdded = 0;
                    long mask;

                    while (bitsAdded < component.bits) {
                        mask = ((long) 1 << Fit.baseTypeSizes[componentField.type & Fit.BASE_TYPE_NUM_MASK]) - 1;
                        if (mesg.hasField(componentField.num)) {
                            mesg.getField(componentField.num).addValue(bitsValue & mask);
                        }
                        else {
                            componentField.addValue(bitsValue & mask);
                            mesg.addField(componentField);
                        }
                        bitsValue >>>= Fit.baseTypeSizes[componentField.type & Fit.BASE_TYPE_NUM_MASK];
                        bitsAdded += Fit.baseTypeSizes[componentField.type & Fit.BASE_TYPE_NUM_MASK];
                    }
                }
                // componentField is an ordinary field, apply scale and offset as usual
                else {
                    if (subField == null) {
                        value = (((bitsValue / component.scale) - component.offset) + componentField.offset) * componentField.scale;
                    }
                    else {
                        value = (((bitsValue / component.scale) - component.offset) + subField.offset) * subField.scale;
                    }

                    if (mesg.hasField(componentField.num)) {
                        mesg.getField(componentField.num).addRawValue(value);
                    }
                    else {
                        componentField.addRawValue(value);
                        mesg.addField(componentField);
                    }
                }
            }
            offset += component.bits;
        }
    }

    protected void decodeCompressedTimestampDataMessage() throws Exception {
        int recordHeader = stream.read();
        throw new FitRuntimeException("Compressed timestamp messages are not currently supported.");
    }

    protected void flipFieldDataByteOrder(int typeSize, int elements) {
        // Swap the bytes for each element.
        for (int element = 0; element < elements; element++) {
            for (int i = 0; i < (typeSize / 2); i++) {
                byte tmp = fieldData[element * typeSize + i];
                fieldData[element * typeSize + i] = fieldData[element * typeSize + typeSize - i - 1];
                fieldData[element * typeSize + typeSize - i - 1] = tmp;
            }
        }
    }

}

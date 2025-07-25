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

/**
 * ByteArrayInputStream with added functionality similar to the Data InputStream class
 */
public class ByteArrayDataInputStream extends ByteArrayInputStream {
    /**
     * Creates a ByteArrayDataInputStream so that it uses buf as its buffer array
     *
     * @param buf - the input buffer
     * @see java.io.ByteArrayInputStream
     * @see java.io.DataInputStream
     */
    public ByteArrayDataInputStream(byte[] buf) {
        super(buf);
    }

    /**
     * Returns the current stream position
     *
     * @return the current stream position
     */
    public int getPosition() {
        return super.pos;
    }

    /**
     * Returns the next available byte without changing the current stream position
     *
     * @return A byte representing the next value to be read
     */
    public byte peek() {
        return this.buf[this.pos];
    }

    /**
     * Reads an unsigned short value from the stream as little endian
     *
     * @return an int value representing an unsigned short value read from the stream as little endian
     */
    public int readUShort() {
        int value = (short) (read() & 0xFF);
        value |= (((short) read() & 0xFF) << 8);
        return value & 0xFFFF;
    }
}
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



public class MemoGlobMesg extends Mesg   {

    
    public static final int PartIndexFieldNum = 250;
    
    public static final int MemoFieldNum = 0;
    
    public static final int MesgNumFieldNum = 1;
    
    public static final int ParentIndexFieldNum = 2;
    
    public static final int FieldNumFieldNum = 3;
    
    public static final int DataFieldNum = 4;
    

    protected static final  Mesg memoGlobMesg;
    static {
        // memo_glob
        memoGlobMesg = new Mesg("memo_glob", MesgNum.MEMO_GLOB);
        memoGlobMesg.addField(new Field("part_index", PartIndexFieldNum, 134, 1, 0, "", false, Profile.Type.UINT32));
        
        memoGlobMesg.addField(new Field("memo", MemoFieldNum, 13, 1, 0, "", false, Profile.Type.BYTE));
        
        memoGlobMesg.addField(new Field("mesg_num", MesgNumFieldNum, 132, 1, 0, "", false, Profile.Type.MESG_NUM));
        
        memoGlobMesg.addField(new Field("parent_index", ParentIndexFieldNum, 132, 1, 0, "", false, Profile.Type.MESSAGE_INDEX));
        
        memoGlobMesg.addField(new Field("field_num", FieldNumFieldNum, 2, 1, 0, "", false, Profile.Type.UINT8));
        
        memoGlobMesg.addField(new Field("data", DataFieldNum, 10, 1, 0, "", false, Profile.Type.UINT8Z));
        
    }

    public MemoGlobMesg() {
        super(Factory.createMesg(MesgNum.MEMO_GLOB));
    }

    public MemoGlobMesg(final Mesg mesg) {
        super(mesg);
    }


    /**
     * Get part_index field
     * Comment: Sequence number of memo blocks
     *
     * @return part_index
     */
    public Long getPartIndex() {
        return getFieldLongValue(250, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set part_index field
     * Comment: Sequence number of memo blocks
     *
     * @param partIndex The new partIndex value to be set
     */
    public void setPartIndex(Long partIndex) {
        setFieldValue(250, 0, partIndex, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    public Byte[] getMemo() {
        
        return getFieldByteValues(0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        
    }

    /**
     * @return number of memo
     */
    public int getNumMemo() {
        return getNumFieldValues(0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get memo field
     * Comment: Deprecated. Use data field.
     *
     * @param index of memo
     * @return memo
     */
    public Byte getMemo(int index) {
        return getFieldByteValue(0, index, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set memo field
     * Comment: Deprecated. Use data field.
     *
     * @param index of memo
     * @param memo The new memo value to be set
     */
    public void setMemo(int index, Byte memo) {
        setFieldValue(0, index, memo, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get mesg_num field
     * Comment: Message Number of the parent message
     *
     * @return mesg_num
     */
    public Integer getMesgNum() {
        return getFieldIntegerValue(1, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set mesg_num field
     * Comment: Message Number of the parent message
     *
     * @param mesgNum The new mesgNum value to be set
     */
    public void setMesgNum(Integer mesgNum) {
        setFieldValue(1, 0, mesgNum, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get parent_index field
     * Comment: Index of mesg that this glob is associated with.
     *
     * @return parent_index
     */
    public Integer getParentIndex() {
        return getFieldIntegerValue(2, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set parent_index field
     * Comment: Index of mesg that this glob is associated with.
     *
     * @param parentIndex The new parentIndex value to be set
     */
    public void setParentIndex(Integer parentIndex) {
        setFieldValue(2, 0, parentIndex, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get field_num field
     * Comment: Field within the parent that this glob is associated with
     *
     * @return field_num
     */
    public Short getFieldNum() {
        return getFieldShortValue(3, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set field_num field
     * Comment: Field within the parent that this glob is associated with
     *
     * @param fieldNum The new fieldNum value to be set
     */
    public void setFieldNum(Short fieldNum) {
        setFieldValue(3, 0, fieldNum, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    public Short[] getData() {
        
        return getFieldShortValues(4, Fit.SUBFIELD_INDEX_MAIN_FIELD);
        
    }

    /**
     * @return number of data
     */
    public int getNumData() {
        return getNumFieldValues(4, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Get data field
     * Comment: Block of utf8 bytes. Note, mutltibyte characters may be split across adjoining memo_glob messages.
     *
     * @param index of data
     * @return data
     */
    public Short getData(int index) {
        return getFieldShortValue(4, index, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

    /**
     * Set data field
     * Comment: Block of utf8 bytes. Note, mutltibyte characters may be split across adjoining memo_glob messages.
     *
     * @param index of data
     * @param data The new data value to be set
     */
    public void setData(int index, Short data) {
        setFieldValue(4, index, data, Fit.SUBFIELD_INDEX_MAIN_FIELD);
    }

}

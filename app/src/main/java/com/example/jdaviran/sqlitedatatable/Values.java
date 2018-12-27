package com.example.jdaviran.sqlitedatatable;

public class Values {
    int val_id;
    Boolean val_check;
    String val_content;

    public Values(int val_id, Boolean val_check, String val_content) {
        this.val_id = val_id;
        this.val_check = val_check;
        this.val_content = val_content;
    }

    public int getVal_id() {
        return val_id;
    }

    public void setVal_id(int val_id) {
        this.val_id = val_id;
    }

    public Boolean getVal_check() {
        return val_check;
    }

    public void setVal_check(Boolean val_check) {
        this.val_check = val_check;
    }

    public String getVal_content() {
        return val_content;
    }

    public void setVal_content(String val_content) {
        this.val_content = val_content;
    }
}

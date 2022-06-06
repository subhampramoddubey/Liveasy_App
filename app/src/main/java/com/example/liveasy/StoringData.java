package com.example.liveasy;

public class StoringData {
    String name,mobilenumber,password;

    public StoringData(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }


    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }


}
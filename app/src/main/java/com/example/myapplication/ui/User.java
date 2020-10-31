package com.example.myapplication.ui;

import android.database.Cursor;

import com.example.myapplication.DatabaseHelperUser;

import java.util.Date;

public class User {
    int bal=0;
    String username;
    String fullName;
    Date sessionExpiryDate;
    DatabaseHelperUser db= new DatabaseHelperUser(BaseApplication.getContext());
    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public int getBal()
    {
        return bal;
    }
    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }
}



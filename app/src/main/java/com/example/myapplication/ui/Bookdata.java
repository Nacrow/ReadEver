package com.example.myapplication.ui;

import android.database.Cursor;

import com.example.myapplication.DatabaseHelperUser;

public class Bookdata {

    DatabaseHelperUser h=new DatabaseHelperUser(BaseApplication.getContext());
    public Cursor getData(String username) {
        int a = 0;
        Cursor s;
            s = h.inquery_order(username);
            return s;
    }
}

package com.example.myapplication.ui;

import android.content.ContextWrapper;
import android.database.Cursor;

import com.example.myapplication.DatabaseHelperUser;
import com.example.myapplication.R;

import androidx.annotation.Nullable;

public class SearchBookdata {

    DatabaseHelperUser h=new DatabaseHelperUser(BaseApplication.getContext());
    public String[] getTitle() {
        int a = 0;
        Cursor s;
            s = h.allbook();
            String[] result;
            s.moveToNext();
            result = new String[s.getCount()];
            if (s != null) {
                s.moveToFirst();
                result[a] = s.getString(s.getColumnIndex("BOOKNAME"));
                a++;
                while (s.moveToNext()) {
                    result[a] = s.getString(s.getColumnIndex("BOOKNAME"));
                    a++;
                }
            }
            return result;
    }


    public String[] getAuthor() {
        int a = 0;
        Cursor s = h.allbook();
        String[] result;
        s.moveToNext();
        result = new String[s.getCount()];
        if (s!= null) {
            s.moveToFirst();
            result[a] = s.getString(s.getColumnIndex("AUTHOR"));
            a++;
            while (s.moveToNext()) {
                result[a] = s.getString(s.getColumnIndex("AUTHOR"));
                a++;
            }
        }
        return result;
    }

    public int[] picturePath() {
        int a = 0;
        Cursor s = h.allbook();
        int[] result;
        s.moveToNext();
        result = new int[s.getCount()];
        if (s!= null) {
            s.moveToFirst();
            result[a] = s.getInt(s.getColumnIndex("IMG"));
            a++;
            while (s.moveToNext()) {
                result[a] = s.getInt(s.getColumnIndex("IMG"));
                a++;
            }
        }
        return result;
    }
    public String[] getDes() {
        int a = 0;
        Cursor s = h.allbook();
        String[] result;
        s.moveToNext();
        result = new String[s.getCount()];
        if (s!= null) {
            s.moveToFirst();
            result[a] = s.getString(s.getColumnIndex("DES"));
            a++;
            while (s.moveToNext()) {
                result[a] = s.getString(s.getColumnIndex("DES"));
                a++;
            }
        }
        return result;
    }
    public String[] getType() {
        int a = 0;
        Cursor s = h.allbook();
        String[] result;
        s.moveToNext();
        result = new String[s.getCount()];
        if (s!= null) {
            s.moveToFirst();
            result[a] = s.getString(s.getColumnIndex("BOOKTYPE"));
            a++;
            while (s.moveToNext()) {
                result[a] = s.getString(s.getColumnIndex("BOOKTYPE"));
                a++;
            }
        }
        return result;
    }
    public int[] getPrice() {
        int a = 0;
        Cursor s = h.allbook();
        int[] result;
        s.moveToNext();
        result = new int[s.getCount()];
        if (s!= null) {
            s.moveToFirst();
            result[a] = s.getInt(s.getColumnIndex("PRICE"));
            a++;
            while (s.moveToNext()) {
                result[a] = s.getInt(s.getColumnIndex("PRICE"));
                a++;
            }
        }
        return result;
    }
}

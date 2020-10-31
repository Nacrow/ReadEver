package com.example.myapplication.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.Column;
import com.bin.david.form.data.format.draw.IDrawFormat;
import com.bin.david.form.data.table.ArrayTableData;
import com.bin.david.form.data.table.TableData;
import com.example.myapplication.DatabaseHelperUser;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fragment_order extends Fragment {
    private final int H = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int W = ViewGroup.LayoutParams.MATCH_PARENT;
    private TableLayout tab;
    private TableLayout tab2;
    private ArrayList<String> tabCol = new ArrayList<>();
    private ArrayList<String> tabH = new ArrayList<>();
    private ArrayList<String> tabCol2 = new ArrayList<>();
    private ArrayList<String> tabH2 = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.order_fragment, container, false);
        DatabaseHelperUser db = new DatabaseHelperUser(getContext());
        SessionHandler session = new SessionHandler(getContext());
        User user = session.getUserDetails();
        Cursor res=db.orderquery(user.getUsername());
        String[] result;
        res.moveToNext();
        int count=res.getCount();
        res.moveToFirst();

        tab = root.findViewById(R.id.tab_01);
        //控制行數
        TableRow tabRow = new TableRow(getContext());
            //控制列數
        TextView tv = new TextView(getContext());
        tv.setText("Book Title");
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.parseColor("#707070"));
        tv.setPadding(20,20,20,20);
        tv.setTextSize(16);
        tabRow.addView(tv);
        TextView tv2 = new TextView(getContext());
        tv2.setText("Date");
        tv2.setGravity(Gravity.CENTER);
        tv2.setBackgroundColor(Color.parseColor("#707070"));
        tv2.setPadding(20,20,20,20);
        tv2.setTextSize(16);
        tabRow.addView(tv2);
        TextView tv3 = new TextView(getContext());
        tv3.setText("Price");
        tv3.setGravity(Gravity.CENTER);
        tv3.setBackgroundColor(Color.parseColor("#707070"));
        tv3.setPadding(20,20,20,20);
        tv3.setTextSize(16);
        tabRow.addView(tv3);
        tab.addView(tabRow,new TableLayout.LayoutParams(W,H));
        for (int row = 0; row < count; row++) {
            tabRow = new TableRow(getContext());
            //控制列數
            for (int col = 0 ; col<3; col++){
                TextView tv4 = new TextView(getContext());
                tv4.setText(res.getString(col+2));
                tv4.setGravity(Gravity.CENTER);
                tv4.setBackgroundResource(R.drawable.cell_shape);
                tv4.setPadding(20,20,20,20);
                tv4.setTextSize(16);
                tabRow.addView(tv4);
            }
            res.moveToNext();
            tab.addView(tabRow,new TableLayout.LayoutParams(W,H));
        }
        Cursor com=db.get_comment_user(user.getFullName());
        String[] comment;
        com.moveToNext();
        int count2=com.getCount();
        com.moveToFirst();

        tab2 = root.findViewById(R.id.tab_02);
        //控制行數
        TableRow tabRow2 = new TableRow(getContext());
        //控制列數
        TextView tw = new TextView(getContext());
        tw.setText("Book Title");
        tw.setGravity(Gravity.CENTER);
        tw.setBackgroundColor(Color.parseColor("#707070"));
        tw.setPadding(20,20,20,20);
        tw.setTextSize(16);
        tabRow2.addView(tw);
        TextView tw2 = new TextView(getContext());
        tw2.setText("Content");
        tw2.setGravity(Gravity.CENTER);
        tw2.setBackgroundColor(Color.parseColor("#707070"));
        tw2.setPadding(20,20,20,20);
        tw2.setTextSize(16);
        tabRow2.addView(tw2);
        TextView tw22 = new TextView(getContext());
        tw22.setText("Author");
        tw22.setGravity(Gravity.CENTER);
        tw22.setBackgroundColor(Color.parseColor("#707070"));
        tw22.setPadding(20,20,20,20);
        tw22.setTextSize(16);
        tabRow2.addView(tw22);
        TextView tw3 = new TextView(getContext());
        tw3.setText("Rate");
        tw3.setGravity(Gravity.CENTER);
        tw3.setBackgroundColor(Color.parseColor("#707070"));
        tw3.setPadding(20,20,20,20);
        tw3.setTextSize(16);
        tabRow2.addView(tw3);
        TextView tww3 = new TextView(getContext());
        tww3.setText("DATE");
        tww3.setGravity(Gravity.CENTER);
        tww3.setBackgroundColor(Color.parseColor("#707070"));
        tww3.setPadding(20,20,20,20);
        tww3.setTextSize(16);
        tabRow2.addView(tww3);
        tab2.addView(tabRow2,new TableLayout.LayoutParams(W,H));
        for (int row = 0; row < count2; row++) {
            tabRow2 = new TableRow(getContext());
            //控制列數
            for (int col = 0 ; col<5; col++){
                TextView tw4 = new TextView(getContext());
                tw4.setText(com.getString(col+1));
                tw4.setGravity(Gravity.CENTER);
                tw4.setBackgroundResource(R.drawable.cell_shape);
                tw4.setPadding(20,20,20,20);
                tw4.setTextSize(16);
                tabRow2.addView(tw4);
            }
            com.moveToNext();
            tab2.addView(tabRow2,new TableLayout.LayoutParams(W,H));
        }
        TextView pho=root.findViewById(R.id.phone);
        pho.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("01162132441");
            }
        });
        return root;
    }
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

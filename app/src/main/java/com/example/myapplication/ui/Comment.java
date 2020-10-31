package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;

public class Comment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String bookname=null;
        //从Intent当中根据key取得value
        if (intent != null) {
            bookname = intent.getStringExtra("bookname");
        }
        setContentView(R.layout.activity_comment);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView_comment);
        ListAdaComment listAdapter = new ListAdaComment();
        listAdapter.getbookname(bookname);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }
}
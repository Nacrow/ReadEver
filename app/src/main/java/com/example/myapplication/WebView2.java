package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebView2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);
        Intent intent = getIntent();
        String value = null;
        if (intent != null) {
            value = intent.getStringExtra("url");
        }
        WebView web;
        web=findViewById(R.id.webview2);
        web.loadUrl(value);
    }
}
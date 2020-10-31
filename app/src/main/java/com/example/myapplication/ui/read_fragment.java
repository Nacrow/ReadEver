package com.example.myapplication.ui;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import com.bifan.txtreaderlib.bean.TxtMsg;
import com.bifan.txtreaderlib.interfaces.ILoadListener;
import com.example.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;

public class read_fragment extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_fragment);
        com.bifan.txtreaderlib.main.TxtReaderView mTxtReaderView;
        mTxtReaderView=findViewById(R.id.activity_hwtxtplay_readerView);
        CopyFileFromAssets copy=new CopyFileFromAssets();
        AssetManager mngr = getApplicationContext().getAssets();
        Intent intent = getIntent();
        String url=null;
        //从Intent当中根据key取得value
        if (intent != null) {
            url = intent.getStringExtra("url");
        }
        InputStream is=null;
        try {
            is =mngr.open(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = new byte[is.available()];
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = new String(bytes);
        TextView con=findViewById(R.id.content);
        con.setText(str);
        //copy.copy2(getApplicationContext(),"Alice.txt",getApplicationContext().getFilesDir().getAbsolutePath() ,"Alice.txt");
        //String Filepath=getApplicationContext().getFilesDir().getPath()+"/Alice.txt";
        //mTxtReaderView.loadTxtFile(Filepath, new ILoadListener() {
    }
}

package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DatabaseHelperUser;
import com.example.myapplication.R;

public class creditActivity extends AppCompatActivity {

    EditText input;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        final DatabaseHelperUser db=new DatabaseHelperUser(getApplicationContext());
        final SessionHandler session = new SessionHandler(getApplicationContext());
        final User user = session.getUserDetails();
        submit=findViewById(R.id.submit_charge);
        input=findViewById(R.id.charge);
        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int charge=0-Integer.parseInt(input.getText().toString());
                String result=db.changeBalance(user.getUsername(),charge);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.DatabaseHelperUser;
import com.example.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

public class PwdActivity extends AppCompatActivity {
    private SessionHandler session;

    EditText opwd;
    EditText npwd;
    EditText npwd2;
    String oldpwd;
    String newpwd;
    String newpwd2;
    Button submit;
    DatabaseHelperUser db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db= new DatabaseHelperUser(getApplicationContext());
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());
        setContentView(R.layout.activity_modifypwd);
        SessionHandler session = new SessionHandler(getApplicationContext());
        final User user = session.getUserDetails();
        submit=findViewById(R.id.bt_pwd_submit);
        opwd=findViewById(R.id.old_pwd);
        npwd=findViewById(R.id.new_pwd);
        npwd2=findViewById(R.id.new_pwd_2);
        ImageButton back=findViewById(R.id.ib_navigation_back);
        back.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldpwd=opwd.getText().toString().trim();
                newpwd=npwd.getText().toString().trim();
                newpwd2=npwd2.getText().toString().trim();
                if (!newpwd.equals(newpwd2))
                {
                    npwd2.setError("Password and Confirm Password does not match");
                    npwd2.requestFocus();
                }
                else {
                    if (db.loginquery(user.getUsername(),oldpwd).getCount()>0) {
                        if (db.Updatepwd(user.getUsername(), newpwd)) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        npwd2.setError("Wrong password");
                        npwd2.requestFocus();
                    }
                }
            }
        });

    }
}

package com.example.myapplication.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.DatabaseHelperUser;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMPTY = "";
    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    String error;
    int a;
    //private String register_url = "http://192.168.0.167:8081/user/db/register.php";
    private SessionHandler session;
    EditText userna;
    //int a;
    EditText fulln;
    EditText pwd;
    EditText pwd2;
    DatabaseHelperUser mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        session = new SessionHandler(getApplicationContext());
        userna=findViewById(R.id.et_register_username);
        pwd=findViewById(R.id.et_register_pwd_input);
        pwd2=findViewById(R.id.et_register_pwd_input2);
        fulln=findViewById(R.id.et_register_fullname);
        findViewById(R.id.ib_navigation_back).setOnClickListener(this);
        findViewById(R.id.bt_register_submit).setOnClickListener(this);
        mydb=new DatabaseHelperUser(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_navigation_back:
            finish();
            break;
            case R.id.bt_register_submit: {
                username = userna.getText().toString().toLowerCase().trim();
                password = pwd.getText().toString().trim();
                confirmPassword = pwd2.getText().toString().trim();
                fullName = fulln.getText().toString().trim();
                if (validateInputs()) {
                    register();
                }
                break;
            }
        }
    }

    private void register() {

        if (mydb.inquery(username).getCount()>0)
        {
            Toast.makeText(RegisterActivity.this,"Username exist!",Toast.LENGTH_SHORT).show();
        }
        else {
            boolean isInserted = mydb.insertData(username, fullName, password);
            if (isInserted = true) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                session.loginUser(username, fullName);
            } else {
                Toast.makeText(RegisterActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        }
        /*
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);
            request.put(KEY_FULL_NAME, fullName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, register_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            a= response.getInt(KEY_STATUS);
                            //Check if user got registered successfully
                            if (response.getInt(KEY_STATUS) == 0) {
                                //Set the user session
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                session.loginUser(username,fullName);
                                error=response.getString(KEY_MESSAGE);

                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing
                                userna.setError("Username already taken!");
                                userna.requestFocus();

                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString(KEY_STATUS), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
        */

    }
    private boolean validateInputs() {
        if (KEY_EMPTY.equals(fullName)) {
            fulln.setError("Full Name cannot be empty");
            fulln.requestFocus();
            return false;

        }
        if (KEY_EMPTY.equals(username)) {
            userna.setError("Username cannot be empty");
            userna.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(password)) {
            pwd.setError("Password cannot be empty");
            pwd.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(confirmPassword)) {
            pwd2.setError("Confirm Password cannot be empty");
            pwd2.requestFocus();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            pwd2.setError("Password and Confirm Password does not match");
            pwd2.requestFocus();
            return false;
        }

        return true;
    }
}
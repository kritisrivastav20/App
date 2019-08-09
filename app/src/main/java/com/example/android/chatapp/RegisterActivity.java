package com.example.android.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    EditText phone, password, email;
    Button signup;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {

        phone = (EditText) findViewById(R.id.et_phone1);
        password = (EditText) findViewById(R.id.et_password1);
        signup = (Button) findViewById(R.id.signup);
        login = (TextView) findViewById(R.id.hint_login);
        email = (EditText) findViewById(R.id.et_email);
    }

    public void goToLogin(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
}

package com.example.android.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseUser currentuser;
    EditText phone, password;
    Button login;
    TextView register, forgotPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {

        phone = (EditText) findViewById(R.id.et_phone);
        password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.button);
        register = (TextView) findViewById(R.id.textView4);
        forgotPass = (TextView) findViewById(R.id.forgot);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(currentuser != null) {
            sendUsertoChatActivity();
        }
    }

    private void sendUsertoChatActivity() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
    }

    public void sendUsertoRegisterActivity(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}

package com.example.android.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseUser currentuser;
    private FirebaseAuth auth;
    private ProgressDialog loadingBar;
    EditText email, password;
    Button login;
    TextView register, forgotPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        currentuser = auth.getCurrentUser();
        init();
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserLogin();
            }
        });
    }

    private void getUserLogin() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(!isValidUser(Email)) {
            Toast.makeText(this, "Invalid phone/Email",
                    Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please enter the password",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Sign in");
            loadingBar.setMessage("Please Wait!");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            auth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                sendUsertoChatActivity();
                                Toast.makeText(LoginActivity.this, "Logged in successfully..",
                                        Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else {
                                loadingBar.dismiss();
                                String error = task.getException().toString();
                                if(error.contains("no user record")) {
                                    Toast.makeText(LoginActivity.this, "User does not exist",
                                            Toast.LENGTH_SHORT).show();
                                }
                                if(error.contains("password is invalid")) {
                                    Toast.makeText(LoginActivity.this, "Password is incorrect",
                                            Toast.LENGTH_SHORT).show();
                                }
                                Log.e("TAG", error);
                            }
                        }
                    });
        }
    }

    private void init() {

        email = (EditText) findViewById(R.id.et_phone);
        password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.button);
        register = (TextView) findViewById(R.id.textView4);
        forgotPass = (TextView) findViewById(R.id.forgot);
        loadingBar = new ProgressDialog(this);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(currentuser != null) {
//            sendUsertoChatActivity();
//        }
//    }

    private boolean isValidUser(String emailId) {
        String phonePattern = "(0/91)?[7-9][0-9]{9}";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        if (phone.matches(phonePattern) && phone.length() > 2) {
//            return true;
//        }
        if (emailId.matches(emailPattern) && emailId.length() > 0) {
            return true;
        }
        return false;
    }

    private void sendUsertoChatActivity() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
    }

    public void sendUsertoRegisterActivity(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}

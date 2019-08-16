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

public class RegisterActivity extends AppCompatActivity {
    EditText confirmPass, password, email;
    Button signup;
    TextView login;
    private FirebaseAuth auth;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        init();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccount();
            }
        });
    }

    private void createNewAccount() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String ConfirmPass = confirmPass.getText().toString().trim();

        if(!isValidUser(Email)) {
            Toast.makeText(this, "Invalid phone/Email",
                    Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please enter the password",
                    Toast.LENGTH_SHORT).show();
        }
        if(!Password.matches(ConfirmPass)) {
            Toast.makeText(this, "Password is incorrect",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Creating account");
            loadingBar.setMessage("Please Wait!");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            auth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            loadingBar.dismiss();
                            Toast.makeText(RegisterActivity.this, "Account Created Successfully..",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                        }
                        else {
                            loadingBar.dismiss();
                            String error = task.getException().toString();
                            if(error.contains("already in use")) {
                                Toast.makeText(RegisterActivity.this, "Email already exists",
                                        Toast.LENGTH_SHORT).show();
                            }
                            Log.e("TAG", error);
                        }
                }
            });
        }
    }

    private void init() {

        password = (EditText) findViewById(R.id.et_email);
        confirmPass = (EditText) findViewById(R.id.et_password1);
        signup = (Button) findViewById(R.id.signup);
        login = (TextView) findViewById(R.id.hint_login);
        email = (EditText) findViewById(R.id.et_phone1);
        loadingBar = new ProgressDialog(this);
    }

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

    public void goToLogin(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
}

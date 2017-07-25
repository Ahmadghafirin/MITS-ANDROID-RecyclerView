package com.example.ahmad.w.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ahmad.w.R;
import com.example.ahmad.w.SessionManager;
import com.example.ahmad.w.database.DataBaseHandler;
import com.example.ahmad.w.user.DashboardActivity;
import com.example.ahmad.w.user.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etGender, etPassword;
    public static final String TAG = "TagMainActivity";
    private SessionManager sessionManager;
    private DataBaseHandler tableUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = SessionManager.getInstance();
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate is Called");

        tableUser = DataBaseHandler.getInstance();

        etName = (EditText) findViewById(R.id.et_name_register);
        etEmail = (EditText) findViewById(R.id.et_email_register);
        etPhone = (EditText) findViewById(R.id.et_phone_register);
        etGender = (EditText) findViewById(R.id.et_gender_register);
        etPassword = (EditText) findViewById(R.id.et_password_register);
    }

    public void submitSave(View view) {
        String name, email, phone, gender, pass;

        name = etName.getText().toString();
        email = etEmail.getText().toString();
        phone = etPhone.getText().toString();
        gender = etGender.getText().toString();
        pass = etPassword.getText().toString();

        if (email.isEmpty()) {
            etEmail.setError("Email cannot be blank");
            etEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Email is not valid");
            etEmail.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            etPassword.setError("Password cannot be blank");
            etPassword.requestFocus();
            return;
        }

        if (pass.length() < 8) {
            etPassword.setError("Password must have 8 characters.");
            etPassword.requestFocus();
            return;
        }
        tableUser.addUser(new User(name, email, phone, gender, pass));
        sessionManager.setLogin(email, pass);

        Intent intent = new Intent(this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}

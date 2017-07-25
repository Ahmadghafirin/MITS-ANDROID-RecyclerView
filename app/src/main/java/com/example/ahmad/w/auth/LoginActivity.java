package com.example.ahmad.w.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmad.w.R;
import com.example.ahmad.w.SessionManager;
import com.example.ahmad.w.database.DataBaseHandler;
import com.example.ahmad.w.user.DashboardActivity;
import com.example.ahmad.w.user.User;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPass;
    private SessionManager sessionManager;
    private DataBaseHandler tableUser;
    public static final int REQUEST_REGISTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = SessionManager.getInstance();
        if (sessionManager.isLoggedIn()) {
            openDashboard();
        }
        setContentView(R.layout.activity_login);

        tableUser = new DataBaseHandler(this);
        etEmail = (EditText) findViewById(R.id.et_emailLogin);
        etPass = (EditText) findViewById(R.id.et_passwordLogin);
    }

    public void submitLogin(View view) {
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

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
            etPass.setError("Password cannot be blank");
            etPass.requestFocus();

            return;
        }

        if (pass.length() < 8) {
            etPass.setError("Password must have 8 characters.");
            etPass.requestFocus();

            return;
        }

        if (tableUser.checkUser(email, pass)) {
            sessionManager.setLogin(email, pass);
            openDashboard();
        } else Toast.makeText(this, "Email or password is invalid", Toast.LENGTH_SHORT).show();
    }

    public void submitSingUp(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, REQUEST_REGISTER);
    }

    public void openDashboard() {
        Intent data = new Intent(this, DashboardActivity.class);
        startActivity(data);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == REQUEST_REGISTER) {
            openDashboard();
        }
    }
}

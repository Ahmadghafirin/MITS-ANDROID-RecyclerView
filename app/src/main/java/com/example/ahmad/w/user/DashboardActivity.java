package com.example.ahmad.w.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ahmad.w.menu.MenuFragment;
import com.example.ahmad.w.R;
import com.example.ahmad.w.SessionManager;
import com.example.ahmad.w.auth.LoginActivity;
import com.example.ahmad.w.model.User;

public class DashboardActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = SessionManager.getInstance();
        setContentView(R.layout.activity_dashboard);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String result = null;
        TextView tvUser = (TextView) findViewById(R.id.tv_user);

        String sessionEmail = sessionManager.getEmail();
        String sessionPass = sessionManager.getPassword();


        User user = User.getUserLogin(sessionEmail, sessionPass);
        result = "Name : " + user.getName() + "\nEmail : " + user.getEmail()
                + "\nPhone :" + user.getPhone() + "\nGender : " + user.getGender();
        tvUser.setText(result);
    }

    public void submitLogOut(View view) {
        sessionManager.clear();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void submitShowMenu(View view) {
        startActivity(new Intent(this, MenuFragment.class));
    }
}

package com.rdacompany.rdasportsandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rdacompany.rdasportsandroid.R;
import com.rdacompany.rdasportsandroid.User;

public class LogInView extends AppCompatActivity {

    private User user;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        Intent intent = getIntent();
        user = User.valueOf(intent.getStringExtra("USER"));
        tvRegister = findViewById(R.id.tv_register);

        if (user == User.EMPLOYEE) {
            tvRegister.setVisibility(View.GONE);
        }
    }

    public void register(View view){
        Intent intent = new Intent(this, RegisterView.class);
        startActivity(intent);
    }

    public void areas (View view) {
        if (user == User.CUSTOMER){
            Intent intent = new Intent(this, CustomerAreaView.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AddSesionView.class);
            startActivity(intent);
        }
    }
}
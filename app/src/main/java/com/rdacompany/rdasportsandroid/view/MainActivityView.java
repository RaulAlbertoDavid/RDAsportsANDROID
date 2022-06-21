package com.rdacompany.rdasportsandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rdacompany.rdasportsandroid.R;
import com.rdacompany.rdasportsandroid.User;

public class MainActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void customers(View view){
        Intent intent = new Intent(this, LogInView.class);
        intent.putExtra("USER", User.CUSTOMER.toString());
        startActivity(intent);
    }

    public void employees(View view){
        Intent intent = new Intent(this, LogInView.class);
        intent.putExtra("USER", User.EMPLOYEE.toString());
        startActivity(intent);
    }
}
package com.rdacompany.rdasportsandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rdacompany.rdasportsandroid.R;
import com.rdacompany.rdasportsandroid.User;
import com.rdacompany.rdasportsandroid.contract.LogInContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.presenter.LogInPresenter;

public class LogInView extends AppCompatActivity implements LogInContract.View {

    private User user;
    private TextView tvRegister;
    private EditText email;
    private EditText password;
    private LogInPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        presenter = new LogInPresenter(this);
        Intent intent = getIntent();
        user = User.valueOf(intent.getStringExtra("USER"));
        tvRegister = findViewById(R.id.tv_register);
        email = findViewById(R.id.email_log);
        password = findViewById(R.id.password_log);

        if (user == User.EMPLOYEE) {
            tvRegister.setVisibility(View.GONE);
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, AddCustomerView.class);
        startActivity(intent);
    }

    public void logIn(View view) {

        presenter.logIn(email.getText().toString(), password.getText().toString(), user);
    }

    @Override
    public void launchIntentCustomer(Customer customer) {
        Intent intent = new Intent(this, ListSessionView.class);
        intent.putExtra("customer", customer);
        startActivity(intent);
    }

    @Override
    public void launchIntentEmployee() {
        Intent intent = new Intent(this, AddSessionView.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
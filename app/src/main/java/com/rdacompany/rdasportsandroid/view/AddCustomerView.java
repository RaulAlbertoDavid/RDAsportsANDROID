package com.rdacompany.rdasportsandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rdacompany.rdasportsandroid.R;
import com.rdacompany.rdasportsandroid.contract.AddCustomerContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.presenter.AddCustomerPresenter;
import com.rdacompany.rdasportsandroid.utils.DatePickerFragment;

public class AddCustomerView extends AppCompatActivity implements AddCustomerContract.View {

    private AddCustomerPresenter presenter;
    private EditText date;
    private EditText email;
    private EditText name;
    private EditText phone;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new AddCustomerPresenter(this);
        setContentView(R.layout.activity_add_customer_view);
        date = findViewById(R.id.birth_date);
        email = findViewById(R.id.email_log);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            String stringMonth = String.valueOf(month + 1);
            String stringDay = String.valueOf(day);

            if ((month + 1) < 10) {
                stringMonth = "0" + stringMonth;
            }
            if (day < 10){
                stringDay = "0" + stringDay;
            }

            final String selectedDate = stringDay + "-" + stringMonth + "-" + year;
            date.setText(selectedDate);
        });


        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void clickDate(View view) {
        if (view.getId() == R.id.birth_date) {
            showDatePickerDialog();
        }
    }


    @Override
    public void addCustomer(View view) {
        if (email.getText().toString().equals("") || name.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Debes introducir el email, password y nombre", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Customer customer = new Customer();
            customer.setName(name.getText().toString());
            customer.setEmail(email.getText().toString());
            customer.setPassword(password.getText().toString());

            customer.setBirthDate(date.getText().toString());
            customer.setPhone(phone.getText().toString());
            presenter.addCustomer(customer);
            System.out.println(customer);
        }
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
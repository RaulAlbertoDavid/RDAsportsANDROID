package com.rdacompany.rdasportsandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rdacompany.rdasportsandroid.R;
import com.rdacompany.rdasportsandroid.utils.DatePickerFragment;

public class AddSesionView extends AppCompatActivity {

    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sesion_view);

        date = findViewById(R.id.session_date);
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            final String selectedDate = day + " / " + (month + 1) + " / " + year;
            date.setText(selectedDate);
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void clickDate(View view) {
        if (view.getId() == R.id.session_date) {
            showDatePickerDialog();
        }
    }
}
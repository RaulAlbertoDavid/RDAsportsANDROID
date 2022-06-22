package com.rdacompany.rdasportsandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rdacompany.rdasportsandroid.R;
import com.rdacompany.rdasportsandroid.contract.AddSessionContract;
import com.rdacompany.rdasportsandroid.domain.Activity;
import com.rdacompany.rdasportsandroid.domain.Area;
import com.rdacompany.rdasportsandroid.domain.Employee;
import com.rdacompany.rdasportsandroid.domain.Session;
import com.rdacompany.rdasportsandroid.domain.dto.SessionDto;
import com.rdacompany.rdasportsandroid.presenter.AddSessionPresenter;
import com.rdacompany.rdasportsandroid.utils.DatePickerFragment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AddSessionView extends AppCompatActivity implements AddSessionContract.View {

    private EditText date;
    private EditText hour;
    private EditText duration;
    private EditText capacity;
    private EditText level;
    private Spinner spinnerEmplo;
    private Spinner spinnerAct;
    private Spinner spinnerArea;
    private AddSessionPresenter presenter;
    private List<Area> areaList;
    private List<Employee> employeeList;
    private List<Activity> activityList;
    private ArrayAdapter adpEmplo;
    private ArrayAdapter adpAct;
    private ArrayAdapter adpArea;
    private Employee employee;
    private Activity activity;
    private Area area;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sesion_view);
        presenter = new AddSessionPresenter(this);
        areaList = new ArrayList<>();
        employeeList = new ArrayList<>();
        activityList = new ArrayList<>();

        date = findViewById(R.id.tf_date);
        hour = findViewById(R.id.tf_hour);
        duration = findViewById(R.id.tf_duration);
        capacity = findViewById(R.id.tf_capacity);
        level = findViewById(R.id.tf_level);
        spinnerEmplo = findViewById(R.id.spinner_employee);
        spinnerAct = findViewById(R.id.spinner_activity);
        spinnerArea = findViewById(R.id.spinner_area);

        adpEmplo = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, employeeList);
        adpAct = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, activityList);
        adpArea = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, areaList);

        presenter.loadAreas();
        presenter.loadEmployees();
        presenter.loadActivities();
    }


    @Override
    public void addSession(View view) {
        SessionDto session = new SessionDto();
        session.setEmployee(employee.getEmployeeId());
        session.setActivity(activity.getActivityId());
        session.setArea(area.getAreaId());
        session.setDateTime(date.getText().toString() + " " + hour.getText().toString());
        session.setDuration(Integer.parseInt(duration.getText().toString()));
        session.setCapacity(Integer.parseInt(capacity.getText().toString()));
        session.setLevel(Integer.parseInt(level.getText().toString()));
        presenter.addSession(session);
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmployees(List<Employee> employees) {
        employeeList.addAll(employees);

        spinnerEmplo.setAdapter(adpEmplo);
        spinnerEmplo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                employee = (Employee) spinnerEmplo.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void showActivities(List<Activity> activities) {
        activityList.addAll(activities);

        spinnerAct.setAdapter(adpAct);
        spinnerAct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                activity = (Activity) spinnerAct.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void showAreas(List<Area> areas) {
        areaList.addAll(areas);

        spinnerArea.setAdapter(adpArea);
        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                area = (Area) spinnerArea.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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

            final String selectedDate = year + "-" + stringMonth + "-" + stringDay;
            date.setText(selectedDate);
        });


        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void clickDate(View view) {
        if (view.getId() == R.id.tf_date) {
            showDatePickerDialog();
        }
    }
}
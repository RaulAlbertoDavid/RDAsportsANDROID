package com.rdacompany.rdasportsandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.rdacompany.rdasportsandroid.R;
import com.rdacompany.rdasportsandroid.contract.SessionDetailContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.domain.Session;
import com.rdacompany.rdasportsandroid.presenter.DetailSessionPresenter;


public class DetailSessionView extends AppCompatActivity implements SessionDetailContract.View {

    private DetailSessionPresenter presenter;
    private Customer customer;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_session);

        presenter = new DetailSessionPresenter(this);

        Intent intent = getIntent();
        int sessionId = intent.getIntExtra("sessionId", 0);
        customer = (Customer) intent.getSerializableExtra("customer");
        presenter.sessionDetail(sessionId);

    }

    @Override
    public void loadSessionDetail(Session session) {
        TextView employeeTv = findViewById(R.id.employee_view);
        TextView activityTv = findViewById(R.id.activity_view);
        TextView areaTv = findViewById(R.id.area_view);
        TextView dateTv = findViewById(R.id.date_time_view);
        TextView durTv = findViewById(R.id.dur_view);
        TextView levelTv = findViewById(R.id.level_view);
        TextView customerTv = findViewById(R.id.customer_view);
        Button inscribete = findViewById(R.id.inscribete_button);

        employeeTv.setText("Monitor: " + session.getEmployee().toString());
        activityTv.setText("Actividad: " + session.getActivity().toString());
        areaTv.setText("Area: " + session.getArea().toString());
        dateTv.setText("Inicio: " + session.getDateTime());
        durTv.setText("Duracion: " + String.valueOf(session.getDuration()));
        customerTv.setText("Inscritos: " + session.getCustomers().size() + "/" + String.valueOf(session.getCapacity()));
        levelTv.setText("Nivel: " + String.valueOf(session.getLevel()));
        this.session = session;
        if (!session.getCustomers().contains(customer)) {
            inscribete.setText("INSCRIBETE");
        } else {
            inscribete.setText("DESAPUNTATE");
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void inscribete(View view) {

        if (!session.getCustomers().contains(customer)) {
            presenter.book(customer, session);
            finish();
        } else {
            presenter.dropOut(customer, session);
            finish();
        }
    }
}
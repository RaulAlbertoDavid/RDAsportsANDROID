package com.rdacompany.rdasportsandroid.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rdacompany.rdasportsandroid.R;
import com.rdacompany.rdasportsandroid.contract.ListSessionContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.domain.Session;
import com.rdacompany.rdasportsandroid.presenter.ListSessionPresenter;

import java.util.ArrayList;
import java.util.List;

public class ListSessionView extends AppCompatActivity implements ListSessionContract.View, AdapterView.OnItemClickListener {

    private List<Session> sessionList;
    private ArrayAdapter<Session> sessionArrayAdapter;
    private ListSessionPresenter presenter;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_session_view);

        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("customer");

        presenter = new ListSessionPresenter(this);
        initialize();
    }

    private void initialize() {
        sessionList = new ArrayList<>();
        ListView lvSessions = findViewById(R.id.session_list);
        sessionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sessionList);
        lvSessions.setAdapter(sessionArrayAdapter);
        registerForContextMenu(lvSessions);

        lvSessions.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllSessions(customer);

    }

    @Override
    public void listAllSessions(List<Session> sessions) {
        sessionList.clear();
        sessionList.addAll(sessions);
        sessionArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Session session = sessionList.get(position);

        Intent intentDetail = new Intent(this, DetailSessionView.class);
        intentDetail.putExtra("customer", customer);
        intentDetail.putExtra("sessionId", session.getSessionId());
        startActivity(intentDetail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preferences, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, PreferenceView.class);
        startActivity(intent);
        return true;
    }
}
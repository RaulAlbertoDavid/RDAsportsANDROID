package com.rdacompany.rdasportsandroid.model;

import com.rdacompany.rdasportsandroid.api.RdaSportsApi;
import com.rdacompany.rdasportsandroid.api.RdaSportsApiInterface;
import com.rdacompany.rdasportsandroid.contract.AddSessionContract;
import com.rdacompany.rdasportsandroid.domain.Activity;
import com.rdacompany.rdasportsandroid.domain.Area;
import com.rdacompany.rdasportsandroid.domain.Employee;
import com.rdacompany.rdasportsandroid.domain.Session;
import com.rdacompany.rdasportsandroid.domain.dto.SessionDto;
import com.rdacompany.rdasportsandroid.presenter.AddSessionPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSessionModel implements AddSessionContract.Model {

    private AddSessionPresenter presenter;
    private RdaSportsApiInterface api;

    public AddSessionModel(AddSessionPresenter presenter) {
        this.presenter = presenter;
        api = RdaSportsApi.buildInstance();
    }

    @Override
    public void addSession(SessionDto session, OnAddSessionListener listener) {
        Call<Session> call = api.addSession(session);
        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                listener.onAddSessionSuccess("Sesion añadida");
            }

            @Override
            public void onFailure(Call<Session> call, Throwable throwable) {
                listener.onAddSessionError("Fallo en la conexion");
            }
        });
    }

    @Override
    public void loadEmployees() {
        Call<List<Employee>> call = api.getEmployees();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                presenter.showEmployees(response.body());
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable throwable) {
                presenter.onAddSessionError("Error al conectar");
            }
        });
    }

    @Override
    public void loadActivities() {
        Call<List<Activity>> call = api.getActivities();
        call.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                presenter.showActivities(response.body());
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable throwable) {
                presenter.onAddSessionError("Error al conectar");
            }
        });
    }

    @Override
    public void loadAreas() {
        Call<List<Area>> call = api.getAreas();
        call.enqueue(new Callback<List<Area>>() {
            @Override
            public void onResponse(Call<List<Area>> call, Response<List<Area>> response) {
                presenter.showAreas(response.body());
            }

            @Override
            public void onFailure(Call<List<Area>> call, Throwable throwable) {
                presenter.onAddSessionError("Error al conectar");
            }
        });
    }
}

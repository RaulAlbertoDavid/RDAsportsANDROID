package com.rdacompany.rdasportsandroid.model;

import android.content.Context;

import com.rdacompany.rdasportsandroid.api.RdaSportsApi;
import com.rdacompany.rdasportsandroid.api.RdaSportsApiInterface;
import com.rdacompany.rdasportsandroid.contract.SessionDetailContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.domain.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSessionModel implements SessionDetailContract.Model {

    private Context context;
    private RdaSportsApiInterface api;

    public DetailSessionModel(Context context) {
        this.context = context;

        api = RdaSportsApi.buildInstance();
    }



    @Override
    public void book(Customer customer, Session session, OnBooklistener listener) {
        Call<Void> call = api.relacion(customer.getCustomerId(), session.getSessionId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onBookSuccess("Registrado correctamente");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                listener.onBookError("Fallo en la conexion");
            }
        });
    }

    @Override
    public void dropOut(Customer customer, Session session, OnDropOutListener listener) {
        Call<Void> call = api.dropOut(customer.getCustomerId(), session.getSessionId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onDropOutSuccess("Te has desapuntado correctamente");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                listener.onDropOutError("Fallo en la conexion");
            }
        });
    }

    @Override
    public void sessionDetail(int sessionId, OnShowSession listener) {
        Call<Session> callMatch = api.getSession(sessionId);
        callMatch.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                listener.onShowSessionSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                listener.onShowSessionError("No se ha podido conectar");
                t.printStackTrace();
            }
        });
    }
}



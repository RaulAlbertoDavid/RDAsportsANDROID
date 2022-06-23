package com.rdacompany.rdasportsandroid.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.rdacompany.rdasportsandroid.api.RdaSportsApi;
import com.rdacompany.rdasportsandroid.api.RdaSportsApiInterface;
import com.rdacompany.rdasportsandroid.contract.ListSessionContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.domain.Session;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSessionModel implements ListSessionContract.Model {

    private Context context;
    private RdaSportsApiInterface api;

    public ListSessionModel(Context applicationContext) {
        this.context = applicationContext;

        api = RdaSportsApi.buildInstance();
    }

    @Override
    public void loadAllSessions(OnLoadSessionListener listener, Customer customer) {
        Call<List<Session>> callSessions = api.getSessions();
        callSessions.enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                List<Session> sessions = response.body();

                SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(context);
                boolean available = preferencias.getBoolean("preferences", false);
                if (available){
                    List<Session> filteredSession = sessions.stream().filter(session -> session.getCustomers().contains(customer))
                            .collect(Collectors.toList());
                    listener.onLoadSessionsSuccess(filteredSession);
                } else {
                    listener.onLoadSessionsSuccess(sessions);
                }
            }

            @Override
            public void onFailure(Call<List<Session>> call, Throwable t) {
                listener.onLoadSessionsError("No se ha podido cargar la lista de partidos");
            }
        });
    }

}

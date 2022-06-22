package com.rdacompany.rdasportsandroid.model;

import android.os.StrictMode;
import android.util.Log;

import com.rdacompany.rdasportsandroid.api.RdaSportsApi;
import com.rdacompany.rdasportsandroid.api.RdaSportsApiInterface;
import com.rdacompany.rdasportsandroid.contract.AddCustomerContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.view.AddCustomerView;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomerModel implements AddCustomerContract.Model {

    private AddCustomerView view;
    private RdaSportsApiInterface api;

    public AddCustomerModel(AddCustomerView view) {
        this.view = view;
    }

    @Override
    public void addCustomer(Customer customer, OnAddCustomerListener listener) {

        api = RdaSportsApi.buildInstance();
        Call<Customer> call = api.addCustomer(customer);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                listener.onAddCustomerSuccess("Customer añadido");
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable throwable) {
                listener.onAddCustomerError("El customer no se ha podido añadir");
            }
        });
    }
}
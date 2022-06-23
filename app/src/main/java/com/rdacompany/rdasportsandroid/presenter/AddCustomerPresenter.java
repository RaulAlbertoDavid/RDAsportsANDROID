package com.rdacompany.rdasportsandroid.presenter;

import android.telecom.Call;

import com.rdacompany.rdasportsandroid.contract.AddCustomerContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.model.AddCustomerModel;
import com.rdacompany.rdasportsandroid.view.AddCustomerView;


public class AddCustomerPresenter implements AddCustomerContract.Presenter, AddCustomerContract.Model.OnAddCustomerListener {

    private AddCustomerModel model;
    private AddCustomerView view;


    public AddCustomerPresenter(AddCustomerView view) {
        this.view = view;
        model = new AddCustomerModel(view);

    }

    @Override
    public void addCustomer(Customer customer) {
        model.addCustomer(customer, this);
    }


    @Override
    public void onAddCustomerSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onAddCustomerError(String message) {
        view.showMessage(message);
    }

}

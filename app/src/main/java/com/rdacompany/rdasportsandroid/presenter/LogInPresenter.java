package com.rdacompany.rdasportsandroid.presenter;

import com.rdacompany.rdasportsandroid.User;
import com.rdacompany.rdasportsandroid.contract.LogInContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.model.LogInModel;
import com.rdacompany.rdasportsandroid.view.LogInView;

public class LogInPresenter implements LogInContract.Presenter, LogInContract.Model.OnLogInListener {

    private LogInModel model;
    private LogInView view;

    public LogInPresenter(LogInView view) {
        this.view = view;
        model = new LogInModel(view);
    }


    @Override
    public void onLogInSuccessEmployee(String message) {
        view.showMessage(message);
        view.launchIntentEmployee();
    }

    @Override
    public void onLogInSuccessCustomer(String message, Customer customer) {
        view.showMessage(message);
        view.launchIntentCustomer(customer);
    }

    @Override
    public void onLogInError(String message) {
        view.showMessage(message);
    }

    @Override
    public void logIn(String email, String password, User user) {
        model.logIn(email, password, this, user);
    }

}

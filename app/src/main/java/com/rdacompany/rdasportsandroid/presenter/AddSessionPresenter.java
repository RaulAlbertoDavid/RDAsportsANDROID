package com.rdacompany.rdasportsandroid.presenter;

import com.rdacompany.rdasportsandroid.contract.AddSessionContract;
import com.rdacompany.rdasportsandroid.domain.Activity;
import com.rdacompany.rdasportsandroid.domain.Area;
import com.rdacompany.rdasportsandroid.domain.Employee;
import com.rdacompany.rdasportsandroid.domain.Session;
import com.rdacompany.rdasportsandroid.domain.dto.SessionDto;
import com.rdacompany.rdasportsandroid.model.AddCustomerModel;
import com.rdacompany.rdasportsandroid.model.AddSessionModel;
import com.rdacompany.rdasportsandroid.view.AddSessionView;

import java.util.List;

public class AddSessionPresenter implements AddSessionContract.Presenter, AddSessionContract.Model.OnAddSessionListener {

    private AddSessionView view;
    private AddSessionModel model;

    public AddSessionPresenter(AddSessionView view) {
        this.view = view;
        model = new AddSessionModel(this);
    }

    @Override
    public void addSession(SessionDto session) {
        model.addSession(session, this);
    }

    @Override
    public void loadEmployees() {
        model.loadEmployees();
    }

    @Override
    public void loadActivities() {
        model.loadActivities();
    }

    @Override
    public void loadAreas() {
        model.loadAreas();
    }

    @Override
    public void showEmployees(List<Employee> employees) {
        view.showEmployees(employees);
    }

    @Override
    public void showActivities(List<Activity> activities) {
        view.showActivities(activities);
    }

    @Override
    public void showAreas(List<Area> areas) {
        view.showAreas(areas);
    }


    @Override
    public void onAddSessionSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onAddSessionError(String message) {
        view.showMessage(message);
    }
}

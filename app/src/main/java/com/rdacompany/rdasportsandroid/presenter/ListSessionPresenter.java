package com.rdacompany.rdasportsandroid.presenter;


import com.rdacompany.rdasportsandroid.contract.ListSessionContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.domain.Session;
import com.rdacompany.rdasportsandroid.model.ListSessionModel;
import com.rdacompany.rdasportsandroid.view.ListSessionView;

import java.util.List;

public class ListSessionPresenter implements ListSessionContract.Presenter, ListSessionContract.Model.OnLoadSessionListener {

    private ListSessionModel model;
    private ListSessionView view;

    public ListSessionPresenter(ListSessionView view) {
        model = new ListSessionModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loadAllSessions(Customer customer) {
        model.loadAllSessions(this, customer);
    }

    @Override
    public void onLoadSessionsSuccess(List<Session> sessions) {
        view.listAllSessions(sessions);
    }

    @Override
    public void onLoadSessionsError(String message) {
        view.showMessage(message);
    }


}

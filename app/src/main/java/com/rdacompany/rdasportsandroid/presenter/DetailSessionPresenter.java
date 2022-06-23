package com.rdacompany.rdasportsandroid.presenter;

import com.rdacompany.rdasportsandroid.contract.SessionDetailContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.domain.Session;
import com.rdacompany.rdasportsandroid.model.DetailSessionModel;
import com.rdacompany.rdasportsandroid.view.DetailSessionView;

public class DetailSessionPresenter implements SessionDetailContract.Presenter, SessionDetailContract.Model.OnShowSession, SessionDetailContract.Model.OnBooklistener, SessionDetailContract.Model.OnDropOutListener {

    private DetailSessionModel model;
    private DetailSessionView view;

    public DetailSessionPresenter(DetailSessionView view) {
        this.view = view;
        model = new DetailSessionModel(view.getApplicationContext());
    }

    @Override
    public void sessionDetail(int sessionId) {
        model.sessionDetail(sessionId, this);
    }

    @Override
    public void book(Customer customer, Session session) {
        model.book(customer, session, this);
    }

    @Override
    public void dropOut(Customer customer, Session session) {
        model.dropOut(customer, session, this);
    }

    @Override
    public void onShowSessionSuccess(Session session) {
        view.loadSessionDetail(session);
    }

    @Override
    public void onShowSessionError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onBookSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onBookError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onDropOutSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onDropOutError(String message) {
        view.showMessage(message);
    }
}

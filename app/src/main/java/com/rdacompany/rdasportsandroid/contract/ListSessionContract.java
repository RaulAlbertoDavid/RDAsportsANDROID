package com.rdacompany.rdasportsandroid.contract;

import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.domain.Session;

import java.util.List;

public interface ListSessionContract {

    interface Model {
        interface OnLoadSessionListener {
            void onLoadSessionsSuccess(List<Session> sessions);
            void onLoadSessionsError(String message);
        }

        void loadAllSessions(OnLoadSessionListener listener, Customer customer);
    }

    interface Presenter {
        void loadAllSessions(Customer customer);
    }

    interface View {
        void listAllSessions(List<Session> sessions);
        void showMessage(String message);
    }

}

package com.rdacompany.rdasportsandroid.contract;

import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.domain.Session;

public interface SessionDetailContract {

    interface Model {

        interface OnShowSession {

            void onShowSessionSuccess(Session session);
            void onShowSessionError(String message);
        }

        interface OnBooklistener {
            void onBookSuccess(String message);
            void onBookError(String message);
        }

        interface OnDropOutListener {
            void onDropOutSuccess(String message);
            void onDropOutError(String message);
        }

        void sessionDetail(int sessionId, OnShowSession listener);
        void book(Customer customer, Session session, OnBooklistener listener);
        void dropOut(Customer customer, Session session, OnDropOutListener listener);
    }

    interface Presenter {
        void sessionDetail(int sessionId);

        void book(Customer customer, Session session);
        void dropOut(Customer customer, Session session);
    }

    interface View {
        void loadSessionDetail(Session session);
        void showMessage(String message);
    }
}

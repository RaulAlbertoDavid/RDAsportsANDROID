package com.rdacompany.rdasportsandroid.contract;


import com.rdacompany.rdasportsandroid.User;
import com.rdacompany.rdasportsandroid.domain.Customer;

public interface LogInContract {

    interface Model {
        interface OnLogInListener {
            void onLogInSuccessEmployee(String message);
            void onLogInSuccessCustomer(String message, Customer customer);
            void onLogInError(String message);
        }
        void logIn (String email, String password, OnLogInListener listener, User user);
    }

    interface Presenter {
        void logIn(String email, String password, User user);

    }

    interface View {
        void logIn (android.view.View view);
        void launchIntentCustomer(Customer customer);
        void launchIntentEmployee();
        void showMessage(String message);
    }
}

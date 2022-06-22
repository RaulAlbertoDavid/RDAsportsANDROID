package com.rdacompany.rdasportsandroid.contract;


import com.rdacompany.rdasportsandroid.domain.Customer;

public interface AddCustomerContract {

    interface Model {
        interface OnAddCustomerListener {
            void onAddCustomerSuccess(String message);
            void onAddCustomerError(String message);
        }
        void addCustomer(Customer customer, OnAddCustomerListener listener);
    }

    interface Presenter {
        void addCustomer(Customer customer);
    }

    interface View {
        void addCustomer(android.view.View view);
        void showMessage(String message);
    }
}

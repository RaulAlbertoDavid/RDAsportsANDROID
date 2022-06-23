package com.rdacompany.rdasportsandroid.model;

import com.rdacompany.rdasportsandroid.User;
import com.rdacompany.rdasportsandroid.api.RdaSportsApi;
import com.rdacompany.rdasportsandroid.api.RdaSportsApiInterface;
import com.rdacompany.rdasportsandroid.contract.LogInContract;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.domain.Employee;
import com.rdacompany.rdasportsandroid.view.LogInView;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInModel implements LogInContract.Model {

    private LogInView view;
    private RdaSportsApiInterface api;

    public LogInModel(LogInView view) {
        this.view = view;
        api = RdaSportsApi.buildInstance();
    }

    public void logIn(String email, String password, OnLogInListener listener, User user) {
        if (user.equals(User.CUSTOMER)) {
            Call<List<Customer>> call = api.getCustomers();
            call.enqueue(new Callback<List<Customer>>() {
                @Override
                public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                    List<Customer> customers = response.body();
                    List<Customer> customersFiltered = customers.stream().filter(customer -> email.equals(customer.getEmail()) && password.equals(customer.getPassword()))
                            .collect(Collectors.toList());

                    if (customersFiltered.isEmpty()) {
                        listener.onLogInError("Las credenciales son incorrectas");
                    } else {
                        Customer customer = customersFiltered.get(0);
                        listener.onLogInSuccessCustomer("Sesion iniciada correctamente", customer);
                    }
                }

                @Override
                public void onFailure(Call<List<Customer>> call, Throwable throwable) {
                    listener.onLogInError("No se ha podido realizar la accion");
                }
            });
        } else {
            Call<List<Employee>> call = api.getEmployees();
            call.enqueue(new Callback<List<Employee>>() {
                @Override
                public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                    List<Employee> employees = response.body();
                    List<Employee> employeesFiltered = employees.stream().filter(employee -> email.equals(employee.getEmail()) && password.equals(employee.getPassword()))
                            .collect(Collectors.toList());
                    if (employeesFiltered.isEmpty()) {
                        listener.onLogInError("Las credenciales son incorrectas");
                    } else {
                        listener.onLogInSuccessEmployee("Sesion iniciada correctamente");
                    }
                }

                @Override
                public void onFailure(Call<List<Employee>> call, Throwable throwable) {
                    listener.onLogInError("No se ha podido realizar la accion");
                }
            });
        }
    }


}

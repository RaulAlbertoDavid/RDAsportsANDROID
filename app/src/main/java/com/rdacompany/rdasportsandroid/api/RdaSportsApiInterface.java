package com.rdacompany.rdasportsandroid.api;

import com.rdacompany.rdasportsandroid.domain.Activity;
import com.rdacompany.rdasportsandroid.domain.Area;
import com.rdacompany.rdasportsandroid.domain.Customer;
import com.rdacompany.rdasportsandroid.domain.Employee;
import com.rdacompany.rdasportsandroid.domain.Session;
import com.rdacompany.rdasportsandroid.domain.dto.SessionDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RdaSportsApiInterface {
    @POST("/customers")
    Call<Customer> addCustomer (@Body Customer newCustomer);

    @POST("/sessions")
    Call<Session> addSession (@Body SessionDto newSession);

    @GET("/customers")
    Call<List<Customer>> getCustomers();

    @GET("/employees")
    Call<List<Employee>> getEmployees();

    @GET("/activities")
    Call<List<Activity>> getActivities();

    @GET("/areas")
    Call<List<Area>> getAreas();

    @GET("/sessions")
    Call<List<Session>> getSessions();

    @GET("session/{sessionId}")
    Call<Session> getSession(@Path("sessionId") int sessionId);

    @POST("/customer/{customerId}/session/{sessionId}")
    Call<Void> relacion (@Path("customerId") int customerId, @Path("sessionId") int sessionId);

    @DELETE("/session/{sessionId}/customer/{customerId}")
    Call<Void> dropOut (@Path("customerId") int customerId, @Path("sessionId") int sessionId);
}

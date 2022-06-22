package com.rdacompany.rdasportsandroid.contract;


import com.rdacompany.rdasportsandroid.domain.Activity;
import com.rdacompany.rdasportsandroid.domain.Area;
import com.rdacompany.rdasportsandroid.domain.Employee;
import com.rdacompany.rdasportsandroid.domain.Session;
import com.rdacompany.rdasportsandroid.domain.dto.SessionDto;

import java.util.List;

public interface AddSessionContract {

    interface Model {
        interface OnAddSessionListener {
            void onAddSessionSuccess(String message);
            void onAddSessionError(String message);
        }
        void addSession (SessionDto session, OnAddSessionListener listener);
        void loadEmployees();
        void loadActivities();
        void loadAreas();
    }

    interface Presenter {
        void addSession(SessionDto session);
        void loadEmployees();
        void loadActivities();
        void loadAreas();
        void showEmployees(List<Employee> employees);
        void showActivities(List<Activity> activities);
        void showAreas(List<Area> areas);
    }

    interface View {
        void addSession(android.view.View view);
        void showMessage(String message);
        void showEmployees(List<Employee> employees);
        void showActivities(List<Activity> activities);
        void showAreas(List<Area> areas);
    }
}

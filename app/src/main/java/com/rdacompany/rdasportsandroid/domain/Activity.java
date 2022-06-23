package com.rdacompany.rdasportsandroid.domain;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Activity {

    private int activityId;
    private String name;
    private String description;
    private String material;
    private boolean isAerobic;

    private List<Session> sessions;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isAerobic() {
        return isAerobic;
    }

    public void setAerobic(boolean aerobic) {
        isAerobic = aerobic;
    }

    @Override
    public String toString() {
        return name;
    }
}
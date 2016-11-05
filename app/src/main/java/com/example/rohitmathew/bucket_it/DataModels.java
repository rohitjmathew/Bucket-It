package com.example.rohitmathew.bucket_it;

import org.json.JSONObject;

import java.util.ArrayList;

public class DataModels {

    public static DataModels instance = null;

    private ArrayList<Patient> patientsList = null;

    public Family getFamily() {
        return family;
    }

    public void setFamily(JSONObject object) {
        this.family = new Family(object);
    }

    private Family family = null;

    public static DataModels getInstance() {
        if(instance == null) {
            instance = new DataModels();
            instance.patientsList = new ArrayList<>();
        }
        return instance;
    }

    public void add(JSONObject patientDetails) {
        patientsList.add(new Patient(patientDetails));
    }

    public void clear() {
        patientsList.clear();
        family = null;
    }

    public ArrayList<Patient> getPatientsList() {
        return patientsList;
    }
}

package com.example.asus.doctor;

/**
 * Created by ASUS on 17-07-2017.
 */

//Simple POJO class

public class inputPatientHistory {

    public String name;
    public String age;
    public String weight;
    public String condition;
    public String allergy;

    public inputPatientHistory(){

    }

    public inputPatientHistory(String name, String age, String weight, String condition, String allergy) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.condition = condition;
        this.allergy = allergy;
    }
}

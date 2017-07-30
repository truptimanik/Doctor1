package com.example.asus.doctor;

/**
 * Created by ASUS on 17-07-2017.
 */

public class inputPill {

    private String pillName;
    private String morning;
    private String noon;
    private String eve;

    public inputPill(String pillName, String morning, String noon, String eve) {
        this.pillName = pillName;
        this.morning = morning;
        this.noon = noon;
        this.eve = eve;
    }


    public inputPill(){

    }


    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getNoon() {
        return noon;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }

    public String getEve() {
        return eve;
    }

    public void setEve(String eve) {
        this.eve = eve;
    }


    /*   private boolean morning;
    private boolean noon;
    private boolean eve;
    private boolean switchDel;

    private String time;*/




  /*  public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {


        this.morning = morning;
    }

    public boolean isNoon() {
        return noon;
    }

    public void setNoon(boolean noon) {
        this.noon = noon;
    }

    public boolean isEve() {
        return eve;
    }

    public void setEve(boolean eve) {
        this.eve = eve;
    }

    public boolean isSwitchDel() {
        return switchDel;
    }

    public void setSwitchDel(boolean switchDel) {
        this.switchDel = switchDel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public inputPill(String pillName, boolean morning, boolean noon, boolean eve) {
        this.pillName = pillName;
        this.morning = morning;
        this.noon = noon;
        this.eve = eve;
     //   this.switchDel = switchDel;
       // this.time = time;

    }


  /*  public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public inputPill(String pillName, boolean morning, boolean noon, boolean eve) {
        pillName = pillName;
        this.morning = morning;
        this.noon = noon;
        this.eve = eve;
        //this.switchDel = switchDel;
    }
    public inputPill(String pillName,String time){
        pillName = pillName;
      //  this.PillName=pillName;
        this.time = time;
        //morning = false;
        //noon=false;


    }


    public String getPillName() {
        return illName;
    }

    public void setPillName(String pillName) {
        pillName = pillName;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isNoon() {
        return noon;
    }

    public void setNoon(boolean noon) {
        this.noon = noon;
    }

    public boolean isEve() {
        return eve;
    }

    public void setEve(boolean eve) {
        this.eve = eve;
    }

  /*  public boolean isSwitchDel() {
        return switchDel;
    }

    public void setSwitchDel(boolean switchDel) {
        this.switchDel = switchDel;
    }*/










}

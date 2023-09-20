package com.example.myapplication;

public class UserInfo {
    private double _Height= 0.0;
    private double _Weight= 0.0;
    private String _Gender = "";
    private double ActivityLevel = 0.0;
    private  int _age = 0;
    private int _sleepDuration = 0;
    private double _bmi=0.0;
    private double _bmr =0.0;
    private double _activityrate=0.0;
    public UserInfo(){}
    public UserInfo(String Name,int age,String gender,double activityrate,double height,double weight, int sleepDuration) {
        this._Height = height;
        this._Weight = weight;
        this._Gender = gender;
        this._age = age;
        this._activityrate =activityrate;

        this._sleepDuration = sleepDuration;
        this._Name = Name;
    }

    public double getBmi() {
        return _bmi;
    }

    public void setBmi(double bmi) {
        this._bmi = bmi;
    }

    public double getBmr() {
        return _bmr;
    }

    public void setBmr(double bmr) {
        this._bmr = bmr;
    }

    private String _Name= "";

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public double get_Height() {
        return _Height;
    }

    public void set_Height(double _Height) {
        this._Height = _Height;
    }

    public double get_Weight() {
        return _Weight;
    }

    public void set_Weight(double _Weight) {
        this._Weight = _Weight;
    }

    public String get_Gender() {
        return _Gender;
    }

    public void set_Gender(String _Gender) {
        this._Gender = _Gender;
    }

    public double getActivityLevel() {
        return ActivityLevel;
    }

    public void setActivityLevel(double activityLevel) {
        ActivityLevel = activityLevel;
    }

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }

    public int get_sleepDuration() {
        return _sleepDuration;
    }

    public void set_sleepDuration(int _sleepDuration) {
        this._sleepDuration = _sleepDuration;
    }




}

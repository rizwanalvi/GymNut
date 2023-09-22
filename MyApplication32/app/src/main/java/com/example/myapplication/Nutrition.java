package com.example.myapplication;

public class Nutrition {
    private String _foodName="";
    private String _protien="";
    private String _carbs="";
    private String _fat="";
    private String _calories="";
    private String _serving="";
    private String _fiber="";
    private String _sugar="";
    private String _date="";
    private String _MealType="";
    public Nutrition(String _foodName){
        this._foodName = _foodName;
    }
    public Nutrition(String _foodName,String MealType,String calories){
        this._foodName = _foodName;
        this._MealType = MealType;
        this._calories = calories;
    }
    public Nutrition(String _foodName,String MealType,String calories,String date){
        this._foodName = _foodName;
        this._MealType = MealType;
        this._calories = calories;
        this._date = date;
    }
    public Nutrition(){

    }
    public Nutrition(String _foodName, String _protien, String _carbs, String _fat, String _calories, String _serving, String _fiber, String _sugar, String _date) {
        this._foodName = _foodName;
        this._protien = _protien;
        this._carbs = _carbs;
        this._fat = _fat;
        this._calories = _calories;
        this._serving = _serving;
        this._fiber = _fiber;
        this._sugar = _sugar;
        this._date = _date;
    }

    public String get_MealType() {
        return _MealType;
    }

    public void set_MealType(String _MealType) {
        this._MealType = _MealType;
    }

    public String get_foodName() {
        return _foodName;
    }

    public void set_foodName(String _foodName) {
        this._foodName = _foodName;
    }

    public String get_protien() {
        return _protien;
    }

    public void set_protien(String _protien) {
        this._protien = _protien;
    }

    public String get_carbs() {
        return _carbs;
    }

    public void set_carbs(String _carbs) {
        this._carbs = _carbs;
    }

    public String get_fat() {
        return _fat;
    }

    public void set_fat(String _fat) {
        this._fat = _fat;
    }

    public String get_calories() {
        return _calories;
    }

    public void set_calories(String _calories) {
        this._calories = _calories;
    }

    public String get_serving() {
        return _serving;
    }

    public void set_serving(String _serving) {
        this._serving = _serving;
    }

    public String get_fiber() {
        return _fiber;
    }

    public void set_fiber(String _fiber) {
        this._fiber = _fiber;
    }

    public String get_sugar() {
        return _sugar;
    }

    public void set_sugar(String _sugar) {
        this._sugar = _sugar;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }
}

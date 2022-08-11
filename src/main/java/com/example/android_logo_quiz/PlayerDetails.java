package com.example.android_logo_quiz;

public class PlayerDetails {
    private String name;
    private int id;
    private int age;
    //default constructor
    public PlayerDetails() {
    }

    //parameterized constructor
    public PlayerDetails(String name, int id,int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PlayerDetails{" +
                "id='" + id + '\'' +
                ", name=" + name +", age= "+age+
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

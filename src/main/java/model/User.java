package dao;

import model.Booking;

import java.util.ArrayList;
import java.util.List;

public class User {
    private  String fullName;



    public User(String fullName) {
        this.fullName = fullName;
    }

    public User() {
    }
    public String getFullName() {
        return fullName;
    }



    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
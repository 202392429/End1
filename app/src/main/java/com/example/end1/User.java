package com.example.end1;

public class User {


    private String ID;
    private String FirstName;
    private String LastName;
    private String Email;

    // constructor function 4 text String
    public User(String id, String fName, String lName, String eamil) {
        ID = id;
        FirstName = fName;
        LastName = lName;
        Email = eamil;
    }

    // setr and getr
    public String getID() {
        return ID;
    }
    public void setID(String id) {
        ID = id;
    }
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
}





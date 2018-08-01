package com.arthur.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;


public class Person {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String occupation;
    private String color;
    private String welcomeMsg;
    @Indexed(unique = true)
    private String email;
    private String image;


    //Explicit constructor
    public Person(){
    }

    public Person(String id, String firstname, String lastname, String occupation, String color, String welcomeMsg, String email, String image) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.occupation = occupation;
        this.color = color;
        this.welcomeMsg = welcomeMsg;
        this.email = email;
        this.image = image;
    }

    public String getId() {
        if (this.id == null) {
            this.id = Integer.toHexString(this.hashCode());
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstName) { this.firstname = firstName; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastName) { this.lastname = lastName; }

    public String getOccupation() { return occupation; }

    public void setOccupation(String occupation) { this.occupation = occupation; }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

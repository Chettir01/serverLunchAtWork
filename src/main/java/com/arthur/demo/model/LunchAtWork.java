package com.arthur.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

public class LunchAtWork {

    @Id
    private String id;
    private String plat;
    private String description;
    private String composition;
    private String image;

    @Indexed(unique = true)
    private Date date;

    private Person person;

    //explicit constructor
    public LunchAtWork(){
    }

    //Constructor
    public LunchAtWork(String plat, String description, String composition, Date date, String image, Person person) {
        this.plat = plat;
        this.description = description;
        this.composition = composition;
        this.date = date;
        this.image = image;
        this.person = new Person();
    }

    //Getters and setters
    public String getId() {
        if (this.id == null) {
            this.id = Integer.toHexString(this.hashCode());
        }
        return id;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

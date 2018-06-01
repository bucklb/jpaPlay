package com.bucklb.jpaPlay.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // <-- be SURE to specify this bit !!!!!
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    // May not need to be explicit with name
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    /*
    // Constructors
    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person() {
    }
    */

    // Setters and getters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {        this.firstName = firstName;    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    // Keeping it simple
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
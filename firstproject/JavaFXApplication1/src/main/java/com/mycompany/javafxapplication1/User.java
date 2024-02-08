/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxapplication1;

import java.util.HashSet;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ntu-user
 */
public class User {
    private SimpleStringProperty user;
    private SimpleStringProperty pass;

    User(String user, String pass) {
        this.user = new SimpleStringProperty(user);
        this.pass = new SimpleStringProperty(pass);
    }

    public String getUser() {
        return user.get();
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getPass() {
        return pass.get();
    }

    public void setPass(String pass) {
        this.pass.set(pass);
    }
    
//    public int getId() {
//        return id;
//    }
//    
//    public void setId(int id) {
//        this.id = id;
//        
//    }
}



/*This code is a Java function that defines a User class with two attributes: user and pass. 
   * The function takes two arguments, user and pass, which are strings that represent the username and password of a user. 
    *The function creates a new User object with these attributes and returns it to the main program. 
    *The function also has getter and setter methods for accessing and modifying the user and pass attributes. 

*The function is useful for creating and managing user accounts in a JavaFX application. JavaFX is a framework for creating graphical user interfaces in Java1. 
 *   The user and pass attributes are stored as SimpleStringProperty objects, which are observable properties that can be bound to other UI elements, such as a TableView2. 
  *  This allows the UI to automatically update when the user or pass values change. 
    */

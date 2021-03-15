package com.pijner.articlerevsys.connector;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * This is a managed bean class that holds the user's name. If a user with the 
 * same name exists in the DB, we pull the user_id from DB. If not, we create a
 * new user in the DB and use the new user_id
 * 
 * @author Prahar Ijner
 */

@SessionScoped
@Named("user")
public class User implements Serializable {
    private String name;
    private int user_id;

    public User() {
        name = "";
        user_id = -1;
    }
    
    public User(String name) {
        this.name = name;
    }

    public User(String name, int user_id) {
        this.name = name;
        this.user_id = user_id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }
    
    /**
     * function to fetch user_id from DB if user already exists. Otherwise, 
     * user is created and the auto-generated ID is fetched
     */
    public void updateID(){
        DBConnector dbc = new DBConnector();
        User details = dbc.getUserByName(this.name);
        if(details == null){
            dbc.addUser(this.name);
            details = dbc.getUserByName(this.name);
        }
        this.user_id = details.user_id;
    }
    
}

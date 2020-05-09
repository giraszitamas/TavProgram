/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.modell;

import hu.unideb.inf.entity.User;
import hu.unideb.inf.util.JpaUserDAO;
import hu.unideb.inf.util.UserDAO;

/**
 * Handles the logged in user's data.
 * @author pixel
 */
public class CurrentUser {
    private User current;

    private static CurrentUser instance = null;
    
    //Get the basic instance
    public static synchronized CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }
    
    private CurrentUser() {
        current = null;
    }
    
    //Get the instance with a new user
    public static synchronized CurrentUser getInstance(User newUser) {
        if (instance == null) {
            instance = new CurrentUser(newUser);
        }else{
            instance.setCurrent(newUser);
        }
        return instance;
    }
    
    private CurrentUser(User current) {
        this.current = current;
    }
    
    //Get the instance with a new user by username
    public static synchronized CurrentUser logIn(String username) {
        if (instance == null) {
            System.out.println(" -- WasNull -- ");
            instance = new CurrentUser(username);
        }else{
            if(!instance.getAndSetNew(username)){
                instance = null;
            }
        }
        System.out.println(instance.toString());
        return instance;
    }
    
    private CurrentUser(String username) {
        boolean valid = this.getAndSetNew(username);
        if(!valid){
            instance = null;
            System.out.println(" -- IsNull -- ");
        }
    }
    
    public static synchronized CurrentUser logOut() {
        instance = new CurrentUser();
        return instance;
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }
    
    private boolean getAndSetNew(String username) {
        boolean valid;
        try {
            System.out.println(" -- DataGetStart -- ");
            UserDAO userDAO = new JpaUserDAO();
            User user = userDAO.getByUsername(username);
            System.out.println(" -- Connection -- ");
            if(user != null){
                current = user;
            }
            userDAO.close();
            System.out.println(" -- Valid -- ");
            valid = true;
        }catch (Exception e){ 
            System.out.println(" -- Not Valid -- ");
            valid = false;
        }
        return valid;
    }
}
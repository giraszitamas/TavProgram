/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.modell;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.util.EduDAO;
import hu.unideb.inf.util.JpaEduDAO;
import java.time.LocalDate;

/**
 *
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
    
    private CurrentUser() {  //privát láthatóságú konstruktor! - egyke megvalósítás
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
    
    private CurrentUser(User current) {  //privát láthatóságú konstruktor! - egyke megvalósítás
        this.current = current;
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }
    
}

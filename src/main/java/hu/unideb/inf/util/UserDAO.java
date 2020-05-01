/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.entity.User;

/**
 *
 * @author pixel
 */
public interface UserDAO extends AutoCloseable {
    
    public User getByUsername(String name);
    public User getById(long id);
    //Feeled like we will need it, but now not sure. The normal update started working
    public boolean addCourse(long courseId);
    
    @Override
    default public void close() {
    }
}

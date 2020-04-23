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
    
    public User findByName(String name);
    
    @Override
    default public void close() {
    }
    
    //I will just add this comment, becasue the git don't want tu update this file.'
}

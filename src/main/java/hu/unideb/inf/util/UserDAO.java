/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.entity.User;
import java.util.List;

/**
 *
 * @author pixel
 */
public interface UserDAO extends AutoCloseable {
    
    public User getByUsername(String name);
    public User getById(long id);
    public List<User> getByPartUsername(String PartName);
    
    @Override
    default public void close() {
    }
}

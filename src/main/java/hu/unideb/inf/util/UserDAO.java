/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.entity.Course;
import java.util.List;
import hu.unideb.inf.entity.User;

/**
 *
 * @author pixel
 */
public interface UserDAO extends AutoCloseable{
    
    public void save(User u);
    public void delete(User u);
    public void update(User u);
    public List<User> getUsers();
    public void save(Course c);
    public void delete(Course c);
    public void update(Course c);
    public List<Course> getCourses();
    
    @Override
    default public void close(){        
    }
    
}

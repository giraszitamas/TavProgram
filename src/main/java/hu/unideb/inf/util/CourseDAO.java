/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.entity.Course;

/**
 *
 * @author pixel
 */
public interface CourseDAO extends AutoCloseable {
    
    public Course getByName(String name);
    public Course getById(long id);
    
    @Override
    default public void close() {
    }
}

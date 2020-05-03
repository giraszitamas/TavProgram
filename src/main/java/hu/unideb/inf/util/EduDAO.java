/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import java.util.List;

/**
 *
 * @author pixel
 */
public interface EduDAO<T> extends AutoCloseable{
    
    public boolean save(T obj);
    public boolean delete(T obj);
    public boolean update(T obj);
    public List<T> getData(Class<T> clazz);
    
    @Override
    default public void close(){
    }
    
}

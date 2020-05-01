/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author pixel
 * @param <T>
 */
public class JpaEduDAO<T> implements EduDAO<T>{
    
    Session session;
    Transaction transaction;

    public JpaEduDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("Edu DAO session opened...");    //During development.
    }

    @Override
    public void close() {
        session.close();
        System.out.println("Edu DAO session closed...");        //During development.
    }
    
    @Override
    public void save(T obj){
        try{
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(e);
        }
    }

    @Override
    public void delete(T obj) {
        try{
            transaction = session.beginTransaction();
            session.remove(obj);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(e);
        }
    }

    @Override
    public void update(T obj) {
        try{
            transaction = session.beginTransaction();
            session.update(obj);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(e);
        }
    }

    @Override
    public List<T> getData(Class<T> clazz) {
        var hql = "FROM " + clazz.getCanonicalName();
        Query query = session.createQuery(hql);
        return query.list();
    }
    
}

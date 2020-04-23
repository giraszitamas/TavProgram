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
        System.out.println("DAO session closed...");        //During development.
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
        }
    }

    @Override
    public List<T> getData() {
        String hql = "FROM hu.unideb.inf.entity.User";
        Query query = session.createQuery(hql);
        return query.list();
    }
    
}

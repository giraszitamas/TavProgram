/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author pixel
 */
public class JpaUserDAO implements UserDAO{
    
    Session session;
    Transaction transaction;

    public JpaUserDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("DAO session opened...");
    }

    @Override
    public void close() {
        session.close();
        System.out.println("DAO session closed...");
    }
    
    @Override
    public void save(User u) {
        try{
            transaction = session.beginTransaction();
            session.save(u);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(User u) {
        try{
            transaction = session.beginTransaction();
            session.remove(u);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(User u) {
        try{
            transaction = session.beginTransaction();
            session.update(u);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getUsers() {
        String hql = "FROM hu.unideb.inf.entity.User";
        Query query = session.createQuery(hql);
        return query.list();
    }
    
        @Override
    public void save(Course c) {
        try{
            transaction = session.beginTransaction();
            session.save(c);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Course c) {
        try{
            transaction = session.beginTransaction();
            session.remove(c);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Course c) {
        try{
            transaction = session.beginTransaction();
            session.update(c);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Course> getCourses() {
        String hql = "FROM hu.unideb.inf.entity.Course";
        Query query = session.createQuery(hql);
        return query.list();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

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
        System.out.println("User DAO session opened...");    //During development.
    }

    @Override
    public void close() {
        session.close();
        System.out.println("User DAO session closed...");        //During development.
    }

    @Override
    public User getByUsername(String username) {
        //GET and Filter the users by username and return it.
        String hql = "SELECT user FROM hu.unideb.inf.entity.User user WHERE user.username=:uname";
        Query query = session.createQuery(hql);
        query.setParameter("uname", username);
        User user = (User)query.uniqueResult();
        return user;
    }

    @Override
    public User getById(long id) {
        //GET and Filter the users by id and return it.
        String hql = "SELECT user FROM hu.unideb.inf.entity.User user WHERE user.id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        User user = (User)query.uniqueResult();
        return user;
    }
    @Override
    public List<User> getByPartUsername(String PartName){
        String hql = "SELECT user FROM hu.unideb.inf.entity.User user WHERE user.username LIKE '%"+PartName+"%'";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public List<User> getEveryUser() {
        String hql = "FROM hu.unideb.inf.entity.User";
        Query query = session.createQuery(hql);
        return query.list();
        
    }
}

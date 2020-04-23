/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pixel
 */
public class JpaUserDAO implements UserDAO{
    Session session;
    Transaction transaction;

    public JpaUserDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("Edu DAO session opened...");    //During development.
    }

    @Override
    public void close() {
        session.close();
        System.out.println("DAO session closed...");        //During development.
    }

    @Override
    public User findByName(String name) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

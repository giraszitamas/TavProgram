/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author pixel
 */
public class JpaCourseDAO implements CourseDAO {

    Session session;
    Transaction transaction;

    public JpaCourseDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("User DAO session opened...");    //During development.
    }
    
    @Override
    public void close() {
        session.close();
        System.out.println("User DAO session closed...");        //During development.
    }
    
    @Override
    public Course getByName(String name) {
        //GET and Filter the courses by name and return them.
        String hql = "SELECT course FROM hu.unideb.inf.entity.Course course WHERE course.name=:name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        Course course = (Course)query.uniqueResult();
        return course;
    }

    @Override
    public Course getById(long id) {
        //GET and Filter the courses by id and return them.
        String hql = "SELECT course FROM hu.unideb.inf.entity.Course course WHERE course.id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        Course course = (Course)query.uniqueResult();
        return course;
    }
}

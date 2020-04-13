/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.modell;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.util.JpaUserDAO;
import hu.unideb.inf.util.UserDAO;
import java.time.LocalDate;

/**
 *
 * @author pixel
 */
public class Simulation {
    
    private User student1, student2, teacher1;
    private Course subject;

    public User getStudent1() {
        return student1;
    }
    
    public User getStudent2() {
        return student2;
    }
    
    public User getTeacher1() {
        return teacher1;
    }
    
    public Course getSubject() {
        return subject;
    }
    
    public Simulation() {
        student1 = new User(User.userType.STUDENT, "Nem2", "Vicc", LocalDate.parse("1999-04-04"), "jonnyyespapa@malbox.unideb.hu");
        student2 = new User(User.userType.STUDENT, "Elek", "Teszt", LocalDate.parse("1998-12-03"), "szamonkerlek@malbox.unideb.hu", "AlmaRetep248");
        teacher1 = new User(User.userType.TEACHER, "Péter", "Alma", LocalDate.parse("1981-07-06"), "ezalma@malbox.unideb.hu");
        subject = new Course("SzoftDev");
        
        subject.addUser(student1);
        subject.addUser(student2);
        subject.addUser(teacher1);
        //student1.addCourse(subject);
        //student2.addCourse(subject);
        //teacher1.addCourse(subject);
        
        System.out.println(teacher1.toString());
        System.out.println(student1.toString());
        System.out.println(student2.toString());
        System.out.println(subject.toString());
        
        try (UserDAO uDAO = new JpaUserDAO()) {
            uDAO.save(student1);
            uDAO.save(student2);
            uDAO.save(teacher1);
            uDAO.save(subject);
        }
        
        subject.setResponsible(teacher1);
        System.out.println(teacher1.toString());
        System.out.println(student1.toString());
        System.out.println(student2.toString());
        System.out.println(subject.toString());
        
        try (UserDAO uDAO = new JpaUserDAO()) {
            uDAO.update(subject);
        }
    }
    
}

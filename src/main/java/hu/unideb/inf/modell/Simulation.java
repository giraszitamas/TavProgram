/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.modell;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.Note;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.util.JpaEduDAO;
import hu.unideb.inf.util.EduDAO;
import java.time.LocalDate;

/**
 *
 * @author pixel
 */
public class Simulation {
    
    private User student1, student2, teacher1, admin1;
    private Note note1, note2;
    private Course subject;

    private static Simulation instance = null;
    
    public static synchronized Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }
    
    public User getStudent1() {
        return student1;
    }
    
    public User getStudent2() {
        return student2;
    }
    
    public User getTeacher1() {
        return teacher1;
    }
    
    public User getAdmin1() {
        return admin1;
    }
    
    public Course getSubject() {
        return subject;
    }
    
    private Simulation() {  //privát láthatóságú konstruktor!
        student1 = new User(User.userType.STUDENT, "ElekVokOztCso", "János", "Kocsis", LocalDate.parse("1999-04-04"), "jonnyyespapa@malbox.unideb.hu", "jelszó123");
        student2 = new User(User.userType.STUDENT, "Tesztellek", "Elek", "Teszt", LocalDate.parse("1998-12-03"), "szamonkerlek@malbox.unideb.hu", "TESZT123");
        teacher1 = new User(User.userType.TEACHER, "EzAlma", "Péter", "Alma", LocalDate.parse("1981-07-06"), "ezalma@malbox.unideb.hu", "jelszó123");
        admin1 = new User(User.userType.ADMIN, "admin", "Jenő", "Menő", LocalDate.parse("1977-07-07"), "nagyonadmin@malbox.unideb.hu", "admin123");
        note1 = new Note("Jegyzet1", "Ez egy jegyzet.");
        note2 = new Note("Jegyzet2");
        note2.setValue("Ez egy másik jegyzet.");
        subject = new Course("SzoftDev", "123");
        
        //Save the users
        try (EduDAO uDAO = new JpaEduDAO<User>()) {
            uDAO.save(student1);
            uDAO.save(student2);
            uDAO.save(teacher1);
            uDAO.save(admin1);
        }
        
        //Add the users to the course
        subject.addUser(student2);
        subject.addUser(teacher1);
        subject.setResponsible(teacher1);
        try (EduDAO cDAO = new JpaEduDAO<Course>()) {
            cDAO.save(subject);
        }
        try (EduDAO uDAO = new JpaEduDAO<Course>()){
            uDAO.update(subject);
        }
        
//        //Add the course to the users
//        student1.addCourse(subject);
//        student2.addCourse(subject);
//        teacher1.addCourse(subject);
//        subject.setResponsible(teacher1);
//        try (EduDAO cDAO = new JpaEduDAO<Course>()) {
//            cDAO.save(subject);
//        }
//        try (EduDAO uDAO = new JpaEduDAO<User>()) {
//            uDAO.update(student1);
//            uDAO.update(student2);
//            uDAO.update(teacher1);
//        }
        
        //Add the notes to the course
        subject.addNote(note1);
        subject.addNote(note2);
        try (EduDAO nDAO = new JpaEduDAO<Note>()) {
            nDAO.save(note1);
            nDAO.save(note2);
        }
        try (EduDAO uDAO = new JpaEduDAO<Course>()) {
            uDAO.update(subject);
        }
    }
}

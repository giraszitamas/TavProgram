/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author pixel
 */
@Entity
@Table(name = "courses")
public class Course implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "course_id")
    private int id;
    
    @Column(unique = false, name = "name")
    private String name;
    
    @Column(unique = false, name = "responsible_id")
    private long responsibleId;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
    name = "user_access",
    joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private Set<User> users = new HashSet<>();
    
    public Course() {
    }
    
    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getResponsible() {
        return responsibleId;
    }

    public void setResponsible(long responsibleId) {
        this.responsibleId = responsibleId;
    }
    
    public void setResponsible(User responsible) {
        this.responsibleId = responsible.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void addUser(User u) {
        users.add(u);
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", responsibleId=" + responsibleId + ", users=" + users + ']';
    }
    
}

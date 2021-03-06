/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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
import javax.persistence.OneToMany;
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
    private long id;
    
    @Column(unique = true, name = "name")
    private String name;
    
    @Column(unique = false, name = "code")
    private String code;
    
    @Column(unique = false, name = "responsible_id")
    private long responsibleId;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
    name = "user_access",
    joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private Set<User> users = new HashSet<>();
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Set<Note> notes = new HashSet<Note>();
    
    public Course() {
    }
    
    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }
    
    public Course(String name, String code, long responsibleId) {
        this.name = name;
        this.code = code;
        this.responsibleId = responsibleId;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public void addUser(User u) {
        users.add(u);
    }

    public Set<User> getUsers() {
        return users;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(long responsibleId) {
        this.responsibleId = responsibleId;
    }
    
    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
    
    public void addNote(Note note) {
        notes.add(note);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.responsibleId != other.responsibleId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.code, other.code);
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(" - ").append(name);
        return sb.toString();
    }
    
    public String printAll(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append("-").append(this.getName()).append('-').append(this.getCode()).append('\n');
        sb.append("Users: ");
        var us = this.getUsers();
        us.forEach((user) -> {
            sb.append(user).append('\n');
        });
        sb.append("Notes: ");
        var ns = this.getNotes();
        ns.forEach((note) -> {
            sb.append(note).append('\n');
        });
        return sb.toString();
    }
    
}

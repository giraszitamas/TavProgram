/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.Table;

/**
 *
 * @author pixel
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "user_id")
    private int id;
    
    @Column(unique = false, name = "user_type")
    private userType type;
    
    @Column(unique = false, name = "first_name")
    private String FirstName;
    
    @Column(unique = false, name = "last_name")
    private String LastName;
    
    @Column(unique = false, name = "birth_date")
    private LocalDate birthDate;
    
    @Column(unique = false, name = "email")
    private String email;
    
    @Column(unique = false, name = "code")
    private String code;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
    name = "user_access",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"))
    private Set<Course> courses = new HashSet<>();
    
    public User() {
    }
    
    public User(userType type, String FirstName, String LastName, LocalDate birthDate, String email) {
        this.type = type;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.birthDate = birthDate;
        this.email = email;
        this.code = null;
    }
    
    public User(userType type, String FirstName, String LastName, LocalDate birthDate, String email, String code) {
        this.type = type;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.birthDate = birthDate;
        this.email = email;
        this.code = code;
    }
    
    public enum userType {
        STUDENT, TEACHER, ADMIN
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public userType getType() {
        return type;
    }

    public void setType(userType type) {
        this.type = type;
    }
    
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public void addCourse(Course c) {
        courses.add(c);
    }

    public Set<Course> getCourse() {
        return courses;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", type=" + type + ", FirstName=" + FirstName + ", LastName=" + LastName + ", birthDate=" + birthDate + ", email=" + email + ", code=" + code + ", courses=" + courses + ']';
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.FirstName, other.FirstName)) {
            return false;
        }
        if (!Objects.equals(this.LastName, other.LastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.birthDate, other.birthDate);
    }
    
}
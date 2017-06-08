/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uia.slit.data;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author evenal
 */
@Entity
public class Student {
    @Id @GeneratedValue
    private long id;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date registered;
    
    public Student() {
    }
    
    public Student(String name) {
        this.name = name;
        this.registered = new Date();
    }

    public String getName() {
        return name;
    }

    public Date getRegistered() {
        return registered;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Student other = (Student) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}

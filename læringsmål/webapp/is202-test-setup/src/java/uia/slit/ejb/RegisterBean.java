/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uia.slit.ejb;

import uia.slit.data.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * This is the bean class of an Enterprise JavaBean.
 * The stateless annotation marks the class as a stateless session
 * EJB.
 * 
 * @author evenal
 */
@Stateless
public class RegisterBean implements RegisterLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void register(String name) {
        System.out.println("uia.slit.ejb.RegisterBean.register():31");
        if (null == name) name = "anonymous";
        Student visitor = new Student(name);
        System.out.println("uia.slit.ejb.RegisterBean.register():34");
        em.persist(visitor);
        System.out.println("uia.slit.ejb.RegisterBean.register():36");
    }

    @Override
    public List<Student> getStudents() {
        System.out.println("uia.slit.ejb.RegisterBean.getRegistered():39");
        TypedQuery<Student> q = em.createQuery("select v from Student v",
                Student.class);
        System.out.println("uia.slit.ejb.RegisterBean.getRegistered():42");
        List<Student> visitors = q.getResultList();
        System.out.println("uia.slit.ejb.RegisterBean.getRegistered():");
        System.out.println("visitors returned "+visitors.size());
        return visitors;
    }
}

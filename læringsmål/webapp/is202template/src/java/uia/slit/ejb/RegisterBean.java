/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uia.slit.ejb;

import uia.slit.data.Visitor;
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
        System.out.println("uia.slit.ejb.RegisterBean.register()");
        if (null == name) name = "anonymous";
        Visitor visitor = new Visitor(name);
        em.persist(visitor);
    }

    @Override
    public List<Visitor> getVisitors() {
        System.out.println("uia.slit.ejb.RegisterBean.getRegistered()");
        TypedQuery<Visitor> q = em.createQuery("select v from Visitor v",
                Visitor.class);
        List<Visitor> visitors = q.getResultList();
        System.out.println("visitors returned "+visitors.size());
        return visitors;
    }
}

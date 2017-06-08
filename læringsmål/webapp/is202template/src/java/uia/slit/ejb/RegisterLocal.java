/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uia.slit.ejb;

import uia.slit.data.Visitor;
import java.util.List;
import javax.ejb.Local;

/**
 * This is a local interface. It defines the methods that are
 * available to a local application client. The interface does not
 * say which class it is an interface for. This is done in the bean
 * class. The bean class must implement the local and remote interfaces
 * that it supports
 * 
 * @author evenal
 */
@Local
public interface RegisterLocal {
    void register(String name);
    public List<Visitor> getVisitors();
}

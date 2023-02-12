package com.orios.hibernate.ddloperations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author obedrios
 */
public class PerformDDLOperations {
    
    public static void main(String[] args) {
         SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.ddl.xml")
                .buildSessionFactory();
         
         Session session = sessionFactory.openSession();
         session.close();
    }
    
}

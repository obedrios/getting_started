package com.orios.hibernatepostgresqlquikstart;

import org.hibernate.Session;

/**
 *
 * @author obedrios
 * Important Note:
 * To run, use the following argument to avoid runtime unhandled expection.
 * To know more about this exception see the below references.
 * --add-opens java.base/java.lang=ALL-UNNAMED
 * 
 * Web Resources:
 * https://www.springcloud.io/post/2022-07/inaccessibleobjectexception/#gsc.tab=0
 * https://www.sqlservercentral.com/articles/postgresql-hibernate-integration
 * https://www.digitalocean.com/community/tutorials/hibernate-tutorial-for-beginners
 */
public class HibernatePostgresqlQuikStartApp {
    
    public static void main(String[] args) {
        
        Employee emp = new Employee();
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setEmail("johndoe@unknown.com");

        //Get Session
        //Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession(); //Works Good
        Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession(); //Works Good        
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(emp);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Employee ID=" + emp.getId());

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().getCurrentSession().close();        
        
    }
}

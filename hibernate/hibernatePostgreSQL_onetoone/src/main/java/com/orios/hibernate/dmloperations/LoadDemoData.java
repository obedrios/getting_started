package com.orios.hibernate.dmloperations;

import com.orios.hibernate.HibernateUtil;
import com.orios.hibernate.models.Department;
import com.orios.hibernate.models.Employee;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author obedrios
 */
public class LoadDemoData {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession(); //Works Good
        //start transaction
        session.beginTransaction();
        
        Department d1 = new Department();        
        d1.setName("Engineering");
        
        Department d2 = new Department();
        d2.setName("Information Technologies");
        
        Department d3 = new Department();
        d3.setName("Sales");
        
        //
        Employee e1 = new Employee();
        e1.setName("John Doe");
        e1.setEmail("johndoe@unknown.com");
        e1.setDepartment(d1);
        
        Employee e2 = new Employee();
        e2.setName("Jane Doe");
        e2.setEmail("janedoe@unknown.com");
        e2.setDepartment(d2);
        
        Employee e3 = new Employee();
        e3.setName("Lance Henricksen");
        e3.setEmail("henricksen@millenium.org");
        e3.setDepartment(d2);
        
        session.save(d1);
        session.save(d2);
        session.save(d3);
        
        session.save(e1);
        session.save(e2);
        session.save(e3);

        //Commit transaction
        session.getTransaction().commit();

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().getCurrentSession().close();
    }

}

package com.orios.hibernate.ddloperations;

import com.orios.hibernate.HibernateUtil;
import com.orios.hibernate.models.Department;
import com.orios.hibernate.models.Employee;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

/**
 *
 * @author obedrios
 */
public class QueryOperations {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession(); //Works Good
        //start transaction
        session.beginTransaction();

        // Simple query list
        List<Employee> employees = session.createQuery("FROM Employee").getResultList();
        employees.stream().forEach(System.out::println);

        // Query using Criteria builder API
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> root = criteriaQuery.from(Employee.class);        
        Join<Employee, Department> join = root.join("department");        
        criteriaQuery.where(criteriaBuilder.equal(join.get("name"), "Information Technologies"));
        session.createQuery(criteriaQuery).getResultList().stream().forEach(System.out::println);
        
        
        //Commit transaction
        session.getTransaction().commit();

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().getCurrentSession().close();
    }

}

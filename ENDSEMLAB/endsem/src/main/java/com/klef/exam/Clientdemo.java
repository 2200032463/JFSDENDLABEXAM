package com.klef.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Clientdemo {
    public static void main(String[] args) {
       
        int departmentId = 5; 
        String updatedName = "Artificial Intelligence Department";
        String updatedLocation = "Seattle";

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        
        session.beginTransaction();

        try {
            department dept = session.get(department.class, departmentId); 
            if (dept != null) {
                dept.setName(updatedName);
                dept.setLocation(updatedLocation);

                session.update(dept); 
                session.getTransaction().commit(); 

                System.out.println("Department updated successfully!");
            } else {
                System.out.println("Department with ID " + departmentId + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback(); 
        } finally {
            session.close();
            factory.close();
        }
    }
}
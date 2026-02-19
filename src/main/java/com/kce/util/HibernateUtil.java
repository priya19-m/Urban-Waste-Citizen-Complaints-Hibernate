package com.kce.util; // Unga package name-ku mathiruken

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // "hibernate.cfg.xml" thaan standard name. 
            // Neenga "hibernate.cfg2.xml" nu vechiruntha athaiye use pannunga.
            sessionFactory = new Configuration().configure("hibernate.cfg2.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("❌ Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
        getSessionFactory().close();
    }
}
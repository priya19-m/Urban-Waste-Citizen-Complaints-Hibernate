package com.kce.dao;

import com.kce.bean.Citizen;
import com.kce.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class CitizenDAO {

    public Citizen findCitizen(String citizenID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Citizen.class, citizenID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Citizen> viewAllCitizens() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Citizen order by citizenID", Citizen.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertCitizen(Citizen citizen) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(citizen); 
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCitizenStatus(String citizenID, String status) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Citizen citizen = session.get(Citizen.class, citizenID);
            if (citizen != null) {
                citizen.setStatus(status);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCitizen(String citizenID) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Citizen citizen = session.get(Citizen.class, citizenID);
            if (citizen != null) {
                session.remove(citizen);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
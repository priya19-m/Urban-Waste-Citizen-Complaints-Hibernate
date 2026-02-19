package com.kce.dao;

import com.kce.bean.WasteServiceRow;
import com.kce.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class WasteServiceDAO {

    // Sequence generation is handled by @GeneratedValue in Bean
    public boolean insertServiceRow(WasteServiceRow row) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(row);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public WasteServiceRow findServiceRow(int serviceRowID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(WasteServiceRow.class, serviceRowID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<WasteServiceRow> findOpenComplaintsByCitizen(String citizenID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from WasteServiceRow where citizenID = :cid";
            Query<WasteServiceRow> query = session.createQuery(hql, WasteServiceRow.class);
            query.setParameter("cid", citizenID);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<WasteServiceRow> findComplaintsByCitizen(String citizenID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from WasteServiceRow where citizenID = :cid and complaintStatus in ('OPEN', 'IN_PROGRESS')";
            Query<WasteServiceRow> query = session.createQuery(hql, WasteServiceRow.class);
            query.setParameter("cid", citizenID);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateComplaintStatusAndClosure(int serviceRowID, String newStatus, String closureRemarks) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            WasteServiceRow row = session.get(WasteServiceRow.class, serviceRowID);
            if (row != null) {
                row.setComplaintStatus(newStatus);
                row.setClosureRemarks(closureRemarks);
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.beans.Personne;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author kawta
 */
public class FemmeService implements IDao<Femme> {
    
    @Override
    public boolean create(Femme o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public Femme getById(int id) {
        Femme femme = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femme = (Femme) session.get(Femme.class, id);
            tx.commit();
            return femme;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return femme;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<Femme> getAll() {
        List<Femme> femmes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femmes = session.createQuery("from Femme").list();
            tx.commit();
            return femmes;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return femmes;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    
    public int nombredEnfantDuneFemme(Femme femme, Date minDate, Date maxDate){
//        AtomicInteger nombredEnfant = new AtomicInteger(0);
//        
//        femme.getMariage().stream().forEach((f) -> {
//            if((f.getDateDebut().compareTo(minDate) == 1 || f.getDateDebut().compareTo(maxDate) == 0 )&&
//                (f.getDateDebut().compareTo(maxDate) == -1 || f.getDateDebut().compareTo(maxDate) == 0)
//            ){
//                nombredEnfant.addAndGet(f.getNbrEnfant());
//            }
//        });
//        
//        return nombredEnfant.get();
//        
        Session session = null;
        Transaction tx = null;
        
        String hql = "SELECT SUM(m.nombredEnfant) FROM Mariage m " +
                 "WHERE m.id.femme = :femme " +
                 "AND (m.dateDebut BETWEEN :minDate AND :maxDate)";
        int result = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Object resultObject = session.createQuery(hql)
                .setParameter("femme", femme)
                .setParameter("minDate", minDate)
                .setParameter("maxDate", maxDate)
                .uniqueResult();
            
            if (resultObject != null) {
                result = ((Long) resultObject).intValue();
            }
            
           tx.commit();
           
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return result;
    }
    
    public List<Femme> femmeMarieesAuMoisDeuxFois(){
        List<Femme> femmes = new ArrayList<>();
        Session session = null;
        Transaction tx = null;
        
        String requet = "SELECT p, COUNT(m.femme) " +
                        "FROM Personne p, Mariage m " +
                        "WHERE p.id = m.femme " +
                        "GROUP BY m.femme " +
                        "HAVING COUNT(m.femme) >= 2";
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            List<Object[]> results = session.createQuery(requet).list();
            tx.commit();
            
            results.stream().map((result) -> (Femme) result[0]).forEach((femme) -> {
                femmes.add(femme);
            });
            
        }catch(HibernateException ex){
            if(tx != null){
                tx.rollback();
            }
        }finally{
            if(session != null){
                session.close();
            }
        }
        return femmes;
    }
}

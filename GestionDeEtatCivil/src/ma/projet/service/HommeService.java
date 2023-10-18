/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.beans.Personne;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

/**
 *
 * @author kawta
 */
public class HommeService implements IDao<Homme>{
    @Override
    public boolean create(Homme o) {
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
    public Homme getById(int id) {
        Homme homme = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            homme = (Homme) session.get(Homme.class, id);
            tx.commit();
            return homme;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }

            return homme;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<Homme> getAll() {
        List<Homme> hommes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            hommes = session.createQuery("from Homme").list();
            tx.commit();
            return hommes;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return hommes;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    // afficher les épouses d’un homme passé
    public void affichierEpousesPasseDunHomme(Homme homme, Date minDate, Date maxDate){
        
        
        System.out.println("The wives of this man are " + homme.getNom());
        
        homme.getMariage().stream().forEach((h) -> {
            if( h.getDateFin() != null && 
                (h.getDateDebut().compareTo(minDate) == 1|| h.getDateDebut().compareTo(maxDate) == 0 ) &&
                (h.getDateFin().compareTo(maxDate) == -1 || h.getDateFin().compareTo(maxDate) == 0)
            ){
                System.out.println(h.getFemme());
            }    
        });
     
        
    }
       
    

    public int getNbrHommesMaries4Femmes(Date minDate, Date maxDate){
        Session session = null;
        Transaction tx = null;
      
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            DetachedCriteria subquery = DetachedCriteria.forClass(Mariage.class, "subAlias")
                .setProjection(Projections.countDistinct("subAlias.femme"))
                .add(Restrictions.eqProperty("subAlias.homme", "alias.homme"));

            Criteria criteria = session.createCriteria(Mariage.class, "alias");

            criteria.add(Restrictions.between("dateDebut", minDate, maxDate));
//            criteria.add(Restrictions.gt("dateDebut", minDate));
//            criteria.add(Restrictions.gt("dateFin", maxDate));
//            criteria.add(Restrictions.or());
//            
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.groupProperty("alias.homme")));

            criteria.add(Subqueries.eq(4L, subquery));
            
            
            List results = criteria.list();
            
            tx.commit();
            
            return results.size();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            if(session != null)
                session.close(); 
        }
        return 0;
    }
    
    public void getLesMariagesDunHommePassé(Homme h){
        
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.println("Name : " + h.getNom());
         
        System.out.println("Ongoing Marriages :"); 
        int s1 = 1;
        for(Mariage m : h.getMariage()){
            if(m.getDateFin() == null){
                
                
                String dateDebut = dateFormat.format(m.getDateDebut());
                System.out.println("\t "+ s1 + ". Femme: " + m.getFemme().getNom() + "\t\t Date Début: " + dateDebut + "  Nombre dEnfants: " + m.getNbrEnfant());
                s1++;
            }
        }
        
        System.out.println("Failed Marriages : ");
        s1 = 1;
        for(Mariage m : h.getMariage()){
            if(m.getDateFin() != null){
                String dateDebut = dateFormat.format(m.getDateDebut());
                 String dateFin = dateFormat.format(m.getDateFin());
                System.out.println("\t "+ s1 + ". Femme: " + m.getFemme().getNom() + "\tDate Début: " + dateDebut + "  Date Fin: " + dateFin + "  Nbr Enfants: " + m.getNbrEnfant());
                s1++;
            }
        }
    }
   
}

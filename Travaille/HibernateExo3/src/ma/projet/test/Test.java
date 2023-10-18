package ma.projet.test;

import java.util.Calendar;
import java.util.Date;
import ma.projet.entities.Employe;
import ma.projet.entities.EmployeTache;
import ma.projet.entities.EmployeTacheKey;
import ma.projet.entities.Projet;
import ma.projet.entities.Tache;
import ma.projet.service.EmployeService;
import ma.projet.service.EmployeTacheService;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {
 public static Date generateDateFormat(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        Date date = calendar.getTime();
        return date;
    }
    public static void main(String[] args) {
      EmployeService es=new EmployeService();
      EmployeTacheService ets=new EmployeTacheService();
      ProjetService ps=new ProjetService();
      TacheService ts=new TacheService(); 
      /*
      Employe employe1=new Employe("kumyun","zerat",1064);
      es.create(employe1);
      
      Projet projet1=new Projet("dance",generateDateFormat(2023,03,01),generateDateFormat(2023,10,01),es.findById(1));
      ps.create(projet1);
      
      Tache tache1=new Tache("balley",generateDateFormat(2002,10,01),generateDateFormat(2023,01,01),70,ps.findById(1));
      ts.create(tache1);
      
      EmployeTache  employeetache1 =new EmployeTache(new EmployeTacheKey(1,1,generateDateFormat(2012,11,06),generateDateFormat(2023,10,16)));
      ets.create(employeetache1);      
     */
     // es.afficherTachesEmploye(1);
     
      es.afficherProjetsEmploye(1);
      
      //ps.afficherTachesPlanifieesProjet(1) ;

      //ps.afficherTachesRealiseesProjet(1);
      
    }
}

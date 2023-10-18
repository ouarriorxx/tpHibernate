package ma.projet.test;

import java.util.Date;
import ma.projet.entities.Produit;
import ma.projet.service.ProduitService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {

    public static void main(String[] args) throws ParseException {
        ProduitService p = new ProduitService();
/*
       Produit p1 = new Produit("Zara","fx5g",new Date(),10,"des1");
       p.create(p1);
       Produit p2 = new Produit("Hermes","burkini",new Date(),20,"des2");
       p.create(p2);
       Produit p3 = new Produit("adidas","stansmith",new Date(),30,"des3");
       p.create(p3);
       Produit p4 = new Produit("nike","airjordan",new Date(),40,"des4");
       p.create(p4);
       Produit p5 = new Produit("Gant","tshirt23",new Date(),50,"des5");
       p.create(p5);
*/
   
/*     for (Produit prod : p.findAll()) {
            System.out.println("produit : " + prod.getMarque() +" , "+ prod.getReference());
        }
*/

/*      Produit prod = p.findById(2);
        System.out.println("Marque: "+ prod.getMarque()+" , Reference: "+ prod.getReference()+" , DateAchat : "+ prod.getDateAchat()+" , Prix : "+ prod.getPrix()+" , Designation : "+prod.getDesignation());
*/

//        p.delete(p.findById(3));

/*        Produit prod = p.findById(1);
        prod.setPrix(200);
        p.update(prod);
*/

/*        System.out.println(" les produits qui ont des prix sup que 100DH ");
        for (Produit prod : p.findAll()) {
            if (prod.getPrix() > 100) {
                   System.out.println("le produit :"+prod.getMarque() +" , "+ prod.getReference());
            }
        }        
*/

/*      Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("entrer date debut sous cette forme yyyy-MM-dd");
        String ddebut = sc.nextLine();
        Date ddebutFormat = dateFormat.parse(ddebut);

        System.out.println("entrer date fin sous cette forme yyyy-MM-dd");
        String dfin = sc.nextLine();
        Date dfinFormat = dateFormat.parse(dfin);

        System.out.println(" la liste des produits Commander entre " + ddebut + "et" + dfin + " sont:");
        for (Produit prod : p.findAll()) {
            if (prod.getDateAchat().after(ddebutFormat) && prod.getDateAchat().before(dfinFormat)) {
                System.out.println("le produit:" + prod.getMarque()+" , "+prod.getReference());
            }
        }*/

    }

}

package com.smartlogi;

import com.smartlogi.entity.Colis;
import com.smartlogi.entity.Livreur;
import com.smartlogi.entity.StatutColis;
import com.smartlogi.service.ColisService;
import com.smartlogi.service.LivreurService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SmartDeliveryApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        LivreurService livreurService = context.getBean("livreurService", LivreurService.class);
        ColisService colisService = context.getBean("colisService", ColisService.class);

        System.out.println("\n=== TEST CRUD SMART DELIVERY SYSTEM (XML CONFIG) ===\n");

        System.out.println("--- Création de livreurs ---");
        Livreur livreur1 = new Livreur("Karimi", "karim", "Camionnette", "0612345678");
        Livreur livreur2 = new Livreur("Hedouchi", "Hamza", "Scooter", "0687654321");

        livreur1 = livreurService.creerLivreur(livreur1);
        livreur2 = livreurService.creerLivreur(livreur2);

        System.out.println("Livreur créé: " + livreur1);
        System.out.println("Livreur créé: " + livreur2);

        System.out.println("\n--- Liste de tous les livreurs ---");
        livreurService.obtenirTousLivreur().forEach(System.out::println);

        System.out.println("\n--- Création de colis ---");
        Colis colis1 = new Colis("Alice Dubois", "12 Rue de Paris", 2.5, StatutColis.PREPARATION);
        Colis colis2 = new Colis("Bob Lambert", "45 Avenue Victor Hugo", 1.8, StatutColis.PREPARATION);
        Colis colis3 = new Colis("Claire Moreau", "78 Boulevard Voltaire", 3.2, StatutColis.PREPARATION);

        colis1 = colisService.EnregistrerColis(colis1, livreur1.getId());
        colis2 = colisService.EnregistrerColis(colis2, livreur1.getId());
        colis3 = colisService.EnregistrerColis(colis3, livreur2.getId());

        System.out.println("Colis créé: " + colis1);
        System.out.println("Colis créé: " + colis2);
        System.out.println("Colis créé: " + colis3);

        System.out.println("\n--- Mise à jour du statut ---");
        colis1 = colisService.MidifierStatus(colis1.getId(), StatutColis.EN_TRANSIT);
        System.out.println("Statut mis à jour: " + colis1);

        System.out.println("\n--- Colis assignés au livreur " + livreur1.getNom() + " ---");
        colisService.listToutColisParLivreur(livreur1.getId()).forEach(System.out::println);


        System.out.println("\n--- Statistiques ---");
        System.out.println("Nombre total de livreurs: " + livreurService.obtenirTousLivreur().size());
        System.out.println("Nombre total de colis: " + colisService.ObtenirToutColis().size());

        System.out.println("\n=== FIN DES TESTS ===");

        ((ClassPathXmlApplicationContext) context).close();
    }
}

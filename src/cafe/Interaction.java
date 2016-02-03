/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Maxime BLAISE
 */
public class Interaction {

    /**
     * Valeur maximale à ne pas dépasser.
     */
    public static int valeurMax = 30000;
    
    /**
     * Scanner pour lire au clavier.
     */
    public static Scanner sc = new Scanner(System.in);

    /**
     * Demande au clavier un nombre entier valide dans un certain intervalle.
     *
     * @param min Min
     * @param max Max
     * @return Nombre entier valide
     */
    public static int demanderEntierEntreIntervalle(int min, int max) {
        while (true) {
            int res = demanderEntier();
            if (res < min || res > max) {
                System.err.println("Erreur: veuillez entrer un nombre entre " + min + " et " + max);
            } else {
                return res;
            }
        }
    }

    /**
     * Demande au clavier un nombre entier valide, avec un minimum.
     *
     * @param min Min
     * @return Nombre entier valide
     */
    public static int demanderEntierAvecMin(int min) {
        while (true) {
            int res = demanderEntier();
            if (res < min) {
                System.err.println("Erreur: veuillez entrer un nombre supérieur ou égal à " + min);
            } else {
                if (res > Interaction.valeurMax) {
                    System.err.println("Erreur: pas trop grand quand même");
                } else {
                    return res;
                }
            }
        }
    }

    /**
     * Demande au clavier un nombre entier.
     *
     * @return Entier valide
     */
    public static int demanderEntier() {
        while (true) {
            try {
                int res = sc.nextInt();
                return res;
            } catch (InputMismatchException e) {
                System.err.println("Erreur: veuillez entrer un nombre entier valide.");
                sc.nextLine();
            }
        }
    }

    /**
     * Demande une chaîne au clavier avec une taille maximale.
     *
     * @param max Max
     * @return Chaine valide
     */
    public static String demanderStringAvecLongueurMax(int max) {
        while (true) {
            String str = sc.nextLine();
            if (str.length() > 30) {
                System.err.println("Erreur: veuillez entrer une chaîne inférieur à " + max + " caractères.");
            } else if (!str.equals("")) {
                return str;
            }
        }
    }

    /**
     * Demande une string de longeur 1 à l'utilisateur.
     * Seul le premier char est pris en compte.
     * @return first char or null
     */
    public static int demanderIntSansInsister(){
        sc.next();
        int val = sc.nextInt();

        return val;
    }
}

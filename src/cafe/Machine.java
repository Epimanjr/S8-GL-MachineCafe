/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author maxime
 */
public class Machine {

    private static String menu = ""
            + "\n 1 - Acheter une boisson"
            + "\n 2 - Ajouter une boisson"
            + "\n 3 - Modifier une boisson"
            + "\n 4 - Supprimer une boisson"
            + "\n 5 - Ajouter un ingredient"
            + "\n 6 - Afficher le stock d'ingredients"
            + "\n 7 - Quitter."
            + "\n ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer choix = 0;
        do {
            System.out.println(menu);
            System.out.println("=> ");

            try {
                choix = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Erreur, veuillez entrer un nombre entier.");
                sc.nextLine();
            }

            switch (choix) {
                case 2:
                    stock.StockBoisson.getStock().ajouterBoisson();
                    break;
                case 3:
                    stock.StockBoisson.getStock().modifierBoisson();
                    break;
                case 4:
                    stock.StockBoisson.getStock().supprimerBoisson();
                    break;
                case 5:
                    stock.StockIngredient.getStock().ajouterIngredient();
                    break;
                case 6:
                    stock.StockIngredient.getStock().afficherStockIngredient();
                    break;
                default:
                    break;
            }

        } while (choix != 7);
    }

}

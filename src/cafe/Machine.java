/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Machine.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(menu);
            System.out.println("=> ");

            choix = Interaction.demanderEntier();

            switch (choix) {
                case 1:
                    Machine.acheterBoisson();
                    break;
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
                case 1212:
                    Machine.ajouteBeaucoupDeChose();
                    break;
                default:
                    break;
            }

        } while (choix != 7);
    }

    /**
     * Achat d'une boisson
     */
    public static void acheterBoisson() {
        if (stock.StockBoisson.getStock().getBoissons().isEmpty()) {
            System.err.println("Erreur: aucune boisson à acheter.");
        } else {
            System.out.println("Quelle boisson voulez-vous acheter ?");
            // Demande
            stock.StockBoisson.getStock().listerBoissons();
            Boisson b = stock.StockBoisson.getStock().demanderQuelleBoisson();

            if (b != null) {
                // Achat
                int argent = demanderArgent();
                System.err.println("TODO");
                //b.acheter(argent);
            }
        }
    }

    /**
     * Demande à l'utilisateur d'insérer l'argent.
     *
     * @return Nombre
     */
    private static int demanderArgent() {
        System.out.println("Entrer combien de monnaie : ");
        return Interaction.demanderEntierAvecMin(1);
    }

    /**
     * Ajoute beaucoup de choses pour simuler
     */
    public static void ajouteBeaucoupDeChose() {
        System.out.println("Ajout de beaucoup de choses !!");
        // Ajout d'ingrédients
        stock.StockIngredient.getStock().ajouterIngredient(Ingredient.LAIT, 10);
        stock.StockIngredient.getStock().ajouterIngredient(Ingredient.SUCRE, 10);
        stock.StockIngredient.getStock().ajouterIngredient(Ingredient.CHOCOLAT, 10);
        stock.StockIngredient.getStock().ajouterIngredient(Ingredient.CAFE, 10);
        // Ajout de 2 boissons
        HashMap<Ingredient, Integer> recetteBoisson1 = new HashMap<>();
        recetteBoisson1.put(Ingredient.LAIT, 6);
        recetteBoisson1.put(Ingredient.CHOCOLAT, 3);
        recetteBoisson1.put(Ingredient.SUCRE, 1);
        recetteBoisson1.put(Ingredient.CAFE, 0);
        stock.StockBoisson.getStock().ajouterBoisson("Chocolat", 2, recetteBoisson1);
        HashMap<Ingredient, Integer> recetteBoisson2 = new HashMap<>();
        recetteBoisson2.put(Ingredient.LAIT, 2);
        recetteBoisson2.put(Ingredient.CHOCOLAT, 0);
        recetteBoisson2.put(Ingredient.SUCRE, 1);
        recetteBoisson2.put(Ingredient.CAFE, 7);
        stock.StockBoisson.getStock().ajouterBoisson("Café au lait", 2, recetteBoisson2);
    }
}

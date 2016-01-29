package stock;

import cafe.Ingredient;
import cafe.Interaction;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Maxime BLAISE
 */
public class StockIngredient {

    /**
     * Liste des ingrédients avec son stock.
     */
    private final HashMap<Ingredient, Integer> ingredients = new HashMap<>();

    private static final StockIngredient stock = new StockIngredient();

    /**
     * Initialise les stock
     */
    public StockIngredient() {
        // Initialise la map avec toutes les valeurs de l'énumération
        for (Ingredient i : Ingredient.values()) {
            ingredients.put(i, 0);
        }
    }

    /**
     * Récupère le stock d'ingrédients.
     *
     * @return le stock
     */
    public static StockIngredient getStock() {
        return stock;
    }

    /**
     * Méthode qui ajoute un ingrédient.
     */
    public void ajouterIngredient() {
        // Demande l'ingrédient
        Ingredient ingredient = demanderQuelIngredient();
        // Demande la quantité
        int quantite = demanderQuantiteIngredient();
        // Ajoute l'ingrédient
        ajouterIngredient(ingredient, quantite);
    }

    /**
     * Ajoute un ingrédient.
     *
     * @param i Ingrédient concerné
     * @param quantite Quantité à ajouter
     */
    public void ajouterIngredient(Ingredient i, int quantite) {
        if (quantite > 0) {
            int quantiteInitiale = this.ingredients.get(i);
            this.ingredients.replace(i, quantiteInitiale + quantite);
            System.out.println(i.toString() + ":" + quantite + " ajouté(s).");
        }
    }

    /**
     * Demande quel ingrédient.
     *
     * @return Numéro de l'ingrédient
     */
    public static Ingredient demanderQuelIngredient() {
        // Affichage des ingrédients
        Ingredient[] tabIngredients = Ingredient.values();
        System.out.println("Quel ingrédient ?");
        for (int i = 0; i < tabIngredients.length; i++) {
            System.out.println((i + 1) + "/ " + tabIngredients[i].toString());
        }
        // Demande du numéro
        while (true) {
            System.out.print("=> ");
            int numeroIngredient = Interaction.demanderEntierEntreIntervalle(1, tabIngredients.length);
            return tabIngredients[numeroIngredient - 1];
        }
    }

    /**
     * Demande d'une quantité.
     *
     * @return Entier valide
     */
    public static int demanderQuantiteIngredient() {
        System.out.println("Quelle quantité (>=0 obligatoirement) ?");
        return Interaction.demanderEntierAvecMin(0);
    }

    /**
     * Récupère la quantité d'un ingrédient.
     *
     * @param i Ingredient
     * @return Quantité
     */
    public int getQuantite(Ingredient i) {
        return this.ingredients.get(i);
    }

    /**
     * Enlève la quantité d'un ingrédient (lors d'un achat)
     *
     * @param i Ingrédient
     * @param q Quantité
     */
    public void enleverQuantite(Ingredient i, int q) {
        int quantite = this.ingredients.get(i);
        this.ingredients.replace(i, quantite - q);
    }

    /**
     * Affiche le stock des ingrédients de la machine à café.
     */
    public void afficherStockIngredient() {
        System.out.println("Stock d'ingrédients de la machine : ");
        Set set = this.ingredients.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Ingredient i = (Ingredient) it.next();
            int quantite = this.ingredients.get(i);
            System.out.println(i.toString() + " => " + quantite);
        }
    }
}

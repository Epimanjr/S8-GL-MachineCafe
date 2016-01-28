package stock;

import cafe.Ingredient;
import java.util.HashMap;
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
    private StockIngredient() {
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
        Ingredient[] tabIngredients = Ingredient.values();
        System.out.println("Quel ingrédient ?");
        for(int i=0;i<tabIngredients.length;i++) {
            System.out.println((i+1) + "/ " + tabIngredients[i].toString());
        }
        Scanner sc = new Scanner(System.in);
        // Demande l'ingrédient
        boolean choixCorrect = false;
        int choix = 0;
        while(!choixCorrect) {
            choix = sc.nextInt();
            if(choix < 1 || choix > tabIngredients.length) {
                System.err.println("Choix incorrect.");
            } else {
                choixCorrect = true;
            }
        }
        Ingredient ingredient = tabIngredients[choix-1];
        // Demande la quantité
        System.out.println("Quelle quantité ? (>0 obligatoirement)");
        choixCorrect = false;
        while(!choixCorrect) {
            choix = sc.nextInt();
            if(choix <= 0) {
                System.err.println("Choix incorrect.");
            } else {
                choixCorrect = true;
            }
        }
        // Ajoute l'ingrédient
        ajouterIngredient(ingredient, choix);
    }

    public void ajouterIngredient(Ingredient i, int quantite) {
        if (quantite > 0) {
            int quantiteInitiale = this.ingredients.get(i);
            this.ingredients.replace(i, quantiteInitiale + quantite);
        }
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
     * Affiche le stock des ingrédients de la machine à café.
     */
    public void afficherStockIngredient() {
        System.out.println("Stock d'ingrédients de la machine : ");
        Set set = this.ingredients.keySet();
        Iterator it = set.iterator();
        while(it.hasNext()) {
            Ingredient i = (Ingredient) it.next();
            int quantite = this.ingredients.get(i);
            System.out.println(i.toString() + " => " + quantite);
        }
    }
}

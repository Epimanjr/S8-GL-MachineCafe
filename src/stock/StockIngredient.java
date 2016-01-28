package stock;

import cafe.Ingredient;
import java.util.HashMap;

/**
 *
 * @author Maxime BLAISE
 * @author Geoffrey GAILLARD
 */
public class StockIngredient {

    /**
     * Liste des ingrédients avec son stock.
     */
    private final HashMap<Ingredient, Integer> ingredients;

    private static final StockIngredient stock = new StockIngredient();

    /**
     * Initialise les stock
     */
    private StockIngredient() {
        // Initialise la map avec toutes les valeurs de l'énumération
        ingredients = new HashMap<>();
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
        //TODO
    }
    
    public void ajouterIngredient(Ingredient i, int quantite) {
        //TODO
    }
}

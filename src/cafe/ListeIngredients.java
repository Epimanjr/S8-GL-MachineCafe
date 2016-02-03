package cafe;

import java.util.ArrayList;

public class ListeIngredients extends ArrayList<Ingredient> {

    /**
     * Notre liste
     */
    private static ListeIngredients liste = new ListeIngredients();

    private ListeIngredients() {
        init();
    }

    /**
     * Permet de récupérer notre liste d'ingrédients à partir de l'extérieur.
     */
    public static ListeIngredients getListe() {
        return liste;
    }

    private void init() {
        this.add(new Ingredient("Café"));
        this.add(new Ingredient("Lait"));
        this.add(new Ingredient("Sucre"));
        this.add(new Ingredient("Chocolat"));
        this.add(new Ingredient("Thé"));
    }
}

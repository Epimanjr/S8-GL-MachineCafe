package cafe;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;


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

    public void insert() {
        for(Ingredient i : this) {
            i.insert();
        }
    }


    private void init() {
        this.add(new Ingredient("Café"));
        this.add(new Ingredient("Lait"));
        this.add(new Ingredient("Sucre"));
        this.add(new Ingredient("Chocolat"));
        this.add(new Ingredient("Thé"));
    }

    public static Optional<Ingredient> obtenirSingleton(String nom){
        return getListe()
                .stream()
                .filter(i -> i.getName().equals(nom))
                .findFirst();
    }
}

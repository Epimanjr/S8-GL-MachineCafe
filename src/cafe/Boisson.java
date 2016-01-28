package cafe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Boisson {

    private int prix;
    private String nom;

    /**
     * Quantité de chaque ingredient
     */
    private HashMap<Ingredient, Integer> recette;

    public Boisson(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
        this.recette = new HashMap<>();

        for (Ingredient i : Ingredient.values()) {
            this.recette.put(i, 0);
        }
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setIngredient(Ingredient i, Integer qte) {
        //this.recette.replace(i, qte);
        // TODO
    }

    public boolean estPossible(stock.StockIngredient stock) {
        return false;
        // TODO
    }

    /**
     * Méthode appelée lors de l'achat de la boisson Vérifie la possibilité, et
     * enlève les ingrédients du stock Calcul la monnaie à rendre et la rend.
     *
     * @param argentDonne Argent donné par l'acheteur
     * @return L'argent à rendre
     */
    public int acheter(int argentDonne) {
        // TODO
        // Vérification de la possibilité
        
        // Vérification de l'argent (ça suffit ?)
        
        // Enlèvement des ingrédients du stock.
        
        // Monnaie à rendre
        return 0;
    }

    @Override
    public String toString() {
        String res = this.nom + "(Prix:" + this.prix + ") Ingrédients{";
        Set set = recette.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Ingredient i = (Ingredient) it.next();
            res += i.toString() + ":" + recette.get(i) + "-";
        }
        res += "}";
        return res;
    }

}

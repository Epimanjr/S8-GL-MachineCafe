package cafe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import exception.MontantInsufisantException;
import exception.StockInsufisantException;

import stock.StockIngredient;
import stock.StockBoisson;

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

        for (Ingredient i : ListeIngredients.getListe()) {
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

    /**
     * Modification d'un ingrédient de la boisson
     *
     * @param i Ingrédient
     * @param qte Quantité
     */
    public void setIngredient(Ingredient i, Integer qte) {
        this.recette.replace(i, qte);
    }

    /**
     * Récupère la quantité d'un ingrédient.
     *
     * @param i Ingrédient
     * @return Quantité
     */
    public int getIngredient(Ingredient i) {
        return this.recette.get(i);
    }

    /**
     * Tester la possiblité de la boisson en fonction du stock. Pas de
     * paramètre, car les stocks sont accessibles partout (Singleton)
     *
     * @param stock Stock à utiliser
     * @return Vrai/Faux
     */
    public boolean estPossible(StockIngredient stock) {
        //return false;
        // TODO
        // Parcourir la liste des ingrédients

        boolean possible = true;

        for (Ingredient i : recette.keySet()) {
            int stockDispo = stock.getQuantite(i);
            int besoin = recette.get(i);
            if (stockDispo < besoin) {
                possible = false;
                break;
            }
        }

        return possible;
    }

    /**
     * Méthode appelée lors de l'achat de la boisson Vérifie la possibilité, et
     * enlève les ingrédients du stock Calcul la monnaie à rendre et la rend.
     *
     * @param argentDonne Argent donné par l'acheteur
     * @param stock Stock à utiliser
     * @return L'argent à rendre
     * @throws MontantInsufisantException
     * @throws StockInsufisantException
     */
    public int acheter(int argentDonne, StockIngredient stock, int sucre)
        throws MontantInsufisantException,
                StockInsufisantException
    {
        int prix = getPrix();  // on ne sait jamais si un calcul
                               // de TVA traine dans un getter

        if(argentDonne < prix){
            throw new MontantInsufisantException(getPrix());
        }
        if(!estPossible(stock)){
            throw new StockInsufisantException(getIngredientManquant(stock));
        }
        // on est OK !


        // Calcul du sucre effectif à enlever
        Ingredient ingredientSucre = new Ingredient("Café");
        int sucreEffectif = sucre;
        if(sucre == -1){
            sucreEffectif = recette.get(ingredientSucre);
        }

        // Enlèvement des ingrédients du stock.
        for(Ingredient i : recette.keySet()){
            if(i.equals(ingredientSucre)){
                stock.enleverQuantite(i, sucreEffectif);
            }
            else{
                stock.enleverQuantite(i, recette.get(i));
            }
        }

        // Monnaie à rendre
        return argentDonne - prix;
    }

    public boolean verifierBoisson() {
        return StockBoisson.testerRecetteBoisson(this.recette);
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

    /**
     * Donne l'ingredient manquant en cas d'achat impossible
     * (pour cause de manque)
     * @param stock Stock de réference
     * @return l'ingrédient manquant
     */
    private Ingredient getIngredientManquant(StockIngredient stock){
        /*
            Pas bien mais pas le choix en Java
            Rend la méthode plus robuste mais ne devrais
            pas exister -> programmation défensive
        */
        Ingredient manquant = null;

        for(Ingredient i : recette.keySet()){
            if(stock.getQuantite(i) < recette.get(i)){
                manquant = i;
                break;
            }
        }
        return manquant;
    }

}

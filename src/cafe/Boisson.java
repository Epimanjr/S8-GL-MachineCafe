package cafe;

import exception.MontantInsufisantException;
import exception.StockInsufisantException;
import base.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import stock.StockIngredient;
import stock.StockBoisson;

public class Boisson {
    public static String nomTable = "boisson";
    private int prix;
    private String nom;

    /**
     * Quantité de chaque ingredient
     */
    private Recette recette;

    public Boisson(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
        this.recette = new Recette();

        for (Ingredient i : ListeIngredients.getListe()) {
            this.recette.put(i, 0);
        }
    }

    /**
     * Insertion de la boisson dans la base de données.
     */
    public void insert() {
        try {
            PreparedStatement pstmt = Connexion.getConnection().prepareStatement("INSERT INTO "+Boisson.nomTable+" VALUES(?, ?)");
            pstmt.setString(1, this.nom);
            pstmt.setInt(2, this.prix);
            pstmt.executeUpdate();
        } catch(SQLException sql) {
            System.err.println("Erreur lors de l'insertion de la boisson " + this.toString());
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
    public boolean estPossible(StockIngredient stock, Recette recette) {
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

    public boolean estPossible(StockIngredient stock){
        return estPossible(stock, this.recette);
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

        // WARN !! overwrite `recette` symbol binding to remove semantic noise.
        Recette recette = getRecetteEnTenantCompteDuSucre(this.recette, sucre);
        // `recette` is now local

        if(!estPossible(stock, recette)){
            throw new StockInsufisantException(getIngredientManquant(stock, recette));
        }
        // on est OK !


        // Calcul du sucre effectif à enlever

        // Enlèvement des ingrédients du stock.
        for(Ingredient i : recette.keySet()){
            stock.enleverQuantite(i, recette.get(i));
        }
        // Monnaie à rendre
        return argentDonne - prix;
    }


    private Recette getRecetteEnTenantCompteDuSucre(Recette recetteActuelle, int quantiteSucre){
        if(quantiteSucre != -1)
            return recetteActuelle.obtenirUnCloneAvecSucrePersonnalise(quantiteSucre);
        else return recetteActuelle;
    }


    public boolean verifierBoisson() {
        return StockBoisson.testerRecetteBoisson(this.recette);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder( this.nom + "(Prix:" + this.prix + ") Ingrédients{");
        for (Ingredient i : recette.keySet()) {
            res.append(i.toString())
               .append(":")
               .append(recette.get(i))
               .append("-");
        }
        res.append("}");
        return res.toString();
    }

    /**
     * Donne l'ingredient manquant en cas d'achat impossible
     * (pour cause de manque)
     * @param stock Stock de réference
     * @return l'ingrédient manquant
     */
    private Ingredient getIngredientManquant(StockIngredient stock, Recette recette){
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

    private Ingredient getIngredientManquant(StockIngredient stock){
        return getIngredientManquant(stock, this.recette);
    }

}

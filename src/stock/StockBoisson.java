package stock;

import cafe.Boisson;
import cafe.Ingredient;
import cafe.ListeIngredients;
import cafe.Interaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Maxime BLAISE
 */
public class StockBoisson {

    /**
     * Nombre de boissons disponibles pour la machine.
     */
    public static int nombreBoissons = 5;

    /**
     * Liste des boissons disponibles pour la machine.
     */
    private final ArrayList<Boisson> boissons = new ArrayList<>();

    private static final StockBoisson stockBoissons = new StockBoisson();

    /**
     * Construit le stock de boisson.
     */
    private StockBoisson() {

    }

    public void insert() {
        for(Boisson b : boissons) {
            b.insert();
        }
    }

    

    /**
     * Interactivité pour la suppression d'une boisson.
     */
    public void supprimerBoisson() {
        if (this.boissons.isEmpty()) {
            System.err.println("Erreur: aucune boisson à supprimer");
        } else {
            listerBoissons();
            Boisson b = demanderQuelleBoisson();
            if (b != null) {
                this.boissons.remove(b);
                System.out.println("Boisson supprimée avec succès");
            }

        }
    }

    /**
     * Interactivité pour la modification d'une boisson
     */
    public void modifierBoisson() {
        if (this.boissons.isEmpty()) {
            System.err.println("Erreur: aucune boisson à modifier");
        } else {
            listerBoissons();
            Boisson b = demanderQuelleBoisson();
            if (b != null) {
                gestionModification(b);
            }
        }
    }

    /**
     * Gestion d'une modification d'une boisson.
     *
     * @param boisson Boisson
     */
    public void gestionModification(Boisson boisson) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Que voulez-vous modifier ?\n1/ Prix de la boisson.\n2/ Un ingrédient.\n3/ Plus rien!");
        boolean flag = false;
        while (!flag) {
            int choixModif = sc.nextInt();
            switch (choixModif) {
                case 1:
                    flag = true;
                    int prix = demanderPrix();
                    boisson.setPrix(prix);
                    System.out.println("Modification OK");
                    break;
                case 2:
                    flag = true;
                    Ingredient ingredient = StockIngredient.demanderQuelIngredient();
                    int quantite = StockIngredient.demanderQuantiteIngredient();
                    int quantiteAvant = boisson.getIngredient(ingredient);
                    boisson.setIngredient(ingredient, quantite);
                    if(boisson.verifierBoisson()) {
                        System.out.println("Modification OK");
                    } else {
                        System.err.println("Erreur: la boisson ne contient plus aucun ingrédient");
                        boisson.setIngredient(ingredient, quantiteAvant);
                    }
                    break;
                case 3:
                    flag = true;
                    break;
                default:
                    System.err.println("Erreur: Mauvais choix!");
                    break;
            }
        }

    }

    /**
     * Demande à l'utilisateur un choix de boisson
     *
     * @return Numéro boisson
     */
    public Boisson demanderQuelleBoisson() {
        System.out.print("=> ");
        int numeroBoisson = Interaction.demanderEntierEntreIntervalle(0, this.boissons.size());
        if (numeroBoisson == 0) {
            return null;
        } else {
            return this.boissons.get(numeroBoisson - 1);
        }
    }

    /**
     * Liste les boissons dans la console.
     */
    public void listerBoissons() {
        System.out.println("Boissons : ");
        for (int i = 0; i < this.boissons.size(); i++) {
            System.out.println((i + 1) + "/ " + this.boissons.get(i).toString());
        }
        System.out.println("(tapez 0 pour retourner au menu)");
    }

    /**
     * Méthode qui va tester la recette d'une boisson (au moins 1 ingrédient)
     *
     * @param recetteBoisson La recette
     * @return boolean Vrai si la recette est OK
     */
    public static boolean testerRecetteBoisson(HashMap<Ingredient, Integer> recetteBoisson) {
        Set set = recetteBoisson.keySet();
        Iterator it = set.iterator();
        int nombreIngredients = 0;
        while(it.hasNext()) {
            nombreIngredients += recetteBoisson.get(it.next());
        }
        return nombreIngredients != 0;
    }

    /**
     * Interactivité pour l'ajout d'une boisson.
     */
    public void ajouterBoisson() {
        if (this.boissons.size() == 3) {
            System.err.println("Erreur: il y a déjà trois boissons dans la machine");
        } else {
            Scanner sc = new Scanner(System.in);
            HashMap<Ingredient, Integer> recetteBoisson = new HashMap<>();

            String nomBoisson = demanderNom();
            int prixBoisson = demanderPrix();
            for (Ingredient i : ListeIngredients.getListe()) {
                int quantite = quantiteIngredient(i);
                recetteBoisson.put(i, quantite);
            }

            if(testerRecetteBoisson(recetteBoisson)) {
                ajouterBoisson(nomBoisson, prixBoisson, recetteBoisson);
            } else {
                System.err.println("Erreur: nouvelle boisson sans ingrédients");
            }
        }
    }

    /**
     * Ajoute une boisson à la liste en fonction des paramètres spécifiés par
     * l'utilisateur.
     *
     * @param nomBoisson Nom
     * @param prixBoisson Prix
     * @param recetteBoisson Recette
     */
    public void ajouterBoisson(String nomBoisson, int prixBoisson, HashMap<Ingredient, Integer> recetteBoisson) {
        if (this.boissons.size() < StockBoisson.nombreBoissons) {
            if(testerNomBoisson(nomBoisson)) {
                // Création
                Boisson boisson = new Boisson(nomBoisson, prixBoisson);
                // Ajout des ingrédients
                Set set = recetteBoisson.keySet();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    Ingredient i = (Ingredient) it.next();
                    boisson.setIngredient(i, recetteBoisson.get(i));
                }
                this.boissons.add(boisson);
                System.out.println("Boisson " + boisson + " ajoutée avec succès");
            } else {
                System.err.println("Erreur: ce nom de boisson existe déjà.");
            }
        } else {
            System.err.println("Erreur: déjà 3 boissons dans la machine.");
        }
    }

    /**
     * Interactivité pour demander la quantité d'UN ingrédient.
     *
     * @param i Ingrédient concerné
     * @return Quantité
     */
    private int quantiteIngredient(Ingredient i) {
        System.out.print("Quantité de " + i.toString() + " (>=0) : ");
        return Interaction.demanderEntierAvecMin(0);
    }

    /**
     * Interactivité pour demander le prix de la boisson.
     *
     * @return Prix
     */
    private int demanderPrix() {
        System.out.print("Prix de la boisson (>0) : ");
        return Interaction.demanderEntierAvecMin(1);
    }

    /**
     * Interactivité pour demander le nom de la boisson.
     *
     * @return Nom
     */
    private String demanderNom() {
        System.out.print("Nom de la boisson (max 30 caractères) : ");
        while (true) {
            String nom = Interaction.demanderStringAvecLongueurMax(30);
            if (testerNomBoisson(nom)) {
                return nom;
            } else {
                System.err.println("Erreur: ce nom de boisson est déjà utilisé.");
            }
        }
    }

    /**
     * Test l'unicité du nom de la boisson.
     *
     * @param nomBoisson Nom
     * @return Faux si déjà utilisé
     */
    private boolean testerNomBoisson(String nomBoisson) {
        // Expression fonctionnelle
        return this.boissons.stream().noneMatch(
                (Boisson b) -> nomBoisson.equals(b.getNom())
        );
    }

    /**
     * Récupère la liste des boissons
     *
     * @return Boisson
     */
    public ArrayList<Boisson> getBoissons() {
        return boissons;
    }

    /**
     * Récupère le stock de boisson.
     *
     * @return Le stock
     */
    public static StockBoisson getStock() {
        return stockBoissons;
    }
}

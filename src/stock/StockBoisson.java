package stock;

import cafe.Boisson;
import cafe.Ingredient;
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
    public static int nombreBoissons = 3;

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

    /**
     * Interactivité pour la suppression d'une boisson.
     */
    public void supprimerBoisson() {
        if (this.boissons.isEmpty()) {
            System.err.println("Erreur: aucune boisson à supprimer");
        } else {
            Scanner sc = new Scanner(System.in);

            listerBoissons();
            int choix = demanderQuelleBoisson(sc);
            if (choix != (-1)) {
                this.boissons.remove(choix);
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
            Scanner sc = new Scanner(System.in);

            listerBoissons();
            int choix = demanderQuelleBoisson(sc);
            if (choix != (-1)) {
                gestionModification(sc, choix);
            }
        }
    }

    /**
     * Gestion d'une modification d'une boisson.
     *
     * @param sc Scanner
     * @param choix Numéro de la boisson
     */
    public void gestionModification(Scanner sc, int choix) {
        System.out.println("Que voulez-vous modifier ?\n1/ Prix de la boisson.\n2/ Un ingrédient.\n3/ Plus rien!");
        boolean flag = false;
        while (!flag) {
            int choixModif = sc.nextInt();
            switch (choixModif) {
                case 1:
                    flag = true;
                    int prix = demanderPrix(sc);
                    this.boissons.get(choix).setPrix(prix);
                    System.out.println("Modification OK");
                    break;
                case 2:
                    flag = true;
                    Ingredient ingredient = StockIngredient.demanderQuelIngredient(sc);
                    int quantite = StockIngredient.demanderQuantiteIngredient(sc);
                    this.boissons.get(choix).setIngredient(ingredient, quantite);
                    System.out.println("Modification OK");
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
     * @param sc Scanner
     * @return Numéro boisson
     */
    public int demanderQuelleBoisson(Scanner sc) {
        do {
            System.out.print("=> ");
            int choix;
            try {
                choix = sc.nextInt();
                if (choix < 0 || choix > this.boissons.size()) {
                    System.err.println("Erreur: mauvais choix");
                } else {
                    return choix - 1;
                }
            } catch (InputMismatchException e) {
                System.err.println("Erreur: veuillez entrer un entier valide.");
                sc.nextLine();
            }

        } while (true);
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
     * Interactivité pour l'ajout d'une boisson.
     */
    public void ajouterBoisson() {
        if (this.boissons.size() == 3) {
            System.err.println("Erreur: il y a déjà trois boissons dans la machine");
        } else {
            Scanner sc = new Scanner(System.in);
            HashMap<Ingredient, Integer> recetteBoisson = new HashMap<>();

            String nomBoisson = demanderNom(sc);
            int prixBoisson = demanderPrix(sc);
            for (Ingredient i : Ingredient.values()) {
                int quantite = quantiteIngredient(sc, i);
                recetteBoisson.put(i, quantite);
            }

            ajouterBoisson(nomBoisson, prixBoisson, recetteBoisson);
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
        }
    }

    /**
     * Interactivité pour demander la quantité d'UN ingrédient.
     *
     * @param sc Scanne
     * @param i Ingrédient concerné
     * @return Quantité
     */
    private int quantiteIngredient(Scanner sc, Ingredient i) {
        System.out.print("Quantité de " + i.toString() + " (>=0) : ");
        do {
            int quantite;
            try {
                quantite = sc.nextInt();
                if (quantite < 0) {
                    System.err.println("Erreur: la quantité doit être >= 0.");
                } else {
                    return quantite;
                }
            } catch (InputMismatchException e) {
                System.err.println("Erreur: veuillez entrer un entier valide.");
                sc.nextLine();
            }
        } while (true);
    }

    /**
     * Interactivité pour demander le prix de la boisson.
     *
     * @param sc Scanner
     * @return Prix
     */
    private int demanderPrix(Scanner sc) {
        System.out.print("Prix de la boisson (>0) : ");
        do {
            int prix;
            try {
                prix = sc.nextInt();
                if (prix <= 0) {
                    System.err.println("Erreur: le prix doit être >0");
                } else {
                    return prix;
                }
            } catch (InputMismatchException e) {
                System.err.println("Erreur: veuillez entrer un entier valide.");
                sc.nextLine();
            }
        } while (true);
    }

    /**
     * Interactivité pour demander le nom de la boisson.
     *
     * @param sc Scanner
     * @return Nom
     */
    private String demanderNom(Scanner sc) {
        System.out.print("Nom de la boisson (max 30 caractères) : ");
        do {
            String nomBoisson = "";
            try {
                nomBoisson = sc.nextLine();
                if (nomBoisson.length() > 30) {
                    System.err.println("Erreur: la taille est > 30 caractères");
                } else {
                    boolean boissonExiste = false;
                    for (Boisson b : this.boissons) {
                        if (b.getNom().equals(nomBoisson)) {
                            System.err.println("Erreur: le nom de cette boisson existe déjà");
                            boissonExiste = true;
                            break;
                        }
                    }
                    if (!boissonExiste) {
                        return nomBoisson;
                    }
                }
            } catch (InputMismatchException e) {
                System.err.println("Erreur: veuillez entrer une chaîne valide.");
            }

        } while (true);
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

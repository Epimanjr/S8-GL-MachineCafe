package stock;

import cafe.Boisson;
import cafe.Ingredient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
    public StockBoisson() {

    }

    public void ajouterBoisson() {
        if (this.boissons.size() == 3) {
            System.err.println("Erreur: il y a déjà trois boissons dans la machine");
        } else {
            Scanner sc = new Scanner(System.in);
            HashMap<Ingredient, Integer> recetteBoisson = new HashMap<>();
            
            String nomBoisson = demanderNom(sc);
            int prixBoisson = demanderPrix(sc);
            for(Ingredient i : Ingredient.values()) {
                int quantite = quantiteIngredient(sc, i);
                recetteBoisson.put(i, quantite);
            }
            
            ajouterBoisson(nomBoisson, prixBoisson, recetteBoisson);
        }
    }
    
    public void ajouterBoisson(String nomBoisson, int prixBoisson, HashMap<Ingredient, Integer> recetteBoisson) {
        //TODO
    }

    /**
     * Interactivité pour demander la quantité d'UN ingrédient.
     * @param sc Scanne
     * @param i Ingrédient concerné
     * @return Quantité
     */
    private int quantiteIngredient(Scanner sc, Ingredient i) {
        System.out.print("Quantité de " + i.toString() + " (>=0) : ");
        do {
            int quantite = sc.nextInt();
            if (quantite < 0) {
                System.err.println("Erreur: la quantité doit être >= 0.");
            } else {
                return quantite;
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
        System.out.print("Prix de la boisson (>0)");
        do {
            int prix = sc.nextInt();
            if (prix <= 0) {
                System.err.println("Erreur: le prix doit être >0");
            } else {
                return prix;
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
        System.out.print("Nom de la boisson (max 30 caractères");
        do {
            String nomBoisson = sc.nextLine();
            if (nomBoisson.length() > 30) {
                System.err.println("Erreur: la taille est > 30 caractères");
            } else {
                return nomBoisson;
            }
        } while (true);
    }
    
    /**
     * Récupère la liste des boissons
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

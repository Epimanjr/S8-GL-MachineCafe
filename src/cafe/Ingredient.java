package cafe;

import base.*;
import java.sql.PreparedStatement;

/**
 * On souhaite avoir du café, du lait, du chocolat, du sucre et du thé.
 *
 * @author Maxime BLAISE
 * @author Geoffrey GAILLARD
 */
public class Ingredient {
    public static String nomTable = "ingredient";

    /**
     * Nom de l'ingrédient.
     */
    private String name = "";

    // Constructeur
    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Insertion de l'ingrédient dans la base de données.
     */
    public void insert() {
        try {
            PreparedStatement pstmt = Connexion.getConnection().prepareStatement("INSERT INTO "+Ingredient.nomTable+" VALUES(?)");
            pstmt.setString(1, this.name);
            pstmt.executeUpdate();
        } catch(SQLException sql) {
            System.err.println("Erreur lors de l'insertion de la boisson " + this.toString());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Ingredient) {
            Ingredient objI = (Ingredient) obj;
            return objI.toString().equals(this.toString());
        } else {
            return false;
        }
    }

    @Override
    public String toString() { return getName(); }
}

package base;

import java.sql.*;
import cafe.*;
import stock.*;

public class Base {

    /**
     * Méthode qui permet de sélectionner dans notre base de données.
     */
    public static ResultSet select(String sql) throws SQLException {
        return Connexion.getConnection().createStatement().executeQuery(sql);
    }

    /**
     * Méthode qui permet d'insérer dans notre base de données.
     */
    public static void insert(String sql) {
        try {
            Connexion.getConnection().createStatement().executeUpdate(sql);
        } catch(SQLException e) {
            System.err.println("Erreur: SQLException pour la requête " + sql);
        }
    }

    /**
     * Méthode qui permet de MAJ des données dans notre base de données.
     */
    public static void update(String sql) {
        insert(sql);
    }

    public static void createTableIngredient() {
        Base.insert("DROP TABLE IF EXISTS ingredient");
        Base.insert("CREATE TABLE IF NOT EXISTS ingredient(nomIngredient string PRIMARY KEY)");
    }

    public static void createTableBoisson() {
        Base.insert("DROP TABLE IF EXISTS boisson");
        Base.insert("CREATE TABLE IF NOT EXISTS boisson(nomBoisson string PRIMARY KEY, prix integer)");
    }

    /**
     * Affiche tous les ingrédients présents dans la BDD (table Ingredient)
     */
    public static void afficherIngredientDeLaBase() {
        try {
            ResultSet rs = Base.select("select * from ingredient");
            while(rs.next())
            {
              // read the result set
              System.out.println("nom = " + rs.getString("nomIngredient"));
            }
        } catch(SQLException e) {
            System.err.println("Erreur de lecture des ingrédients de la base.");
        }
    }

    /**
     * Affiche toutes les boissons présentes dans la BDD (table Boisson)
     */
    public static void afficherBoissonDeLaBase() {
        try {
            ResultSet rs = Base.select("select * from boisson");
            while(rs.next())
            {
              // read the result set
              System.out.println("nom = " + rs.getString("nomBoisson"));
              System.out.println("prix = " + rs.getString("prix"));
            }
        } catch(SQLException e) {
            System.err.println("Erreur de lecture des boissons de la base.");
        }
    }

    public static void main(String[] args) {
        /*Base.createTableIngredient();
        Base.createTableBoisson();

        ListeIngredients.getListe().insert();*/
        afficherIngredientDeLaBase();
        //afficherBoissonDeLaBase();
    }
}

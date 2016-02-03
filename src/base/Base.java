package base;

import java.sql.*;

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
    public static void insert(String sql) throws SQLException {
        Connexion.getConnection().createStatement().executeUpdate(sql);
    }

    /**
     * Méthode qui permet de MAJ des données dans notre base de données.
     */
    public static void update(String sql) throws SQLException {
        Connexion.getConnection().createStatement().executeUpdate(sql);
    }

    public static void createTableIngredient() {
        String sql = "CREATE TABLE IF NOT EXISTS ingredient(nomIngredient string PRIMARY KEY)";
        try {
            Base.insert(sql);
        } catch(SQLException e) {
            System.err.println("Erreur: SQLException pour la requête " + sql);
        }
    }

    public static void createTableBoisson() {
        String sql = "CREATE TABLE IF NOT EXISTS boisson(nomBoisson string PRIMARY KEY, prix integer)";
        try {
            Base.insert(sql);
        } catch(SQLException e) {
            System.err.println("Erreur: SQLException pour la requête " + sql);
        }
    }

    public static void main(String[] args) {
        /*Base.createTableIngredient();
        Base.createTableBoisson();*/
        

    }
}

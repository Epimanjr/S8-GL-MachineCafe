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
        //TODO
    }

    public static void createTableBoisson() {
        //TODO
    }

    public static void main(String[] args) {
        try {
            ResultSet rs = Base.select("select * from person");
            while(rs.next())
            {
              // read the result set
              System.out.println("name = " + rs.getString("name"));
              System.out.println("id = " + rs.getInt("id"));
            }
        } catch(SQLException e) {
            System.err.println("SQLException pour select * from person");
        }

    }
}

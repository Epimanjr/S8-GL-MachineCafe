package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    private static Connection connection;

    private static Connexion connexion = new Connexion();

    private Connexion() {
        initConnexion();
    }

    public static Connection getConnection() {
        return connection;
    }

    private void initConnexion() {
        try {
            System.out.print("Connexion à la base de données db/machinecafe.db ... ");
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:db/machinecafe.db");
            System.out.println("OK");
        } catch(SQLException sql) {
            System.err.println("Erreur: impossible de se connecter à la base de données.");
        } catch(ClassNotFoundException e) {
            System.err.println("Erreur de driver SQLite.");
        }
    }
}

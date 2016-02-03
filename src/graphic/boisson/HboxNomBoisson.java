package graphic.boisson;

import exception.ValeurIncorrecteException;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HboxNomBoisson extends HBox {

    /**
     * Champ de saisie pour le nom de la boisson.
     */
    private TextField saisieNomBoisson;

    public HboxNomBoisson() {
        saisieNomBoisson = new TextField();
        Label label = new Label("Nom boisson : ");
        label.setPrefWidth(150);
        this.getChildren().addAll(label, saisieNomBoisson);
    }

    /**
     * Méthode qui retourne le nom de la boisson si il est correct.
     */
    public String recupereNomBoisson() throws ValeurIncorrecteException {
        String nom = saisieNomBoisson.getText();
        if(nom.length() > 30) {
            throw new ValeurIncorrecteException();
        }
        return nom;
    }
}

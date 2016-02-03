package graphic.boisson;

import exception.ValeurIncorrecteException;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HboxPrixBoisson extends HBox {

    /**
     * Champ de saisie pour le nom de la boisson.
     */
    private TextField saisiePrixBoisson;

    public HboxPrixBoisson() {
        saisiePrixBoisson = new TextField();
        Label label = new Label("Prix boisson : ");
        label.setPrefWidth(150);
        this.getChildren().addAll(label, saisiePrixBoisson);
    }

    /**
     * Méthode qui retourne le prix de la boisson si il est correct.
     */
    public int recuperePrixBoisson() throws ValeurIncorrecteException {
        String prix = saisiePrixBoisson.getText();
        // TODO
        return 0;
    }
}

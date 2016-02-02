package graphic;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Maxime BLAISE
 */
public class FenetreBoissons extends Stage {

    /**
     * Notre fenêtre
     */
    private static final FenetreBoissons fenetre = new FenetreBoissons();

    public FenetreBoissons() {
        Group group = new Group();
        this.setScene(new Scene(group));
        initComponents(group);
        this.setTitle("Gestion des boissons");
    }

    /**
     * Récupère la fenêtre souhaité.
     *
     * @return Stage
     */
    public static FenetreBoissons getFenetre() {
        return fenetre;
    }

    private void initComponents(Group group) {
        group.getChildren().add(new VboxBoisson());
    }
}

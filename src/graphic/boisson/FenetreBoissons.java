package graphic.boisson;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Maxime BLAISE
 */
public class FenetreBoissons extends Stage {

    public FenetreBoissons(int action) {
        Group group = new Group();
        this.setScene(new Scene(group));
        initComponents(group, action);
        this.setTitle("Gestion des boissons");
        this.show();
    }

    private void initComponents(Group group, int action) {
        group.getChildren().add(new VboxBoisson(action));
    }
}

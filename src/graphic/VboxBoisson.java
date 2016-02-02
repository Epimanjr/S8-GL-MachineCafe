package graphic;

import javafx.scene.layout.VBox;

public class VboxBoisson extends VBox {

    public VboxBoisson() {
        this.setSpacing(10);
        this.getChildren().add(new TableIngredients());
    }
}

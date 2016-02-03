package graphic.ingredient;

import cafe.Ingredient;
import exception.ValeurIncorrecteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

/**
 * @author Maxime BLAISE
 */
public class TableIngredients extends VBox {

    /**
     * Liste des TextField.
     */
    private HashMap<Ingredient, TextField> listeSaisiesIngredients = new HashMap<>();

    public TableIngredients() {
        this.setSpacing(10);
        // Parcours des ingrédients
        for(Ingredient i : Ingredient.values()) {
            HBox hboxIngredientI = new HBox();
            TextField saisieIngredientI = new TextField();
            saisieIngredientI.setMaxWidth(50);
            listeSaisiesIngredients.put(i, saisieIngredientI);
            Label label = new Label(i.toString());
            label.setPrefWidth(150);
            hboxIngredientI.getChildren().addAll(label, saisieIngredientI);
            this.getChildren().add(hboxIngredientI);
        }
    }

    /**
     * Récupère la recette en fonction des différents TextField
     *
     * @return une HashMap avec les ingrédients
     */
    public HashMap<Ingredient, Integer> recupereRecette() throws ValeurIncorrecteException {
        HashMap<Ingredient, Integer> recette = new HashMap<>();
        // Parcours des TextField
        Iterator it = this.listeSaisiesIngredients.keySet().iterator();
        while(it.hasNext()) {
            Ingredient ingredient = (Ingredient) it.next();
            try {
                int quantite = Integer.parseInt(listeSaisiesIngredients.get(ingredient).getText());
                recette.put(ingredient, quantite);
            } catch(Exception ex) {
                throw new ValeurIncorrecteException();
            }
        }
        return recette;
    }
}

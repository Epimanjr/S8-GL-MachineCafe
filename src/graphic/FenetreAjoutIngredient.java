/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Maxime BLAISE
 */
public class FenetreAjoutIngredient extends Stage {

    /**
     * Notre fenêtre
     */
    private static final FenetreAjoutIngredient fenetre = new FenetreAjoutIngredient();

    /**
     * Constructeur privé.
     */
    private FenetreAjoutIngredient() {
        Group group = new Group();
        this.setScene(new Scene(group));
        initComponents(group);
    }

    /**
     * Récupère la fenêtre souhaité.
     *
     * @return Stage
     */
    public static FenetreAjoutIngredient getFenetre() {
        return fenetre;
    }

    private void initComponents(Group group) {
        VBox vboxCentral = new VBox();
        vboxCentral.setPadding(new Insets(20, 50, 50, 20));
        vboxCentral.setSpacing(20);

        final TextField[] saisies = new TextField[MainFrame.listeIngredients.length];
        for (int i = 0; i < saisies.length; i++) {
            HBox hbox = new HBox();
            Label label = new Label(MainFrame.listeIngredients[i].toString());
            label.setPrefWidth(120);
            saisies[i] = new TextField();
            saisies[i].setMaxWidth(50);
            hbox.getChildren().addAll(label, saisies[i]);
            vboxCentral.getChildren().add(hbox);
        }

        Button valider = new Button("Valider l'ajout");
        Label label = new Label("");
        valider.setOnAction((ActionEvent event) -> {
            // Vérification
            boolean flag = true;
            int[] tabValeur = new int[saisies.length];
            for(int i=0;i<saisies.length;i++) {
                if(!valeurCorrecte(saisies[i].getText(), 0, 30000)) {
                    flag = false;
                    label.setText("Valeurs incorrectes");
                    break;
                } else {
                    tabValeur[i] = new Integer(saisies[i].getText());
                }
            }
            if(flag) {
                label.setText("OK");
                for(int i=0;i<saisies.length;i++) {
                    stock.StockIngredient.getStock().ajouterIngredient(MainFrame.listeIngredients[i], tabValeur[i]);
                }
                MainFrame.setStockIngredient();
                this.close();
            }
        });
        vboxCentral.getChildren().addAll(valider, label);
        group.getChildren().add(vboxCentral);

    }

    /**
     * Test si une valeur est correcte
     *
     * @param str Chaine
     * @param min Min
     * @param max Max
     * @return Vrai/Faux
     */
    private boolean valeurCorrecte(String str, int min, int max) {
        try {
            Integer i = new Integer(str);
            return i >= min || i <= max;
        } catch (Exception e) {
            return false;
        }
    }
}

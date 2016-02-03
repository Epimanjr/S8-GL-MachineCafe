/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic.ingredient;

import graphic.MainFrame;
import graphic.boisson.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
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
        this.setTitle("Ajout d'ingrédients");
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
            saisies[i] = new TextField("0");
            saisies[i].setMaxWidth(50);
            hbox.getChildren().addAll(label, saisies[i]);
            vboxCentral.getChildren().add(hbox);
        }

        Button valider = new Button("Valider l'ajout");
        Label label = new Label("");
        valider.setOnAction((ActionEvent event) -> {
            actionBoutonValider(saisies, label);
        });
        vboxCentral.getChildren().addAll(valider, label);
        group.getChildren().add(vboxCentral);

    }

    /**
     * Action du bouton valider.
     *
     * @param saisies Saisies de l'utilisateur
     * @param label Label résultat
     */
    private void actionBoutonValider(TextField[] saisies, Label label) {
        // Vérification
        boolean flag = true;
        int[] tabValeur = new int[saisies.length];
        for (int i = 0; i < saisies.length; i++) {
            if (!valeurCorrecte(saisies[i].getText(), 0, 30000)) {
                flag = false;
                label.setText("Valeurs incorrectes");
                break;
            } else {
                tabValeur[i] = new Integer(saisies[i].getText());
            }
        }
        if (flag) {
            traitementModification(saisies, label, tabValeur);
        }
    }

    /**
     * Si les valeurs saisies sont correctes
     *
     * @param saisies
     * @param label
     */
    private void traitementModification(TextField[] saisies, Label label, int[] tabValeur) {
        label.setText("");
        this.close();
        XYChart.Series<String, Number> s = (XYChart.Series<String, Number>) MainFrame.bc.getData().get(0);
        for (int i = 0; i < saisies.length; i++) {
            if (tabValeur[i] > 0) {
                stock.StockIngredient.getStock().ajouterIngredient(MainFrame.listeIngredients[i], tabValeur[i]);

                BarChart.Data data = s.getData().get(i);
                data.setYValue(stock.StockIngredient.getStock().getQuantite(MainFrame.listeIngredients[i]));
            }
            saisies[i].setText("0");
        }
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

package graphic;

import graphic.boisson.HboxNomBoisson;
import graphic.boisson.HboxPrixBoisson;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VboxBoisson extends VBox {

    /**
     * 1 pour ajouter une boisson
     * 2 pour modifier une boisson
     * 3 pour supprimer une boisson
     */
    private int actionSouhaitee;

    public VboxBoisson(int action) {
        this.actionSouhaitee = action;
        this.setSpacing(10);
        this.setPadding(new Insets(30,30,30,30));
        initComponents();
    }

    private void initComponents() {
        // TODO
        HboxNomBoisson nomBoisson = new HboxNomBoisson();
        HboxPrixBoisson prixBoisson = new HboxPrixBoisson();
        TableIngredients tableIngredients = new TableIngredients();
        this.getChildren().addAll(genererLabelTitre(), nomBoisson, prixBoisson, tableIngredients, genererBoutonDeValidation());
    }

    /**
     * Génère le Label qui se trouve en haut de la fenêtre.
     */
    private Label genererLabelTitre() {
        Label labelTitre = new Label(texteDuLabel());
        // Modification titre, couleur, ...
        labelTitre.setTextFill(Color.RED);
        labelTitre.setFont(new Font("Arial", 30));
        return labelTitre;
    }

    /**
     * Génère le Bouton de validation en fonction du choix.
     */
    private Button genererBoutonDeValidation() {
        Button boutonDeValidation = new Button(texteDuBouton());
        // Action lors du click
        return boutonDeValidation;
    }

    /**
     * Récupérer le texte du Label en fonction du choix.
     */
    private String texteDuLabel() {
        switch(this.actionSouhaitee) {
            case 1:
                return "Ajout d'une nouvelle boisson";
            case 2:
                return "Modification d'une boisson";
            case 3:
                return "Suppression d'une boisson";
        }
        return "Erreur de configuration";
    }

    /**
     * Récupérer le texte du Bouton de validation en fonction du choix
     */
    private String texteDuBouton() {
        switch(this.actionSouhaitee) {
            case 1:
                return "Ajouter la boisson";
            case 2:
                return "Modifier la boisson";
            case 3:
                return "Supprimer la boisson";
        }
        return "Erreur de configuration";
    }
}

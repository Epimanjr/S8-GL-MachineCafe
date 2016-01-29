/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import cafe.Ingredient;
import cafe.Machine;
import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author maxime
 */
public class MainFrame extends Application {

    /**
     * Liste des ingrédients
     */
    public static final Ingredient[] listeIngredients = Ingredient.values();
    private static BarChart<String, Number> bc;
    static XYChart.Series<String, Number> series1 = new XYChart.Series<>();

    @Override
    public void start(Stage primaryStage) {
        init(primaryStage);
        primaryStage.show();
    }

    public static void setStockIngredient() {
        bc.getData().remove(0);
        creerSerieAvecStockIngredients();
    }

    private static void creerSerieAvecStockIngredients() {
        // Nouvelle série
        series1 = new XYChart.Series<>();
        series1.setName("Stock des ingrédients");
        for (Ingredient i : listeIngredients) {
            series1.getData().add(new XYChart.Data<>(i.toString(), stock.StockIngredient.getStock().getQuantite(i)));
        }

        bc.getData().add(series1);
    }

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));

        initVboxIngredients(root);

    }

    private void initVboxIngredients(Group root) {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Button boutonAjouterIngredients = new Button("Ajouter du stock à un ingrédient");
        boutonAjouterIngredients.setOnAction((ActionEvent event) -> {
            FenetreAjoutIngredient.getFenetre().show();
        });
        hbox.setPadding(new Insets(10, 50, 10, 50));

        hbox.getChildren().add(boutonAjouterIngredients);
        vbox.getChildren().addAll(createChart(), hbox);
        root.getChildren().add(vbox);
    }

    protected BarChart<String, Number> createChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 100, 1);
        bc = new BarChart<>(xAxis, yAxis);
        // setup chart
        bc.setTitle("Stock des ingrédients");
        xAxis.setLabel("Ingrédients");
        yAxis.setLabel("Quantité");
        creerSerieAvecStockIngredients();
        return bc;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

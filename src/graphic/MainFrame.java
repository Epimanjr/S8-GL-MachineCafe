/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import cafe.Boisson;
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
    public static BarChart<String, Number> bc;
    public static BarChart<String, Number> bcBoissons;
    static XYChart.Series<String, Number> series1 = new XYChart.Series<>();

    @Override
    public void start(Stage primaryStage) {
        Machine.ajouteBeaucoupDeChose();
        init(primaryStage);
        primaryStage.setTitle("Gestion d'une machine à café");
        primaryStage.show();
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

        HBox hboxPrincipal = new HBox();

        initVboxIngredients(hboxPrincipal);
        initVBoxBoissons(hboxPrincipal);
        root.getChildren().add(hboxPrincipal);
    }

    private void initVBoxBoissons(HBox root) {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Button boutonAjouterBoisson = new Button("Ajouter une boisson");
        boutonAjouterBoisson.setOnAction((ActionEvent event) -> {
            FenetreBoissons.getFenetre().show();
        });
        hbox.setPadding(new Insets(10, 50, 10, 50));

        hbox.getChildren().add(boutonAjouterBoisson);
        vbox.getChildren().addAll(createChartBoissons(), hbox);
        root.getChildren().add(vbox);
    }

    /**
     * Affichage des ingrédients
     *
     * @param root .
     */
    private void initVboxIngredients(HBox root) {
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

    /**
     * Création de l'histogramme des boissons
     *
     * @return
     */
    protected BarChart<String, Number> createChartBoissons() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        bcBoissons = new BarChart<>(xAxis, yAxis);
        // setup chart
        bcBoissons.setTitle("Visualisation des boissons");
        xAxis.setLabel("Boissons");
        yAxis.setLabel("Ingrédients Nécessaire");
        /* for(Boisson b : stock.StockBoisson.getStock().getBoissons()) {
            XYChart.Series<String,Number> seriesB = new XYChart.Series<>();
            seriesB.setName(b.getNom());
            for(Ingredient i : listeIngredients) {
                seriesB.getData().add(new XYChart.Data<>(i.toString(), b.getIngredient(i)));
            }
            bcBoissons.getData().add(seriesB);
        }*/
        for (Ingredient i : listeIngredients) {
            XYChart.Series<String,Number> seriesB = new XYChart.Series<>();
            seriesB.setName(i.toString());
            for(Boisson b : stock.StockBoisson.getStock().getBoissons()) {
                seriesB.getData().add(new XYChart.Data<>(b.getNom(), b.getIngredient(i)));
            }
            bcBoissons.getData().add(seriesB);
        }

        return bcBoissons;
    }

    /**
     * Création de l'histogramme
     *
     * @return BarChart
     */
    protected BarChart<String, Number> createChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(true);
        bc = new BarChart<>(xAxis, yAxis);
        // setup chart
        bc.setTitle("Etat du stock des ingrédients de la machine");
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

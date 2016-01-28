/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import cafe.Ingredient;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maxime
 */
public class StockBoissonTest {
    
    StockBoisson stockBoissons;
    
    public StockBoissonTest() {
        stockBoissons = StockBoisson.getStock();
         HashMap<Ingredient, Integer> recette = new HashMap<>();
        for(Ingredient i : Ingredient.values()) {
            recette.put(i, (int) (Math.random() * 10));
        }
        stockBoissons.ajouterBoisson("Chocolat", 1, recette);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testTailleListe() {
        assertEquals(stockBoissons.getBoissons().size(), 1);
    }
    
    @Test
    public void testNomBoisson() {
        assertEquals(stockBoissons.getBoissons().get(0).getNom(), "Chocolat");
    }
    
    @Test
    public void testPrixBoisson() {
        assertEquals(stockBoissons.getBoissons().get(0).getPrix(), 1);
    }
 
}

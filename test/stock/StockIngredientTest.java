/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maxime
 */
public class StockIngredientTest {
    
    StockIngredient stockIngredient;
    
    public StockIngredientTest() {
        stockIngredient = StockIngredient.getStock();
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
    public void ajouterPositif() {
        //stockIngredient.ajouterIngredient(Ingredient.LAIT, 2);
        //assertEquals(stockIngredient.getQuantite(Ingredient.LAIT), 2);
    }
    
    @Test
    public void ajouterNegatif() {
//        stockIngredient.ajouterIngredient(Ingredient.CHOCOLAT, -2);
//        assertEquals(stockIngredient.getQuantite(Ingredient.CHOCOLAT), 0);
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

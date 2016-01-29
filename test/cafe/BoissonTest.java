/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe;

import junit.framework.TestCase;
import stock.StockIngredient;

/**
 *
 * @author geoffrey
 */
public class BoissonTest extends TestCase {

    private Boisson boissonTest;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Boisson mocca = new Boisson("Moccacino", 3);
        mocca.setIngredient(Ingredient.LAIT, 2);
        mocca.setIngredient(Ingredient.CAFE, 3);
        mocca.setIngredient(Ingredient.CHOCOLAT, 2);
        mocca.setIngredient(Ingredient.SUCRE, 1);
        
        this.boissonTest = mocca;
    }

    
    
    /**
     * Test of estPossible method, of class Boisson.
     */
    public void testEstPossibleCorrecte() {
        System.out.println("estPossible-Correct");
        
        StockIngredient stock = new StockIngredient();
        stock.ajouterIngredient(Ingredient.LAIT, 10);
        stock.ajouterIngredient(Ingredient.CHOCOLAT, 10);
        stock.ajouterIngredient(Ingredient.CAFE, 10);
        stock.ajouterIngredient(Ingredient.SUCRE, 10);
        
        
        assertTrue(this.boissonTest.estPossible(stock));
    }
    
    
    /**
     * Test of estPossible method, of class Boisson.
     */
    public void testEstPossibleIncorrect() {
        System.out.println("estPossible-Correct");
        
        StockIngredient stock = new StockIngredient();
        stock.ajouterIngredient(Ingredient.LAIT, 10);
        stock.ajouterIngredient(Ingredient.CHOCOLAT, 10);
        stock.ajouterIngredient(Ingredient.CAFE, 1);  //  <---------------------
        stock.ajouterIngredient(Ingredient.SUCRE, 10);
        
        
        assertFalse(this.boissonTest.estPossible(stock));
    }


}

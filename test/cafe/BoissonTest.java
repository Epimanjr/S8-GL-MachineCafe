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

    /**
     * Test of estPossible method, of class Boisson.
     */
    public void testEstPossibleCorrecte() {
        System.out.println("estPossible-Correct");
        StockIngredient stock = null;
        Boisson instance = null;
        boolean expResult = false;
        boolean result = instance.estPossible(stock);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    /**
     * Test of estPossible method, of class Boisson.
     */
    public void testEstPossibleIncorrect() {
        System.out.println("estPossible-Incorrect");
        StockIngredient stock = null;
        Boisson instance = null;
        boolean expResult = false;
        boolean result = instance.estPossible(stock);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


}

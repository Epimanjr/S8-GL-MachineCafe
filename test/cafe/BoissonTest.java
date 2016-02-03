/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe;

import exception.MontantInsufisantException;
import exception.StockInsufisantException;
import junit.framework.TestCase;
import org.junit.Test;
import stock.StockIngredient;

/**
 *
 * @author geoffrey
 */
public class BoissonTest extends TestCase {

    private Boisson boissonTest;

    /*
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Boisson mocca = new Boisson("Mocaccino", 3);
        mocca.setIngredient(Ingredient.LAIT, 2);
        mocca.setIngredient(Ingredient.CAFE, 3);
        mocca.setIngredient(Ingredient.CHOCOLAT, 2);
        mocca.setIngredient(Ingredient.SUCRE, 1);
        
        this.boissonTest = mocca;
    }

    private StockIngredient getStockPlein(){
        StockIngredient stock = new StockIngredient();
        stock.ajouterIngredient(Ingredient.LAIT, 10);
        stock.ajouterIngredient(Ingredient.CHOCOLAT, 10);
        stock.ajouterIngredient(Ingredient.CAFE, 10);
        stock.ajouterIngredient(Ingredient.SUCRE, 10);
        return stock;
    }
    
    private StockIngredient getStockVide(){
        StockIngredient stock = new StockIngredient();
        stock.ajouterIngredient(Ingredient.LAIT, 10);
        stock.ajouterIngredient(Ingredient.CHOCOLAT, 10);
        stock.ajouterIngredient(Ingredient.CAFE, 1);  //  <---------------------
        stock.ajouterIngredient(Ingredient.SUCRE, 10);
        
        return stock;
    }
    

    public void testEstPossibleCorrecte() {
        System.out.println("estPossible-Correct");
        
        StockIngredient stock= getStockPlein();
        
        assertTrue(this.boissonTest.estPossible(stock));
    }
    
    

    public void testEstPossibleIncorrect() {
        System.out.println("estPossible-Correct");
        
        StockIngredient stockVide = getStockVide();
       
        assertFalse(this.boissonTest.estPossible(stockVide));
    }
    
    

    public void testAchatBoissonArgent() {
        int montant = 2;
        StockIngredient stockPlein = getStockPlein();
        
        try{
            boissonTest.acheter(montant,stockPlein);
            fail("La boisson a accepté un montant inferieur à son prix");
        } catch (MontantInsufisantException ex) {
            assertEquals(ex.getPrixAttendu(), boissonTest.getPrix());
        } catch (StockInsufisantException ex) {
            fail("La boisson a propagée une exception non attentue dans "
                    + "ce context.");
        }
        
        
    }
    

    @Test(expected=StockInsufisantException.class)
    public void testAchatBoissonStock(){
        
        int montant = 10;
        StockIngredient stockPlein = getStockVide();
        
        try {
            boissonTest.acheter(montant,stockPlein);
            fail("La boisson a été achetée avec un stock insufisant");
        } catch (MontantInsufisantException ex) {
            fail("La boisson a propagée une exception non attentue dans "
                    + "ce context.");
        } catch (StockInsufisantException ex) {
            Ingredient i = new Ingredient("Café");
            assertEquals(ex.getManquant(), i);
        }
        
    }
    
    @Test
    public void testAchatBoissonOK(){
        int montant = 10;
        StockIngredient stockPlein = getStockPlein();
        
        int monnaieAttendue = montant - boissonTest.getPrix();
        
        try {
            int rendu = boissonTest.acheter(montant, stockPlein);
            assertEquals(monnaieAttendue, rendu);
            
        } catch (Exception ex) {
            fail("Echec d'achat d'une boisson avec un contexte correct.");
        }
    }

    */

}

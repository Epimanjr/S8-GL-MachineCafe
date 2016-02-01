/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import cafe.Ingredient;

/**
 *
 * @author geoffrey
 */
public class StockInsufisantException extends Exception{

    private final Ingredient manquant;

    public StockInsufisantException(Ingredient manquant) {
        this.manquant = manquant;
    }

    public Ingredient getManquant() {
        return manquant;
    }

}

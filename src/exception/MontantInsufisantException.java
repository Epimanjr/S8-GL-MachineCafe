/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author geoffrey
 */
public class MontantInsufisantException extends Exception {

    private int prixAttendu;

    public MontantInsufisantException(int prixAttendu) {
        this.prixAttendu= prixAttendu;
    }

    public int getPrixAttendu(){
        return this.prixAttendu;
    }

}

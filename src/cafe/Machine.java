/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author maxime
 */
public class Machine {
    
    private static String menu = ""
            + "\n 1 - Acheter une boisson"
            + "\n 2 - Ajouter une boisson"
            + "\n 3 - Modifier une boisson"
            + "\n 4 - Supprimer une boisson"
            + "\n 5 - Ajouter un ingredient"
            + "\n 6 - Afficher le stock d'ingredients"
            + "\n 7 - Quitter."
            + "\n ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer choix = 0;
        do{
            System.out.println(menu);
            System.out.println("=> ");
            
            try{
                choix = sc.nextInt();
            }catch(InputMismatchException e){
                continue;
            }
            
            
            switch(choix){
                case 5:
                    stock.StockIngredient.getStock().ajouterIngredient();
                    break;
                default:
                                             
            }
            
        } while(choix != 7);
    }
    
}

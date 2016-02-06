package cafe;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by geoffrey on 06/02/16.
 * @author Geoffrey Gaillard
 */
public class Recette extends HashMap<Ingredient, Integer> {


    public Recette clone(){
        Recette r = new Recette();
        for(Ingredient i : this.keySet()){
            r.put(i, this.get(i));
        }
        return r;
    }

    public Recette obtenirUnCloneAvecSucrePersonnalise(int quantiteSucre){
        Recette r = this.clone();
        Ingredient sucre =  ListeIngredients.obtenirSingleton("Sucre")
                                            .orElseGet(null);
        if(sucre != null) {
            r.put(sucre, quantiteSucre);
        }
        return r;
    }

}

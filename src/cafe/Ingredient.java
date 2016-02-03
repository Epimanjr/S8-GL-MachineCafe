package cafe;

/**
 * On souhaite avoir du café, du lait, du chocolat, du sucre et du thé.
 *
 * @author Maxime BLAISE
 * @author Geoffrey GAILLARD
 */
public class Ingredient {

    /**
     * Nom de l'ingrédient.
     */
    private String name = "";

    // Constructeur
    public Ingredient(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Ingredient) {
            Ingredient objI = (Ingredient) obj;
            return objI.toString().equals(this.toString());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

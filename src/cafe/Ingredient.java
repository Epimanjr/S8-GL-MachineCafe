package cafe;

/**
 *
 * @author Maxime BLAISE
 * @author Geoffrey GAILLARD
 */
public enum Ingredient {
    CAFE("Café"),
    LAIT("Lait"),
    CHOCOLAT("Chocolat"),
    SUCRE("Sucre"),
    THE("Thé");

    private String name = "";

    // Constructeur
    Ingredient(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

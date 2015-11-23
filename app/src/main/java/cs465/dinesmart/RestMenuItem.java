package cs465.dinesmart;

//restaurant menu item objects
public class RestMenuItem {
    private String itemName = "";
    private double price = 0;
    private int calories = 0;
    private int protein;

    public RestMenuItem (String itemName, double price, int calories, int protein) {
        super();
        this.itemName = itemName;
        this.price = price;
        this.calories = calories;
        this.protein = protein;
    }

    public String getItemName() {
        return itemName;
    }
    public double getPrice() {
        return price;
    }
    public int getCalories() {
        return calories;
    }
    public int getProtein() { return protein; }
}


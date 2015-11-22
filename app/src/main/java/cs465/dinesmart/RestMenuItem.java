package cs465.dinesmart;

//restaurant menu item objects
public class RestMenuItem {
    private String itemName = "";
    private double price = 0;
    private int calories = 0;

    public RestMenuItem (String itemName, double price, int calories) {
        super();
        this.itemName = itemName;
        this.price = price;
        this.calories = calories;
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
}


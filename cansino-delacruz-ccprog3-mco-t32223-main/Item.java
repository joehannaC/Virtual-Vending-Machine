/**
 * Represents an item in the vending machine
 * This class represents an individual item that can be found in the vending machine. 
 * It stores information such as the item's name, price, quantity, and calore amount.
 * Additionally, it provides methods to manage the item's availability, quantity, and pricing within the vending machine
 */
public class Item {
    private String itemName;
    private int quantity, initialQuantity;
    private float price;
    private int calories;
    private boolean sellable;

    /**
     * Assgins values to this item including its name, initial quantity, price, and amount of calories.
     * @param itemName the initial name of this item
     * @param quantity the initial quantity of this item
     * @param price the initial price of this item
     * @param calories the initial calorie amount of this item
     * @param sellable if the item is sellable
     */
    public Item (String itemName, int quantity, float price, int calories, boolean sellable) {
        this.itemName = itemName;
        this.quantity = quantity;
        initialQuantity = quantity;
        this.price = price;
        this.calories = calories;
        this.sellable = sellable;
    }
    /**
     * Returns the name of this item
     * @return the name of this item
     */
    public String getItemName() {
        return itemName;
    }
    /**
     * Returns the current quantity of this item
     * @return the current quantity of this item
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Returns the price of this item
     * @return the price of this item
     */
    public float getPrice() {
        return price;
    }
    /**
     * Assigns a nrw value for this item
     * @param price the price of this item
     */
    public void setPrice(float price) {
        this.price = price;
    }
    /**
     * Returns the amount of calories of this item
     * @return the amount of calories of this item
     */
    public int getCalories() {
        return calories;
    }
    /**
     * Returns a boolean value if this item is available or not.
     * If the quantity is zero, this item will not be available.
     * @return the availability status of this item (true if available and false if not)
     */
    public boolean isItemAvailable ()
    {
        if (quantity > 0)
            return true;

        return false;
    }
    /**
     * Restocks the quantity for this item.
     * Returns a boolean value whether the quantity input sa valid or not (true if yes and false if not).
     * If the quantity input is less than or equal when added to the maximum item slot capacity, the input is valid and this item will be restocked. Otherwise, invalid.
     * @param quantity the quantity to be added to this item
     * @return a boolean value whether the quantity input sa valid or not
     */
    public boolean restock(int quantity) {

        if (this.quantity + quantity > 0 && this.quantity + quantity <= 10)
        {
            this.quantity += quantity;
            return true;
        }
        return false;
    }
    /**
     * Dispenses this item on this vending machine. 
     * This emthod returns a boolean value if this item is available or not.
     * If this item is available, this item will be dispensed and its quantity in this vending machine will be decremented.
     * Otherwise, display that this item is out of stock.
     * @return the boolean value if the item can be dispensed
     */
    public boolean dispense() 
    {
        if (quantity > 0) 
        {
            quantity--;
            return true;
        }

        return false;
    }
    /**
     * Returns the quantity of this item that has been sold
     * @return the quantity sold of the item
     */
    public int quantitySold ()
    {
        return initialQuantity - quantity;
    }
    /**
     * Returns the boolean value if the item is sellable
     * @return the boolean value if the item is sellable
     */
    public boolean isSellable ()
    {
        return sellable;
    }
}


import java.util.*;
import java.lang.String;
/**
 * Represents a vending machine. 
 * This class encapsulates the functionality of a vending machine. 
 * It manages the various features and operations of the machine, such as adding items, payment processing, recording transactions, and inventory management.
 */
public class VendingMachine {
    private Item[] items, startingInventory, endingInventory;
    private int numberOfSlots, type;
    private float collectedMoney = (float) 0.0, startingMoney = (float) 0.0;
    private Denomination[] denominations = new Denomination[11];
    private ArrayList<Transactions> transactions = new ArrayList<Transactions>();
    /**
     * Initializes this vending machine.
     * This constructor will also assign a value for the denominations, initializes the items, create the starting inventory, and sets a value for its starting money.
     * @param numberOfSlots the maximum number of slots 
     */
    public VendingMachine (int numberOfSlots, int type) 
    {
        this.numberOfSlots = numberOfSlots;
        this.type = type;
        this.items = new Item[this.numberOfSlots];
        startingInventory = new Item[this.numberOfSlots];

        this.denominations[0] =  new Denomination((float) 0.25, 10);
        this.denominations[1] =  new Denomination((float) 0.50, 10);
        this.denominations[2] =  new Denomination((float) 1, 10);
        this.denominations[3] =  new Denomination((float) 5, 10);
        this.denominations[4] =  new Denomination((float) 10, 10);
        this.denominations[5] =  new Denomination((float) 20, 10);
        this.denominations[6] =  new Denomination((float) 50, 10);
        this.denominations[7] =  new Denomination((float) 100, 10);
        this.denominations[8] =  new Denomination((float) 200, 10);
        this.denominations[9] =  new Denomination((float) 500, 10);
        this.denominations[10] =  new Denomination((float) 1000, 10);

        for (int i = 0; i < denominations.length; i++)
        {
            startingMoney += (denominations[i].getAmount()*denominations[i].getQuantity());
        }

        collectedMoney += startingMoney;
    }
    /**
     * Adds a new Iitem in this vending machine.
     * @param slotIndex the index for the slot of the item
     * @param itemName the name of the item
     * @param quantity the quantity of the item
     * @param price the price of the item
     * @param calories the amount of calories in the item
     */
    public void addItem (int slotIndex, String itemName, int quantity, float price, int calories, boolean sellable) 
    {
        items[slotIndex] = new Item(itemName, quantity, price, calories, sellable);
    }
    /**
     * Adds a transaction to this vending machine
     * @param transaction a transaction to this vending machine
     */
    public void addTransaction (Transactions transaction)
    {
        transactions.add(transaction);
    }
    /**
     * Returns the number of slots for the items of this vending machine
     * @return the number of slots for the items of this vending machine
     */
    public int getNumberOfSlots () {

        return numberOfSlots;
    }
    /**
     * Returns all the items in this vedning machine
     * @return all the items in this vedning machine
     */
    public Item[] getItems () {

        return items;
    }
    /**
     * Returns an item in this vending machine based on its slot index.
     * @param slotIndex the slot index of an item in this vending machine
     * @return an item in this vending machine
     */
    public Item getItem (int slotIndex) {

        return items[slotIndex];
    }
    /**
     * Returns all denominations in this vending machine.
     * @return all denominations in this vending machine
     */
    public Denomination[] getDenominations ()
    {
        return denominations;
    }
    /**
     * Returns a denomination in this vending machine based on its index.
     * @param denominationIndex the index of a denomination in this vending machine
     * @return a denomination in this vending machine
     */
    public Denomination getDenomination (int denominationIndex) {

        return denominations[denominationIndex];
    }
    /**
     * Returns the collected money in this vending machine. 
     * @return the collected money in this vending machine
     */
    public float getCollectedMoney ()
    {
        return collectedMoney;
    }
    /**
     * Returns the initial amount of money in this vending machine.
     * @return the initial amount of money in this vending machine
     */
    public float getStartingMoney ()
    {
        return startingMoney;
    }
    /**
     * Returns all the transactions in this vending machine.
     * @return all the transactions in this vending machine
     */
    public ArrayList<Transactions> getTransactions ()
    {
        return transactions;
    }
    /**
     * Restocks an item with a specified quantity in this vending machine.
     * @param itemIndex the index of a certain item in this vending machine 
     * @param quantity the quantity of the item to be restocked
     */
    public void restockItem (int itemIndex, int quantity) 
    {
        items[itemIndex].restock(quantity);
    }
    /**
     * Dispanses an item in this vending machine.
     * Returns a boolean value if the dispensing of the item has been successful or not (true if yes, false if not).
     * @param itemIndex the index of a certain item in this vending machine
     * @return a boolean value if the dispensing of the item has been successful or not
     */
    public boolean dispenseItem (int itemIndex) 
    {
        boolean dispenseFlag = false;

        if(itemIndex >= 0 && itemIndex < items.length) 
        {
            items[itemIndex].dispense();
            return true;
        }

        return dispenseFlag;
    }
    /**
     * Assigns a new price to an item in this vending machine.
     * This method will also check if the index of the item is valid.
     * If the index os valid, a new price will be assigned to the chosen item.
     * @param itemIndex the index of a certain item in this vending machine 
     * @param price the new price of a certain item in this vending machine 
     * @return a boolean value whether the item is valid or not
     */
    public boolean setPrice(int itemIndex, float price) 
    {
        if (itemIndex >= 0 && itemIndex < items.length) 
        {
            items[itemIndex].setPrice(price);
            return true;
        }

        return false;
    }
    /**
     * Collects money that would be added to this vending machine.
     * @param amount the amount to be added to this vending machine
     */
    public void collectMoney (float amount) 
    {
        this.collectedMoney += amount;
    }
    /**
     * Decreases money based on the specified amount. 
     * @param amount the amount to be decreased in this vending machine
     */
    public void decreaseMoney (float amount) 
    {
        this.collectedMoney -= amount;
    }
    /**
     * Returns the money collected from the transactions to this vending machine
     * @return the money collected from the transactions to this vending machine
     */
    public float getActualCollectedMoney ()
    {
        return collectedMoney - startingMoney;
    }
    /**
     * Replenishes a denomination based on its index and specified quantity
     * @param denominationIndex the index of a denomination in this vending machine
     * @param quantity the quantity to be added to the quantity of a denomination
     */
    public void replenishDenomination (int denominationIndex, int quantity) {
        if(denominationIndex >= 0 && denominationIndex < denominations.length) {
            denominations[denominationIndex].replenish(quantity);
        }
    }
    /**
     * Assigns a new value fort the collected money in this vending machine.
     * @param collectedMoney the new value of the collected money
     */
    public void setCollectedMoney(float collectedMoney) 
    {
        this.collectedMoney = collectedMoney;
    }
    /**
     * Returns the starting inventory in thid vending machine
     * @return the starting inventory in thid vending machine
     */
    public Item[] getStartingInventory ()
    {
        return startingInventory;
    }
    /**
     * Returns the current inventory in thid vending machine
     * @return the starting inventory in thid vending machine
     */
    public Item[] getEndingInventory ()
    {
        return endingInventory;
    }
    /**
     * Instantiates an item for its starting inventory in this vending machine
     * @param index the index of the inventory in this vending machine
     * @param itemName the name of the specified item in this vending machine
     * @param quantity the quantity of the specified item in this vending machine
     * @param price the price of a specified item in this vending machine
     * @param calories the amount of calories of a specified item in this vending machine
     */
    public void setStartingInventory (int index, String itemName, int quantity, float price, int calories, boolean sellable)
    {
        startingInventory[index] = new Item(itemName, quantity, price, calories, sellable);
    }
    /**
     * Assigns the current values of all items to the ending (current) inventory.
     * The endingInventory field is a reference-type so any changes made to the items field will also relfect in endingInventory.
     */
    public void setEndingInventory ()
    {
        endingInventory = items;
    }
    /**
     * Returns a string containing the calorie information of an item
     * @param slotNum the slot number of an item
     * @return a string containing the calorie information of an item
     */
    public String getItemCalorieString (int slotNum)
    {
        return "Item "+ (slotNum + 1) + "\nName: " + items[slotNum].getItemName() + "\nCalories: " + items[slotNum].getCalories();
    }
    /**
     * Returns the quantity of a denomination
     * @param denominationAmount The denomination amount
     * @return The quantity of a denomination
     */
    public int getDenominationQuantity(float denominationAmount) {
        for (Denomination denomination : denominations) {
            if (denomination.getAmount() == denominationAmount) {
                return denomination.getQuantity();
            }
        }
        return 0;
    }
    /**
     * Decreases the quantity of a denomination
     * @param denominationAmount The denomination amount
     * @param quantity The quantity that wil be subtracted to the denomination
     */
    public void decreaseDenominationQuantity(float denominationAmount, int quantity) {
        for (Denomination denomination : denominations) {
            if (denomination.getAmount() == denominationAmount) {
                int currentQuantity = denomination.getQuantity();
                if (currentQuantity >= quantity) {
                    denomination.setQuantity(currentQuantity - quantity);
                } else {
                }
            }
        }
    }
    /**
     * Returns the type of this vending machine. Either regular or special
     * @return the type of this vending machine
     */
    public int getType ()
    {
        return type;
    }
}

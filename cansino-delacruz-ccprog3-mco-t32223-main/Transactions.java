/**
 * Represents successful transactions in the vending machine. 
 * This class records information about the transactions that have been successfully completed in the vending machine. 
 * It stores details such as the item name, quantity sold, and total amount for each transaction.
 * It provides methods to add new transactionsa and retrieve transaction details.
 */
public class Transactions {
    
    private String soldItemName;
    private int soldQuantity;
    private float totalAmount;
    /**
     * Assigns values to the class. Includes the item name, its quantity, and the total amount of the item.
     * @param soldItemName the name of the item that was sold
     * @param soldQuantity the quantity of the item that was sold
     * @param totalAmount the total amount of the sold item
     */
    public Transactions (String soldItemName, int soldQuantity, float totalAmount)
    {
        this.soldItemName = soldItemName;
        this.soldQuantity = soldQuantity;
        this.totalAmount = totalAmount;
    }
    /**
     * Returns the name of the sold item
     * @return the name of the item
     */
    public String getSoldItemName() {
        return soldItemName;
    }
    /**
     * Returns the quantity of the sold item
     * @return the quantity of the item
     */
    public int getSoldQuantity() {
        return soldQuantity;
    }
    /**
     * Returns the total amount of the sold item
     * @return the total amount of the item
     */
    public float getTotalAmount() {
        return totalAmount;
    }
}

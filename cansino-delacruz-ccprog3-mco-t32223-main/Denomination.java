/**
 * Represents the denominations used in the vending machine. 
 * This class represents the denominations that the vending machine accepts for payment. 
 * It keeps track of the available quantity of each denomination.
 * It provides methods to manage the denominations, including updating their quantities. 
 */
public class Denomination {
    private float amount;
    private int quantity;
    /**
     * Assigns values to this denomination.
     * @param amount the value of this denomination
     * @param quantity the initial quantity of this denomination
     */
    public Denomination (float amount, int quantity) {
        this.amount = amount;
        this.quantity = quantity;
    }
    /**
     * Returns the value of this denomination
     * @return the value of this denomination
     */
    public float getAmount() {

        return amount;
    }
    /**
     * Returns the current quantity of this denomination
     * @return the current quantity of this denomination
     */
    public int getQuantity() {

        return quantity;
    }
    /**
     * Replenishes the quantity of this denomination
     * @param quantity the quantity to be added to the current quantity of this denomination
     */
    public void replenish (int quantity) {
        this.quantity += quantity;
    }
    /**
     * Decreases the quantity of this denomination by a specified count.
     * If the count is greater than zero and it's less than or equals to the quantity, the current quantity for this denomination will be decreased by the count argument.
     * @param count the specified count to be decreased
     */
    public void decreaseQuantity(float count) {
        if(count > 0 && count <= quantity) {
            quantity -= count;
        }
    }
    /**
     * Replenishes the quantity of this denomination by a specified value added to the current quantity
     * @param replenishQuantity the quqntity to be added to this denomination's current quantity
     */
    public void increaseQuantity(int replenishQuantity) {
        quantity += replenishQuantity;
    }
    /**
     * Sets the quantity of a denomination to a new value
     * @param quantity the new quantity of the denomination
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


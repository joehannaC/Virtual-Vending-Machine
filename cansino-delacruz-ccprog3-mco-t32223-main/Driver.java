/**
 * Contains helper methods to simulate a vending machine factory. 
 * This class instantiates the Controller class which contains the main functions of the program
 * It includes functionalities related to creating and managing vending machines, initializing item inventory, and displaying the diffrent features.
 */
public class Driver {
    /**
     * Simulates a vending machine factory. 
     * Two options are provided for the user.
     * The first option will create a new vending machine.
     * For this option, the user can choose vending machine they want to create.
     * The second option will test the current vending machine for different features.
     * Different vending features and maintenance features can be tested.
     * @param args contains supplied command-line arguments as an array of String objects
     */
    public static void main (String[] args)
    {
        View vmView = new View();
        
        Controller vmController = new Controller(vmView);
    }
}

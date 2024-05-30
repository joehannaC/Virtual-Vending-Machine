import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.text.*;
/**
 * Contains helper methods to simulate a vending machine factory. 
 * This class provides methods that assist in simulating the operations of a vending machine factory. 
 * It includes functionalities related to creating and managing vending machines, initializing item inventory, and displaying the diffrent features.
 */
public class Controller {
	private View vmView;
	private VendingMachine vendingMachine;
	private int maxSlotNum, currSlotNum;
	/**
	 * Contains all the ActionListener methods that would respond to all the buttons found in the menu and other features of this vending machine.
	 * Inis constructor also processes the instantiation of this vending machine.
	 * @param vmView The current instance of the View class
	 */
	public Controller (View vmView)
	{
		this.vmView = vmView;
		currSlotNum = 0;

		this.vmView.setCreateVmBtnListener(new ActionListener() { // Redirects to the vending machine type menu
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.nextCard("vmTypePanel"); 
			}
		});

		this.vmView.setTestVmBtnListener(new ActionListener() { // If the user wants to test this vending machine
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vendingMachine == null) // Displays an error if there's no vending machine yet
				{
					JOptionPane.showMessageDialog(null, "No vending machine has been created yet. Please create one first." , "System Message", JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					vmView.nextCard("testVmPanel"); // Redirects to the menu for the testing type 
				}
			}
		});

		this.vmView.setMainMenuBtnListener(new ActionListener() { // Redirects to the main menu
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.nextCard("welcomePanel");
			}
		});

		this.vmView.setMainMenuBtn2Listener(new ActionListener() { // Redirects to the main menu
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.nextCard("welcomePanel");
			}
		});

		this.vmView.setRegVmBtnListener(new ActionListener() { // If the user chooses to create a regular vending machine
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.nextCard("regVmInitPanel"); // Redirects to the initialization of the new regular vending machine
			}
		});

		this.vmView.setMaxSlotNumBtnListener(new ActionListener() { // If the maximum slots of the new vending machine has been inputted
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
						maxSlotNum = Integer.valueOf(vmView.getSlotNumInput());

					} catch (NumberFormatException E) { // Displays a popup if a non-numerical value was inputted
						E.printStackTrace();
						vmView.createInvalidPopUp();
						Timer timer2 = new Timer(1500, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								vmView.hideInvalidPopUp();
							}
						});
						timer2.setRepeats(true);
						vmView.showInvalidPopUp();
						timer2.restart();
					}

					if (maxSlotNum > 0 && maxSlotNum <= 8) // If the input is valid, a new regular vending machine will be instantiated
						{
							vendingMachine = new VendingMachine(maxSlotNum, 1);
							vmView.regVmAddItems(currSlotNum); // Asks input for the first item
							vmView.nextCard("regVmAddItemsPanel"); // Displays where the item information will be inputted
						}
						else // Displays a popup error if the maximum slots is zero or greater than the maximum slot number
						{
							vmView.createInvalidPopUp();
							Timer timer2 = new Timer(1500, new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									vmView.hideInvalidPopUp();
								}
							});
							timer2.setRepeats(true);
							vmView.showInvalidPopUp();
							timer2.restart();
						}
				vmView.clearMaxSlotNumFld(); // Clears the text field for the slot number
			}
		});

		this.vmView.setNextItemBtnListener(new ActionListener() { // If the item information was inputted
			@Override
			public void actionPerformed(ActionEvent e) {

				if (currSlotNum < maxSlotNum) // Will ask for another item information input if the current slot number not greater than the maximum slot number
				{
					try {
						String name = vmView.getItemNameInput();
						int quantity = Integer.valueOf(vmView.getItemQuantityInput()), calories = Integer.valueOf(vmView.getItemCalorieInput());
						float price = Float.valueOf(vmView.getItemPriceInput());

						vendingMachine.addItem(currSlotNum, name, quantity, price, calories, true); // Adds the item in this vending machine
						vendingMachine.setStartingInventory(currSlotNum, name, quantity, price, calories, true); // Adds the item to the starting inventory

						vmView.resetTextFields(); // Resets the text fields

						currSlotNum += 1; // Increments the slot number

						if (currSlotNum < maxSlotNum) // If the current slot number is not greater than the maximum slot number
						{
							vmView.regVmAddItems(currSlotNum); // Asks input for the next item
							vmView.nextCard("regVmAddItemsPanel"); // Displays where the item information will be inputted
						}
						else // If all the items have been inputted
						{
							vmView.resetTextFields(); // Resets the text fields
							currSlotNum = 0; // Resets the current slot number
							vmView.nextCard("welcomePanel"); // Redirects to the main menu
						}
					} catch (Exception a) { // Displays a popup if an input is invalid
						vmView.createInvalidPopUp();
						Timer timer2 = new Timer(1500, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								vmView.hideInvalidPopUp();
							}
						});
						timer2.setRepeats(true);
						vmView.showInvalidPopUp();
						timer2.restart();
					}
				}
			}
		});

		this.vmView.setSpecialVmBtnListener(new ActionListener() { // If the user chooses to create a new special vending machine
			@Override
			public void actionPerformed(ActionEvent e) {

				vendingMachine =  new VendingMachine(10, 2); // Instantiates a new special vending machine

				vendingMachine.addItem(0, "Cheesecake", 10, 175, 321, true); // Adds the different items in this vending machine
				vendingMachine.addItem(1, "Sponge Cake", 10, 150, 297, true);
				vendingMachine.addItem(2, "Red Velvet Cake", 10, 215, 367, true);
				vendingMachine.addItem(3, "Chocolate Cake", 10, 180, 371, true);

				vendingMachine.addItem(4, "Strawberry", 10, 50, 35, true);
				vendingMachine.addItem(5, "Banana", 10, 40, 25, true);
				vendingMachine.addItem(6, "Ice Cream", 10, 60, 200, true);
				
				vendingMachine.addItem(7, "Sprinkles", 10, 50, 300, false);
				vendingMachine.addItem(8, "Cream", 10, 85, 40, false);
				vendingMachine.addItem(9, "Icing", 10, 50, 35, false);

				for (int i = 0; i < vendingMachine.getItems().length; i++) // Adds the items to the starting inventory
				{
					vendingMachine.setStartingInventory(i, vendingMachine.getItem(i).getItemName(), vendingMachine.getItem(i).getQuantity(), vendingMachine.getItem(i).getPrice(), vendingMachine.getItem(i).getCalories(), vendingMachine.getItem(i).isSellable());
				}
				// Displays a message for the new special vending machine
				JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "A new special vending machine has been created" , "System Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		this.vmView.setVendingFtBtnListener(new ActionListener() { // Redirects to the test vending features menu
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.nextCard("vendingFtPanel");
			}
		});

		this.vmView.setExitTestBtnListener(new ActionListener() { // Redirects to the menu for the testing type 
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.nextCard("testVmPanel");
			}
		});

		this.vmView.setExitTestBtn2Listener(new ActionListener() { // Redirects to the menu for the testing type 
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.nextCard("testVmPanel");
			}
		});

		this.vmView.setExitTestBtn5Listener(new ActionListener() { // Redirects to the test vending features menu
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.nextCard("vendingFtPanel");
			}
		});
		
		this.vmView.setExitTestBtn6Listener(new ActionListener() { // Redirects to the test vending features menu
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.nextCard("welcomePanel");
			}
		});

		this.vmView.setMaintenanceFtBtnListener(new ActionListener() { // Redirects to the test maintenance features menu
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.nextCard("maintenanceFtPanel");
			}
		});

		this.vmView.setStockItemBtnListener(new ActionListener() { // Lets the user test the re/stocking of the items feature of this vending machine
			@Override
			public void actionPerformed(ActionEvent e) {
				restockItemsDisplay();
			}
		});

		this.vmView.setSetPriceBtnListener(new ActionListener() { // Lets the user test the setting the price of the items feature of this vending machine
			@Override
			public void actionPerformed(ActionEvent e) {
				setItemPrice();
			}
		});

		this.vmView.setReplenishMoneyBtnListener(new ActionListener() { // Lets the user test the replenishing money feature of this vending machine
            @Override
            public void actionPerformed(ActionEvent e) {
                ReplenishMoney();
            }
        });

		this.vmView.setCollectMoneyBtnListener(new ActionListener() { // Lets the user test the colelcting money feature of this vending machine
			@Override
			public void actionPerformed(ActionEvent e) {
				collectingMoney();
			}
		});

		this.vmView.setDisplayTransactionsBtnListener(new ActionListener() { // Displays all of the transactions of this vending machine
			@Override
			public void actionPerformed(ActionEvent e) {
				vendingMachine.setEndingInventory(); // Sets the ending inventory of this vending machine
				vmView.transactionsDisplay(vendingMachine); // Displays all the transactions
				vmView.nextCard("transactionsPanel"); // Redirects to the transactions page
			}
		});

		this.vmView.setPayDenominationsBtnListener(new ActionListener() { // Lets the user test the paying denomination feature of this vending machine
			@Override
			public void actionPerformed(ActionEvent e) {
				vendingMachine.collectMoney(payDenominationsDisplay()); // Collects the inputted amount
			}
		});

		this.vmView.setDispenseItemBtnListener(new ActionListener() { // Lets the user test the main vending feature of this vending machine
            @Override
            public void actionPerformed(ActionEvent e) {
				vmView.dispenseItemDisplay(vendingMachine); // Displays the vending page
				vmView.nextCard("dispenseItemPanel"); // Reirects to the vending page
           }
        });

		this.vmView.setEnterItemBtnListener(new ActionListener() { // If the user chose a slot number of an item in the vending machine
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						int vmSlotNum = Integer.valueOf(vmView.getDispenseItemInfoTxt()); 
		
						if (!isItemAvailable(vmSlotNum)) // Displays an error if the item chosen 
						{
							JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "Invalid item option. Please try again" , "System Message", JOptionPane.ERROR_MESSAGE);
							vmView.setDispenseItemInfoTxt(""); // Resets the slot input field
						}
						else if (vendingMachine.getType() == 1) // If the vending machine is a regula vending machine
						{
							vmSlotNum -= 1; // Decrements the slot number to match the index of ite item

							if (vmView.getAmountInserted() < vendingMachine.getItem(vmSlotNum).getPrice()) // Displays an error if the amount insrted in this vending machine is insufficient
								JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "Insufficent amount inserted. Please try again." , "System Message", JOptionPane.ERROR_MESSAGE);
							else
							{
								dispenseVmItem(vmSlotNum); // Dispenses the item otherwise		
							}
						}
						else // If the vending machine is a special vending machine
						{
							vmSlotNum -= 1; // Decrements the slot number to match the index of ite item

							int currSlotNum = vmSlotNum, result;
							
							if (vmView.getAmountInserted() < vendingMachine.getItem(vmSlotNum).getPrice()) // Displays an error if the amount insrted in this vending machine is insufficient
								JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "Insufficent amount inserted. Please try again." , "System Message", JOptionPane.ERROR_MESSAGE);
							else
							{
								vmView.incrementOrderNum(); // Increments the number of items the user has picked
								result = JOptionPane.showOptionDialog(vmView.getDispenseItemPanel(), "Do you want to add another item/s?" , "Dispensing An Item",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); // Displays an option if the user wants to add another item/s
																	
								if (result == JOptionPane.YES_OPTION) // If yes, adds the slot number to a list of picked items
								{
									vmView.getSlotNums().add(currSlotNum);
								}
								else // If no, 
								{
									if (vmView.getOrderNum() < 2) // If there's only one item that was picked
									{
										if (vendingMachine.getItem(currSlotNum).isSellable() == true) // Dispenses the item if it's sellable
											dispenseVmItem(currSlotNum); 
										else // Displays an error and resets the number of picked items otherwise
										{ 
											JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "Item is not sellable. Please try again." , "System Message", JOptionPane.ERROR_MESSAGE);
											vmView.setOrderNum(0);
										}
									}
									else // If there are more than one item in the list
									{
										vmView.getSlotNums().add(currSlotNum); // Adds the current picked item on the list

										boolean hasSellable = false;

										for (int i = 0; i < vmView.getSlotNums().size(); i++)
										{
											int itemIndex = vmView.getSlotNums().get(i);

											if (vendingMachine.getItem(itemIndex).isSellable()) // Assigns true to hasSellable an item is sellable
											{
												hasSellable = true;
												break;
											}
										}

										if (hasSellable) // If the item is sellable
										{
											dispenseVmItem(vmView.getSlotNums()); // Dispenses all the picked items on the list
										}
										else // Resets the number and list of picked items otherwise 
										{
											JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "None of items are sellable. Please try again" , "System Message", JOptionPane.ERROR_MESSAGE);
											vmView.setOrderNum(0);
											vmView.resetItemList();
										}
									}
								}
							}
						}

					} catch (Exception E) {      
					} 
					vmView.setDispenseItemInfoTxt(""); // Refreshes the slot number field, current amount inserted, and updates the label of the items in this vending machine
					vmView.setDispenseAmountInfoTxt("Current Amount Inserted: " + vmView.getAmountInserted());
					vmView.setVmLbl(vendingMachine);
				}
		});

		this.vmView.setInsertMoneyBtnListener(new ActionListener() { // If the user inserts a money to this vending machine
			@Override
			public void actionPerformed(ActionEvent e) {
				float newAmount = vmView.getAmountInserted() + payDenominationsDisplay(); // Adds the previous amount and the amount inserted in this vending machine
				vmView.setNewAmount(newAmount);
                vmView.setDispenseAmountInfoTxt("Current Amount inserted: " + vmView.getAmountInserted()); // Displays the new amount
            }
		});

		this.vmView.setExitTestBtn4Listener(new ActionListener() { // If the user chooses to exit the vending feature
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.setNewAmount(0); // Resets the amount in the vending machine
				vmView.nextCard("vendingFtPanel"); // Redirects to the test vending features menu
			}
		});

		this.vmView.setCalorieBtnListener(new ActionListener() { // DIsplays the calorie information of the items inis vending machine
			@Override
			public void actionPerformed(ActionEvent e) {
				vmView.CalorieDisplay(vendingMachine);
				vmView.nextCard("caloriePanel"); // Redirects to the calor
			}
		});
	}
	/**
	 * DIspenses the item that the user chose in this vending machine
	 * If this vending machine doesn't have enough change for the user, the transaction will be cancelled.
	 * The user will also be asked if they want to continue their purchase. 
     * If the user chose to continue their purchase, their chosen item will be dispensed and its quantity on the vending machine will be decreased.
	 * @param vmSlotNum The slot number of the chosen item
	 */
	public void dispenseVmItem (int vmSlotNum) 
	{
		float remainingChange = vmView.getAmountInserted() - vendingMachine.getItem(vmSlotNum).getPrice();

		if (vendingMachine.getCollectedMoney() - remainingChange < 0) // If this vending machine can't produce a change, the transation will be cancelled
		{
			JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "The vending machine does not have enough amount for change. The current transaction has been canceled" , "System Message", JOptionPane.ERROR_MESSAGE);
			vmView.nextCard("vendingFtPanel"); // Redirects to the test vending features menu
		}
		else // If this vending machine can produce a change
		{
			vmView.setRemainingChange(remainingChange); // Sets the remaining change to its new value
			vmView.setNewAmount(produceChange(vmView.getAmountInserted(), vendingMachine.getItem(vmSlotNum).getPrice())); // Sets a new amount for the amount inserted (the chanage amount)
			int result = JOptionPane.showOptionDialog(vmView.getDispenseItemPanel(), "Do you want to continue with your purchase?" , "Dispensing An Item",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); // Asks if the users wants to continue their purchase

			if (result == JOptionPane.YES_OPTION) // If yes, dispenses the item and adds the item to the transactions of this vending machine
			{
				JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "The item " + vendingMachine.getItem(vmSlotNum).getItemName() + " has been dispensed\nChange received: " + vmView.getRemainingChange(), "System Message", JOptionPane.INFORMATION_MESSAGE);
				vendingMachine.getItem(vmSlotNum).dispense(); 
				Transactions tempTransaction;
				tempTransaction = new Transactions(vendingMachine.getItem(vmSlotNum).getItemName(), 1, vendingMachine.getItem(vmSlotNum).getPrice());
				vendingMachine.addTransaction(tempTransaction);
			} 
			else // If no, the purchase will be canceled
			{
				JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "Purchase canceled\nChange received: " + vmView.getRemainingChange(), "System Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		// Refreshes the number of picked items, slot number field, current amount inserted, and updates the label of the items in this vending machine
		vmView.setOrderNum(0);
		vmView.setDispenseItemInfoTxt("");
		vmView.setDispenseAmountInfoTxt("Current Amount Inserted: " + vmView.getAmountInserted());
		vmView.setVmLbl(vendingMachine);
	}
	/**
	 * DIspenses the items that the user chose in this vending machine
	 * If this vending machine doesn't have enough change for the user, the transaction will be cancelled.
	 * The user will also be asked if they want to continue their purchase. 
     * If the user chose to continue their purchase, their chosen item will be dispensed and its quantity on the vending machine will be decreased.
	 * @param slotNums An ArrayList of Integer type containing the slot number of all the picked items
	 */
	public void dispenseVmItem (ArrayList<Integer> slotNums)
	{
		float totalPrice = 0.0f;
		int itemIndex, totalCalories = 0;
		ArrayList<Integer> base = new ArrayList<Integer>();
		ArrayList<Integer> layer = new ArrayList<Integer>();
		ArrayList<Integer> top = new ArrayList<Integer>();

		for (int i = 0; i < slotNums.size(); i++)
		{
			itemIndex = slotNums.get(i);
			totalPrice += vendingMachine.getItem(itemIndex).getPrice();
			totalCalories += vendingMachine.getItem(itemIndex).getCalories();

			if (itemIndex < 4)
			{
				base.add(itemIndex);
			}
			else if (itemIndex == 6 || itemIndex == 8)
			{
				layer.add(itemIndex);
			}
			else
			{
				top.add(itemIndex);
			}
		}

		float remainingChange = vmView.getAmountInserted() - totalPrice;

		if (vendingMachine.getCollectedMoney() - remainingChange < 0) // If this vending machine can't produce a change, the transation will be cancelled
		{
			JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "The vending machine does not have enough amount for change. The current transaction has been canceled" , "System Message", JOptionPane.ERROR_MESSAGE);
			vmView.nextCard("vendingFtPanel"); // Redirects to the test vending features menu
		}
		else // If this vending machine can produce a change
		{
			vmView.setRemainingChange(remainingChange); // Sets the remaining change to its new value
			vmView.setNewAmount(produceChange(vmView.getAmountInserted(), totalPrice)); // Sets a new amount for the amount inserted (the chanage amount)
			int result = JOptionPane.showOptionDialog(vmView.getDispenseItemPanel(), "Do you want to continue with your purchase?" , "Dispensing An Item",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null); // Asks if the users wants to continue their purchase

			if (result == JOptionPane.YES_OPTION) // If yes, 
			{
				JTextPane items = new JTextPane();
				items.setEditable(false);
                StyledDocument doc = items.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                StyleConstants.setFontFamily(center, "Century Gothic");
                StyleConstants.setBold(center, true);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);

				boolean hasCake = false;

				for (int i = 0; i < slotNums.size(); i++) // Checks it if one of the items is a cake
				{
					int itemIndex2 = slotNums.get(i);
					if (vendingMachine.getItem(itemIndex2).getItemName().toUpperCase().contains("CAKE"))
					{
						hasCake = true;
						break;
					}
				}
				//Displays the processes in creating the item
				try {
					doc.insertString(0, "\n\nCHANGE: " + vmView.getRemainingChange(), center);

					if (hasCake)
						doc.insertString(0, "\n\nADDING THE CAKE INTO THE BOX\n\nCAKE DONE!\n\nTOTAL CALORIES " + totalCalories + " kcal.", center);
					else
						doc.insertString(0, "\n\nADDING THE ITEMS TO A BOWL\n\nPRODUCT DONE!\n\nTOTAL CALORIES " + totalCalories + " kcal.", center);

					for (int k = 0; k < top.size(); k++)
					{
						int itemIdx3  = top.get(k);

						doc.insertString(0, "\n\nADDING THE TOPPING " + vendingMachine.getItem(itemIdx3).getItemName().toUpperCase(), center);
					}

					for (int j = 0; j < layer.size(); j++)
					{
						int itemIdx2  = layer.get(j);

						doc.insertString(0, "\n\nADDING A LAYER OF " + vendingMachine.getItem(itemIdx2).getItemName().toUpperCase(), center);
					}
					
					for (int i = base.size() - 1; i >= 0; i--)
					{
						int itemIdx  = base.get(i);

						if (i == 0)
							doc.insertString(0, "\n\nADDING THE BASE " + vendingMachine.getItem(itemIdx).getItemName().toUpperCase(), center);
						else
							doc.insertString(0, "\n\nADDING A LAYER OF " + vendingMachine.getItem(itemIdx).getItemName().toUpperCase(), center);
					}
					
					if (hasCake)
						doc.insertString(0, "\nPREPARING THE BASE CAKE LAYER", center);
					else
						doc.insertString(0, "\nPREPARING THE ITEMS", center);

					for (int l = 0; l < slotNums.size(); l++) // Dispenses the item and adds the item to the transactions of this vending machine
					{
						int vmSlotNum = slotNums.get(l);

						vendingMachine.getItem(vmSlotNum).dispense(); 
						Transactions tempTransaction;
						tempTransaction = new Transactions(vendingMachine.getItem(vmSlotNum).getItemName(), 1, vendingMachine.getItem(vmSlotNum).getPrice());
						vendingMachine.addTransaction(tempTransaction);
					}

				} catch (Exception e) {
				}
				//Displays the processes in creating the item
				JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), items, "System Message", JOptionPane.INFORMATION_MESSAGE);
				
			} 
			else //If no, the transaction will be canceled
			{
				JOptionPane.showMessageDialog(vmView.getDispenseItemPanel(), "Purchase canceled\nChange received: " + vmView.getRemainingChange(), "System Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		// Refreshes the number and list of picked items, slot number field, current amount inserted, and updates the label of the items in this vending machine
		vmView.setOrderNum(0);
		vmView.resetItemList();
		vmView.setDispenseItemInfoTxt("");
		vmView.setDispenseAmountInfoTxt("Current Amount Inserted: " + vmView.getAmountInserted());
		vmView.setVmLbl(vendingMachine);
	}
	/**
	 * Checks if an item is available
	 * @param vmSlotNum The slot number of an item
	 * @return The boolean value whether an item is available or not
	 */
	public boolean isItemAvailable (int vmSlotNum)
	{
		if (vmSlotNum < 1 || vmSlotNum > vendingMachine.getItems().length || vendingMachine.getItem(vmSlotNum-1).isItemAvailable() == false)
			return false;
		
		return true;
	}
	/**
	 * Produces the change to an inserted amount based on the price of an item.
	 * This method will decrease the value of current money in this vending machine and the denominations that it used.
	 * @param amount The current inserted amount in this vending machine
	 * @param itemPrice The price of the chosen item
	 * @return The remaining amount of the user in this vending machine
	 */
	public float produceChange (float amount, float itemPrice)
    {
        float remainingChange = amount - itemPrice;

        amount = remainingChange;

        for (int i = vendingMachine.getDenominations().length - 1; i >= 0; i--)
        {
            if (vendingMachine.getDenomination(i).getQuantity() > 0 && (remainingChange >= vendingMachine.getDenomination(i).getAmount()) && remainingChange >= 0)
            {
                while (vendingMachine.getDenomination(i).getAmount() <= remainingChange && vendingMachine.getDenomination(i).getQuantity() > 0)
                {  // Decreases the quantity of a denomination while an amount is still left
                    remainingChange -= vendingMachine.getDenomination(i).getAmount();
                    vendingMachine.decreaseMoney(vendingMachine.getDenomination(i).getAmount());
                    vendingMachine.getDenomination(i).decreaseQuantity(1); 
                }
            }
        }
        return amount;
    }
	/**
	 * Restocks an item in this vending machine.
	 */
	public void restockItemsDisplay()
    {
        String itemsList[] = new String[vendingMachine.getItems().length];
        int itemsSize = vendingMachine.getItems().length, result = -1;

        for (int i = 0; i < itemsSize; i++)
        {
            itemsList[i] =  String.valueOf(vendingMachine.getItem(i).getItemName());
        }

        JComboBox itemOptions =  new JComboBox(itemsList);

        JTextField restockItemQuantityFld = new JTextField(3);

        JLabel itemNameLbl = new JLabel ("Choose the item to be re/stocked");

        JLabel itemQuantityLbl = new JLabel("Insert the quantity of the chosen item");

        JFrame restockItemFrame = new JFrame();
        restockItemFrame.setSize(500,500);
       
        do {

            result = JOptionPane.showOptionDialog(restockItemFrame, new Object[] {itemNameLbl, itemOptions, itemQuantityLbl, restockItemQuantityFld}, "Stocking / Restocking Items",
                     JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            
            if (result == JOptionPane.OK_OPTION) // If the user chooses to restock an item, 
            {
                try {
                    int quantity = Integer.valueOf(restockItemQuantityFld.getText()), itemIndex = Integer.valueOf(itemOptions.getSelectedIndex());
                    String itemName = itemOptions.getSelectedItem().toString();
                    int previousQuantity = vendingMachine.getItem(itemIndex).getQuantity();

                    if ((quantity + previousQuantity) <= 10) // If the quanity doesn't esceed the maximum slot, the chosen item will be restocked and its starting inventory will be changed
                    {
                        vendingMachine.restockItem(itemIndex, quantity);
                        vendingMachine.setStartingInventory(itemIndex, vendingMachine.getItem(itemIndex).getItemName(), quantity, vendingMachine.getItem(itemIndex).getPrice(), vendingMachine.getItem(itemIndex).getCalories(), vendingMachine.getItem(itemIndex).isSellable());
                        JOptionPane.showMessageDialog(null, "Re/stocking successful\nItem Name: " + itemName + "\nPrevious Quantity: " + previousQuantity + "\nQuantity Added: " + quantity + "\nQuantity After Re/stock: " + vendingMachine.getItem(itemIndex).getQuantity(),  
                        "System Message", JOptionPane.INFORMATION_MESSAGE);    
                        restockItemQuantityFld.setText("");        
                    }
                    else // Otherwise, display an error
                    {
                        JOptionPane.showMessageDialog(null, "An item must only have a maximum of 10 items per slot. Please try again", "System Message", JOptionPane.ERROR_MESSAGE);
                        restockItemQuantityFld.setText("");
                    }

                } catch (Exception e) { // Catches the input if it's invalid
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please try again.", "System Message", JOptionPane.ERROR_MESSAGE);
                    restockItemQuantityFld.setText("");
                }
            }

        } while (result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION); // Will continue until the user chooses to exit
    }
	/**
	 * Returns the slot number of the user's chosen item on tnis vending machine. 
     * This method will iterate until the user inputs a valid slot number. The returned slot value will always be valid.
	 * @param itemOptions
	 * @return The slot number of the chosen item
	 */
	public int chooseItem(JComboBox<String> itemOptions) {
        int slot = itemOptions.getSelectedIndex();

        if (slot >= 0 && slot < vendingMachine.getNumberOfSlots()) {
            String itemName = vendingMachine.getItem(slot).getItemName();
            float itemPrice = vendingMachine.getItem(slot).getPrice();
            JOptionPane.showMessageDialog(null, "Chosen item: " + itemName + " - Php " + itemPrice);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid slot number. Please choose a valid item."); // Displays an error of slot number is invalid
            slot = -1;
        }
        return slot;
    }
	/**
	 * Sets the price of an item in this vending machine.
	 */
    public void setItemPrice() {
        int choice;
        float price;

        String[] itemInfo = new String[vendingMachine.getItems().length];
        for (int i = 0; i < vendingMachine.getItems().length; i++) {
            Item item = vendingMachine.getItem(i);
            itemInfo[i] = String.format(item.getItemName() + " - " + item.getPrice());
        }

        JComboBox<String> itemOptions = new JComboBox<>(itemInfo);

        do {
            String[] options = {"Change item price", "EXIT"};
            choice = JOptionPane.showOptionDialog(null, new Object[]{itemOptions}, "Set Item Price", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]
            );

            if (choice == JOptionPane.YES_OPTION) { // If the user chooses to set the price of an item
                int slotNum = chooseItem(itemOptions);
                if (slotNum >= 0) {
                    String priceInput = JOptionPane.showInputDialog(null, "Enter the new price:");
                    try {
                        price = Float.parseFloat(priceInput); // Sets the price of the chosen item to the price input
                        vendingMachine.setPrice(slotNum, price);
                        itemInfo[slotNum] = String.format(vendingMachine.getItem(slotNum).getItemName() + " - " + price);
                        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(itemInfo);
                        itemOptions.setModel(model);
                        itemOptions.setSelectedIndex(-1);
                        JOptionPane.showMessageDialog(null, "Item price changed successfully!\nNew price: Php " + price);
                    } catch (NumberFormatException ex) { // Catches if the price input is valid
                        JOptionPane.showMessageDialog(null, "Invalid price input. Please enter a valid number.");
                    }
                }
            } else if (choice == JOptionPane.NO_OPTION) { // Exits the feature otherwise
                JOptionPane.showMessageDialog(null, "Exiting Set Item Price");
            } else if (choice == JOptionPane.CLOSED_OPTION) {
                choice = JOptionPane.NO_OPTION;
            }
        } while (choice != JOptionPane.NO_OPTION); // Will repeat until the user exits

    }
	/**
	 * Replenishes money in this vending machine. 
	 */
	public void ReplenishMoney()
    {
        String[] denominationsList = new String[vendingMachine.getDenominations().length];
        for (int i = 0; i < vendingMachine.getDenominations().length; i++) {
            denominationsList[i] = String.format( vendingMachine.getDenomination(i).getAmount() + " - Quantity: " + vendingMachine.getDenomination(i).getQuantity());
        }

        JComboBox<String> denominationOptions = new JComboBox<>(denominationsList);

        do {
            int result = JOptionPane.showOptionDialog(null, new Object[]{denominationOptions}, "Replenish Denominations", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (result == JOptionPane.OK_OPTION) { // If the use chose to replenish a denomination, 
                String chosenOption = denominationOptions.getSelectedItem().toString();
                String[] part = chosenOption.split(" - Quantity: ");
                float denomination = Float.parseFloat(part[0]);
                int denominationIndex = -1;

                if (denomination != 0) { // If the amount of the denomination is not zero
                    for (int i = 0; i < vendingMachine.getDenominations().length; i++) {
                        if (denomination == vendingMachine.getDenomination(i).getAmount()) {
                            denominationIndex = i; // Assigns the denominationIndex to the index of the denomination
                            break;
                        }
                    }

                    if (denominationIndex != -1) { // If the denomination is valid, 
                        String quantityInput = JOptionPane.showInputDialog(null,
                                String.format("Enter the quantity of " + vendingMachine.getDenominations()[denominationIndex].getAmount()) + ": ");
                        try { // Asks the user for input. If the input is valid, the denomination will be replenished and the total amount added will be collected
                            int replenishQuantity = Integer.parseInt(quantityInput);
                            vendingMachine.replenishDenomination(denominationIndex, replenishQuantity);
                            vendingMachine.collectMoney(vendingMachine.getDenomination(denominationIndex).getAmount() * replenishQuantity);
                            JOptionPane.showMessageDialog(null, String.format("Replenished " + vendingMachine.getDenominations()[denominationIndex].getAmount() + " by " + replenishQuantity));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid quantity input. Please enter a valid number.");
                        }
                    } else { // Displays nn error otherwise
                        JOptionPane.showMessageDialog(null, String.format("Denomination " + denomination + " not found. Please try again."));
                    }
                } else { 
                    StringBuilder outputMessage = new StringBuilder();
                    for (int i = 0; i < vendingMachine.getDenominations().length; i++) {outputMessage.append(String.format(vendingMachine.getDenomination(i).getAmount() + " - Quantity: " + vendingMachine.getDenomination(i).getQuantity() + "\n"));
                    }
                    JOptionPane.showMessageDialog(null, outputMessage.toString());
                    break;
                }
            } else {
                break;
            }
        } while (true); // Will iterate until the user chooses to exit
    }
	/**
	 * Collects money from this vending machine and decreases and/or resets the value of the denomination/s
	 */
	public void collectingMoney() {
        float totalDenominationCollected = 0.0f;
        boolean exit = false;

        while (!exit) {
            String[] options = {"Collect Specific Denomination", "Collect All Denomination", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Collecting Money", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice == JOptionPane.YES_OPTION) {
                String[] denominationOptions = new String[vendingMachine.getDenominations().length];
                for (int i = 0; i < vendingMachine.getDenominations().length; i++) {
                    denominationOptions[i] = String.format("%.2f", vendingMachine.getDenomination(i).getAmount());
                }
                String selectedDenomination = (String) JOptionPane.showInputDialog(null, "Select the denomination to collect:", "Collect Specific Denomination", JOptionPane.QUESTION_MESSAGE, null, denominationOptions, denominationOptions[0]);
				// Asks which denomination to be collected
                if (selectedDenomination != null) { // If the denomination is valid, 
                    float chosenDenomination = Float.parseFloat(selectedDenomination);
                    int denominationQuantity = vendingMachine.getDenominationQuantity(chosenDenomination);

                    String input = JOptionPane.showInputDialog(null, "Enter the quantity to collect (Max: " + denominationQuantity + "):");
                    try { // Asks the user to input the quantity to be collected from the denomination
                        int chosenQuantity = Integer.parseInt(input);
                        if (chosenQuantity < 0 || chosenQuantity > denominationQuantity) { // If the input is invalid, displays an error
                            JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a non-negative number up to " + denominationQuantity + ".");
                        } else { // Otherwise, collects the amount of that denomination and decreses its quantity
                            totalDenominationCollected -= (chosenDenomination * chosenQuantity);
                            vendingMachine.decreaseDenominationQuantity(chosenDenomination, chosenQuantity);
                            vendingMachine.collectMoney(totalDenominationCollected);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a valid number.");
                    }
                }
                JOptionPane.showMessageDialog(null, "Total denominations collected: " + totalDenominationCollected, "System Message", JOptionPane.PLAIN_MESSAGE);
            } else if (choice == JOptionPane.NO_OPTION) { 
				// If the user chooses to collect all the denominations, all the amount in the vending machine will be collected and the quantity of the denominations will be reset to zero
                for (Denomination denomination : vendingMachine.getDenominations()) {
                    float denominationAmount = denomination.getAmount();
                    int denominationQuantity = denomination.getQuantity();
                    totalDenominationCollected -= (denominationAmount * denominationQuantity);
                    denomination.setQuantity(0);
                }
                vendingMachine.collectMoney(totalDenominationCollected); // Collects the total collected denomination
                JOptionPane.showMessageDialog(null, "Total denominations collected: " + totalDenominationCollected, "System Message", JOptionPane.PLAIN_MESSAGE);
                exit = true;
            } else if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) { // will continue until the user chooses to exit
                exit = true;
            }
        }

    }
	/**
	 * Allows the user to pay in denominations in this vending machine.
     * The amount will be collected by this vending machine and will be returned to be used as a credit.
	 * @return The amount inserted/chosen by the user
	 */
	public float payDenominationsDisplay()
    {
        String denominationsList[] = new String[vendingMachine.getDenominations().length];
        int denominationsSize = vendingMachine.getDenominations().length, result = -1;
        float totalAmount = (float) 0.0, previousAmount = vendingMachine.getCollectedMoney(), afterAmount = (float) 0.0;

        for (int i = 0; i < denominationsSize; i++)
        {
            denominationsList[i] =  String.valueOf(vendingMachine.getDenomination(i).getAmount());
        }

        JComboBox denominationOptions =  new JComboBox(denominationsList);

        JTextField denominationQuantityFld = new JTextField(3);

        JLabel denominationAmount = new JLabel ("Choose the denomination amount");

        JLabel denominationQuantityLbl = new JLabel("Insert the quantity of the chosen denomination");

        JFrame denominationFrame = new JFrame();
        denominationFrame.setSize(500,500);
       
        do {

            result = JOptionPane.showOptionDialog(denominationFrame, new Object[] {denominationAmount, denominationOptions, denominationQuantityLbl, denominationQuantityFld}, "Paying Denominations",
                     JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					// Asks the user if they want to pay in denominations
            if (result == JOptionPane.OK_OPTION) // If yes, asks the user to input the denomination and the quantity to be added
            {
                try {
                    int quantity = Integer.valueOf(denominationQuantityFld.getText());
                    float amount = Float.valueOf(denominationOptions.getSelectedItem().toString());
                    int denominationSlot = Integer.valueOf(denominationOptions.getSelectedIndex());
                    totalAmount += (quantity*amount);
                    // If the inputs are valid, adds the quantity of the denomination to its prior quantity
                    JOptionPane.showMessageDialog(null, "Payment successful\nAmount paid: " + quantity*amount, "System Message", JOptionPane.INFORMATION_MESSAGE);  
                    vendingMachine.getDenomination(denominationSlot).increaseQuantity(quantity);
                    denominationQuantityFld.setText("");        

                } catch (Exception e) { // Displays an error otherwise
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please try again.", "System Message", JOptionPane.ERROR_MESSAGE);
                    denominationQuantityFld.setText("");
                }
            }

        } while (result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION); // Will continue until the user chooses to exit

        afterAmount = previousAmount + totalAmount;

        if (totalAmount > 0) // Displays the total amount 
        JOptionPane.showMessageDialog(denominationFrame, "Total amount in the vending machine before payment: " + previousAmount + "\nTotal Amount Paid: " + totalAmount + "\nTotal amount in the vending machine after payment: " + afterAmount
        , "System Message", JOptionPane.PLAIN_MESSAGE);
    
        return totalAmount;
    }
}

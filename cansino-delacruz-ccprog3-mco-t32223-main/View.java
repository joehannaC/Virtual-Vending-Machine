import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.text.*;
/**
 * The main user interface in the program.
 * This class contains the different graphic elements that simulates a vending machine factory.
 */
public class View extends JFrame {

    private JFrame mainFrame;
    private JLabel background, addItemsLbl, itemNameLbl, itemQuantityLbl, itemPriceLbl, itemCalorieLbl;
    private JPanel welcomePanel, vmTypePanel, testVmPanel, vendingFtPanel, contPanel =  new JPanel(new CardLayout()),
            maintenanceFtPanel, maintenanceFtPanel2, regVmInitPanel, dispenseItemPanel, dispenseItemLeftPanel;
    private JButton createVmBtn, testVmBtn, regVmBtn, specialVmBtn, mainMenuBtn, mainMenuBtn2, exitTestBtn, exitTestBtn2, exitTestBtn3, exitTestBtn4, exitTestBtn5, exitTestBtn6, maxSlotNumBtn, nextItemBtn, vendingFtBtn, maintenanceFtBtn,
            payDenominationsBtn, dispenseItemBtn, calorieBtn, stockItemBtn, setPriceBtn, replenishMoneyBtn, collectMoneyBtn, displayTransactionsBtn, enterItemBtn, insertMoneyBtn;
    private JTextField maxSlotNumFld, itemNameFld, itemQuantityFld, itemPriceFld, itemCalorieFld;
    private JTextArea dispenseItemInfo, dispenseAmountInfo; 
    private JTextPane sDispenseItemLblTxt[];
    private CardLayout cards = new CardLayout();
    private Popup invalidPopUp;
    private PopupFactory popupFactory;
    private float amountInserted = (float) 0.0, remainingChange  = (float) 0.0;
    private int orderNum = 0;
    ArrayList<Integer> slotNums = new ArrayList<Integer>();
    /**
     * Initializes the main frame, display panels, and buttons in the user interface.
     */
    public View ()
    {
        this.mainFrame = new JFrame("J.J. Pastries Vending Machine Factory");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(1280,720);
        this.mainFrame.setLayout(new FlowLayout());

        background = new JLabel(new ImageIcon("background.png"));
        this.mainFrame.setContentPane(background);

        popupFactory = new PopupFactory();

        initBtns();
        initRegVm();
        welcomeDisplay();
        createVmDisplay();
        regVmInitialization();
        testVmDisplay();
        vendingFtDisplay();
        maintenanceFtDisplay();

        cards.show(contPanel, "welcomePanel");

        this.mainFrame.setVisible(true);
    }
    /**
     * Initializes the buttons for the slot number, exit testing, enter, and insert money buttons.
     */
    public void initBtns()
    {
        maxSlotNumBtn =  new JButton (new ImageIcon("enter.png"));
        maxSlotNumBtn.setMaximumSize(new Dimension(578, 102));
        maxSlotNumBtn.setContentAreaFilled(false);
        maxSlotNumBtn.setOpaque(false);
        maxSlotNumBtn.setBorderPainted(false);

        exitTestBtn =  new JButton(new ImageIcon("exittest.png"));
        exitTestBtn.setMaximumSize(new Dimension(578, 103));
        exitTestBtn.setContentAreaFilled(false);
        exitTestBtn.setOpaque(false);
        exitTestBtn.setBorderPainted(false);

        exitTestBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTestBtn.setIcon(new ImageIcon("exittesthover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTestBtn.setIcon(new ImageIcon("exittest.png"));
            }
        });

        exitTestBtn2 =  new JButton(new ImageIcon("exittest.png"));
        exitTestBtn2.setMaximumSize(new Dimension(578, 103));
        exitTestBtn2.setContentAreaFilled(false);
        exitTestBtn2.setOpaque(false);
        exitTestBtn2.setBorderPainted(false);

        exitTestBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTestBtn2.setIcon(new ImageIcon("exittesthover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTestBtn2.setIcon(new ImageIcon("exittest.png"));
            }
        });

        exitTestBtn3 =  new JButton(new ImageIcon("exittest.png"));
        exitTestBtn3.setMaximumSize(new Dimension(578, 103));
        exitTestBtn3.setContentAreaFilled(false);
        exitTestBtn3.setOpaque(false);
        exitTestBtn3.setBorderPainted(false);

        exitTestBtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTestBtn3.setIcon(new ImageIcon("exittesthover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTestBtn3.setIcon(new ImageIcon("exittest.png"));
            }
        });

        exitTestBtn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextCard("vendingFtPanel");
            }
        });

        exitTestBtn4 =  new JButton(new ImageIcon("exittest2.png"));
        exitTestBtn4.setMaximumSize(new Dimension(578, 103));
        exitTestBtn4.setContentAreaFilled(false);
        exitTestBtn4.setOpaque(false);
        exitTestBtn4.setBorderPainted(false);

        exitTestBtn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTestBtn4.setIcon(new ImageIcon("exittest2hover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTestBtn4.setIcon(new ImageIcon("exittest2.png"));
            }
        });

        exitTestBtn5 =  new JButton(new ImageIcon("exittest2.png"));
        exitTestBtn5.setMaximumSize(new Dimension(578, 103));
        exitTestBtn5.setContentAreaFilled(false);
        exitTestBtn5.setOpaque(false);
        exitTestBtn5.setBorderPainted(false);

        exitTestBtn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTestBtn5.setIcon(new ImageIcon("exittest2hover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTestBtn5.setIcon(new ImageIcon("exittest2.png"));
            }
        });

        exitTestBtn6 =  new JButton(new ImageIcon("exittest2.png"));
        exitTestBtn6.setMaximumSize(new Dimension(578, 103));
        exitTestBtn6.setContentAreaFilled(false);
        exitTestBtn6.setOpaque(false);
        exitTestBtn6.setBorderPainted(false);

        exitTestBtn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTestBtn6.setIcon(new ImageIcon("exittest2hover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTestBtn6.setIcon(new ImageIcon("exittest2.png"));
            }
        });

        insertMoneyBtn =  new JButton(new ImageIcon("insertmoney.png"));
        insertMoneyBtn.setMaximumSize(new Dimension(578, 103));
        insertMoneyBtn.setContentAreaFilled(false);
        insertMoneyBtn.setOpaque(false);
        insertMoneyBtn.setBorderPainted(false);

        insertMoneyBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                insertMoneyBtn.setIcon(new ImageIcon("insertmoneyhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                insertMoneyBtn.setIcon(new ImageIcon("insertmoney.png"));
            }
        });

        enterItemBtn = new JButton("ENTER");
        enterItemBtn.setBorderPainted(false);
    }
    /**
     * Initializes the fields and buttons for the regular vending items initalization page
     */
    public void initRegVm ()
    {
        itemNameFld =  new JTextField(3);
        itemNameFld.setPreferredSize(new Dimension(0,0));
        itemQuantityFld =  new JTextField(3);
        itemQuantityFld.setPreferredSize(new Dimension(0,0));
        itemPriceFld =  new JTextField(3);
        itemPriceFld.setPreferredSize(new Dimension(0,0));
        itemCalorieFld =  new JTextField(3);
        itemCalorieFld.setPreferredSize(new Dimension(0,0));

        nextItemBtn =  new JButton(new ImageIcon("nextitem.png"));
        nextItemBtn.setMaximumSize(new Dimension(578, 103));
        nextItemBtn.setContentAreaFilled(false);
        nextItemBtn.setOpaque(false);
        nextItemBtn.setBorderPainted(false);

        nextItemBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextItemBtn.setIcon(new ImageIcon("nextitemhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextItemBtn.setIcon(new ImageIcon("nextitem.png"));
            }
        });
    }
    /**
     * Initializes the welcome page for the user interface
     */
    public void welcomeDisplay()
    {
        JLabel menuBanner = new JLabel(new ImageIcon("option.png"));
        menuBanner.setSize(new Dimension(578, 154));

        createVmBtn =  new JButton(new ImageIcon("createnew.png"));
        createVmBtn.setMaximumSize(new Dimension(578, 102));
        createVmBtn.setContentAreaFilled(false);
        createVmBtn.setOpaque(false);
        createVmBtn.setBorderPainted(false);

        createVmBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createVmBtn.setIcon(new ImageIcon("createnewhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createVmBtn.setIcon(new ImageIcon("createnew.png"));
            }
        });

        testVmBtn =  new JButton(new ImageIcon("test.png"));
        testVmBtn.setMaximumSize(new Dimension(578, 102));
        testVmBtn.setContentAreaFilled(false);
        testVmBtn.setOpaque(false);
        testVmBtn.setBorderPainted(false);

        testVmBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                testVmBtn.setIcon(new ImageIcon("testhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                testVmBtn.setIcon(new ImageIcon("test.png"));
            }
        });

        welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridLayout(4, 0, 0, 1));

        welcomePanel.setOpaque(false);

        JLabel welcomeBanner = new JLabel(new ImageIcon("welcomebanner.gif"));
        welcomeBanner.setSize(640, 196);


        welcomePanel.add(welcomeBanner);
        welcomePanel.add(menuBanner);
        welcomePanel.add(createVmBtn);
        welcomePanel.add(testVmBtn);

        contPanel.setLayout(cards);
        contPanel.setOpaque(false);
        contPanel.setSize(1280, 720);
        contPanel.add(welcomePanel, "welcomePanel");

        this.mainFrame.add(contPanel);
    }
    /**
     * Adds fields and buttons on the regular vending items initalization page 
     * @param currSlotNum The current slot number of the item to be initialized
     */
    public void regVmAddItems (int currSlotNum)
    {
        currSlotNum += 1;

        addItemsLbl = new JLabel("Add Item " + currSlotNum);
        
        itemNameLbl = new JLabel("Insert the name of Item " + currSlotNum);
        itemQuantityLbl = new JLabel("Insert the quantity of Item " + currSlotNum);
        itemPriceLbl = new JLabel("Insert the price of Item " + currSlotNum);
        itemCalorieLbl = new JLabel("Insert the calorie amount of Item " + currSlotNum);

        JPanel regVmAddItemsPanel = new JPanel();
        regVmAddItemsPanel.setLayout(new GridLayout(3, 1, 0, 10));
        regVmAddItemsPanel.setOpaque(false);

        regVmAddItemsPanel.add(addItemsLbl);

        JPanel regvmAddItemsPanel2 = new JPanel();
        regvmAddItemsPanel2.setLayout(new GridLayout(4, 2, 5, 10));
        regvmAddItemsPanel2.setOpaque(false);

        regvmAddItemsPanel2.add(itemNameLbl);        
        regvmAddItemsPanel2.add(itemNameFld);        
        regvmAddItemsPanel2.add(itemQuantityLbl);        
        regvmAddItemsPanel2.add(itemQuantityFld);        
        regvmAddItemsPanel2.add(itemPriceLbl);        
        regvmAddItemsPanel2.add(itemPriceFld);        
        regvmAddItemsPanel2.add(itemCalorieLbl);        
        regvmAddItemsPanel2.add(itemCalorieFld);

        regVmAddItemsPanel.add(regvmAddItemsPanel2);
        regVmAddItemsPanel.add(nextItemBtn);

        contPanel.add(regVmAddItemsPanel, "regVmAddItemsPanel");
    }
    /**
     * Resets the text fields for the item initialization page
     */
    public void resetTextFields ()
    {
        itemNameFld.setText("");
        itemQuantityFld.setText("");
        itemPriceFld.setText("");
        itemCalorieFld.setText("");
    }
    /**
     * Initializes and adds fields and buttons for the regular vending machine initalization page
     */
    public void regVmInitialization ()
    {
        JLabel popUpPic = new JLabel(new ImageIcon("regvmpopup.png"));
        popUpPic.setOpaque(false);

        regVmInitPanel =  new JPanel();
        regVmInitPanel.setLayout(new GridLayout(3, 1, 0, 10));
        regVmInitPanel.setOpaque(false);
        
        maxSlotNumFld =  new JTextField(3);
        maxSlotNumFld.setSize(50, 50);
        maxSlotNumFld.setColumns(2);

        maxSlotNumBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                maxSlotNumBtn.setIcon(new ImageIcon("enterhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                maxSlotNumBtn.setIcon(new ImageIcon("enter.png"));
            }
        });

        regVmInitPanel.add(popUpPic);
        regVmInitPanel.add(maxSlotNumFld);
        regVmInitPanel.add(maxSlotNumBtn);

        contPanel.add(regVmInitPanel,"regVmInitPanel");
    }
    /**
     * Initializes and adds fields and buttons for the test maintenance features page
     */
    public void maintenanceFtDisplay ()
    {
        stockItemBtn =  new JButton(new ImageIcon("stockingitem.png"));
        stockItemBtn.setMaximumSize(new Dimension(578, 103));
        stockItemBtn.setContentAreaFilled(false);
        stockItemBtn.setOpaque(false);
        stockItemBtn.setBorderPainted(false);

        stockItemBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stockItemBtn.setIcon(new ImageIcon("stockingitemhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stockItemBtn.setIcon(new ImageIcon("stockingitem.png"));
            }
        });

        setPriceBtn =  new JButton(new ImageIcon("setprice.png"));
        setPriceBtn.setMaximumSize(new Dimension(578, 103));
        setPriceBtn.setContentAreaFilled(false);
        setPriceBtn.setOpaque(false);
        setPriceBtn.setBorderPainted(false);

        setPriceBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setPriceBtn.setIcon(new ImageIcon("setpricehover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setPriceBtn.setIcon(new ImageIcon("setprice.png"));
            }
        });

        replenishMoneyBtn =  new JButton(new ImageIcon("replenishmoney.png"));
        replenishMoneyBtn.setMaximumSize(new Dimension(578, 103));
        replenishMoneyBtn.setContentAreaFilled(false);
        replenishMoneyBtn.setOpaque(false);
        replenishMoneyBtn.setBorderPainted(false);

        replenishMoneyBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                replenishMoneyBtn.setIcon(new ImageIcon("replenishmoneyhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                replenishMoneyBtn.setIcon(new ImageIcon("replenishmoney.png"));
            }
        });

        collectMoneyBtn =  new JButton(new ImageIcon("collectmoney.png"));
        collectMoneyBtn.setMaximumSize(new Dimension(578, 103));
        collectMoneyBtn.setContentAreaFilled(false);
        collectMoneyBtn.setOpaque(false);
        collectMoneyBtn.setBorderPainted(false);

        collectMoneyBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                collectMoneyBtn.setIcon(new ImageIcon("collectmoneyhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                collectMoneyBtn.setIcon(new ImageIcon("collectmoney.png"));
            }
        });

        displayTransactionsBtn =  new JButton(new ImageIcon("displaytransactions.png"));
        displayTransactionsBtn.setMaximumSize(new Dimension(578, 103));
        displayTransactionsBtn.setContentAreaFilled(false);
        displayTransactionsBtn.setOpaque(false);
        displayTransactionsBtn.setBorderPainted(false);

        displayTransactionsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                displayTransactionsBtn.setIcon(new ImageIcon("displaytransactionshover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                displayTransactionsBtn.setIcon(new ImageIcon("displaytransactions.png"));
            }
        });

        JLabel maintenanceTestBanner = new JLabel(new ImageIcon("maintenancefttest.png"));
        maintenanceTestBanner.setSize(new Dimension(578, 135));
        maintenanceFtPanel = new JPanel();
        maintenanceFtPanel.setLayout(new GridLayout(2, 1, 0, 2));
        maintenanceFtPanel.setOpaque(false);

        maintenanceFtPanel.add(maintenanceTestBanner);

        maintenanceFtPanel2 = new JPanel();
        maintenanceFtPanel2.setLayout(new GridLayout(3, 2, 0, 10));
        maintenanceFtPanel2.setOpaque(false);
        maintenanceFtPanel2.add(stockItemBtn);
        maintenanceFtPanel2.add(setPriceBtn);
        maintenanceFtPanel2.add(replenishMoneyBtn);
        maintenanceFtPanel2.add(collectMoneyBtn);
        maintenanceFtPanel2.add(displayTransactionsBtn);
        maintenanceFtPanel2.add(exitTestBtn2);

        maintenanceFtPanel.add(maintenanceFtPanel2);

        contPanel.add(maintenanceFtPanel,"maintenanceFtPanel");
    }
    /**
     * Initializes and adds fields and buttons for the test vending features page
     */
    public void vendingFtDisplay ()
    {
        payDenominationsBtn =  new JButton(new ImageIcon("paydenominations.png"));
        payDenominationsBtn.setMaximumSize(new Dimension(578, 103));
        payDenominationsBtn.setContentAreaFilled(false);
        payDenominationsBtn.setOpaque(false);
        payDenominationsBtn.setBorderPainted(false);

        payDenominationsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                payDenominationsBtn.setIcon(new ImageIcon("paydenominationshover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                payDenominationsBtn.setIcon(new ImageIcon("paydenominations.png"));
            }
        });

        dispenseItemBtn =  new JButton(new ImageIcon("dispenseitem.png"));
        dispenseItemBtn.setMaximumSize(new Dimension(578, 103));
        dispenseItemBtn.setContentAreaFilled(false);
        dispenseItemBtn.setOpaque(false);
        dispenseItemBtn.setBorderPainted(false);

        dispenseItemBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dispenseItemBtn.setIcon(new ImageIcon("dispenseitemhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dispenseItemBtn.setIcon(new ImageIcon("dispenseitem.png"));
            }
        });

        calorieBtn =  new JButton(new ImageIcon("calorie.png"));
        calorieBtn.setMaximumSize(new Dimension(578, 103));
        calorieBtn.setContentAreaFilled(false);
        calorieBtn.setOpaque(false);
        calorieBtn.setBorderPainted(false);

        calorieBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                calorieBtn.setIcon(new ImageIcon("caloriehover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                calorieBtn.setIcon(new ImageIcon("calorie.png"));
            }
        });

        JLabel vmTestBanner = new JLabel(new ImageIcon("vendingfttest.png"));
        vmTestBanner.setSize(new Dimension(578, 135));

        vendingFtPanel = new JPanel();
        vendingFtPanel.setLayout(new GridLayout(5, 1, 0, 5));
        vendingFtPanel.setOpaque(false);

        vendingFtPanel.add(vmTestBanner);
        vendingFtPanel.add(payDenominationsBtn);
        vendingFtPanel.add(dispenseItemBtn);
        vendingFtPanel.add(calorieBtn);
        vendingFtPanel.add(exitTestBtn);

        contPanel.add(vendingFtPanel,"vendingFtPanel");
    }
    /**
     * Displays the calories of all items inside this vending machine
     * @param vendingMachine The current instance of this vending machine
     */
    public void CalorieDisplay(VendingMachine vendingMachine) 
    {
        JTextArea displayCalories = new JTextArea();
        displayCalories.setEditable(false);

        for (int i = 0; i < vendingMachine.getNumberOfSlots(); i++) {
            displayCalories.append(vendingMachine.getItemCalorieString(i));
            displayCalories.append("\n\n");
        }

        JScrollPane scroll = new JScrollPane(displayCalories);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Object[] options = {"OK"};
        int result = JOptionPane.showOptionDialog(null, scroll, "Calorie Information of the Item", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
    /**
     * Returns the inputted slot number in the display area of this vending machine
     * @return the inputted slot number in the display area of this vending machine
     */
    public String getDispenseItemInfoTxt ()
    {
        return dispenseItemInfo.getText();
    }
    /**
     * Sets the text inside the slot number display area of this vending machine
     * @param str The new value of the display area
     */
    public void setDispenseItemInfoTxt (String str)
    {
        dispenseItemInfo.setText(str);
    }
    /**
     * Returns the panel where 
     * @return
     */
    public JPanel getDispenseItemPanel ()
    {
        return dispenseItemPanel;
    }
    /**
     * Returns the amount inserted in this vending machine
     * @return the amount inserted in this vending machine
     */
    public float getAmountInserted ()
    {
        return amountInserted;
    }
    /**
     * Sets the amount inserted in this vending machine to the new value
     * @param newValue The new value of the amount inserted in this vending machine
     */
    public void setNewAmount (float newValue)
    {
        amountInserted = newValue;
    }
    /**
     * Sets the remaining change in this vending machine to the new value
     * @param amount The new value of the remaining change in this vending machine
     */
    public void setRemainingChange (float amount)
    {
        remainingChange = amount;
    }
    /**
     * Returns the remaining change in this vending machine
     * @return the remaining change in this vending machine
     */
    public float getRemainingChange ()
    {
        return remainingChange;
    }
    /**
     * Sets the text inside the amount inserted display area to the new text
     * @param str the new value of the display area
     */
    public void setDispenseAmountInfoTxt (String str)
    {
        dispenseAmountInfo.setText(str);
    }
    /**
     * Returns the amount of times the user has picked an item
     * @return the amount of times the user has picked an item
     */
    public int getOrderNum ()
    {
        return orderNum;
    }
    /**
     * Sets the amount of times a user has picked an item
     * @param newNum the new amount of times a use has picked an item
     */
    public void setOrderNum (int newNum)
    {
        orderNum = newNum;
    }
    /**
     * Increments the amount of times a user has picked an item
     */
    public void incrementOrderNum ()
    {
        orderNum += 1;
    }
    /**
     * Returns the list of all the items the user has picked
     * @return the list of all the items the user has picked
     */
    public ArrayList<Integer> getSlotNums ()
    {
        return slotNums;
    }
    /**
     * Resets the list of all the items the user has picked
     */
    public void resetItemList ()
    {
        slotNums.clear();
    }
    /**
     * Displays the label of an item in this vending machine
     * @param vendingMachine The current instance of this vending machine 
     */
    public void setVmLbl (VendingMachine vendingMachine)
    {
        sDispenseItemLblTxt =  new JTextPane[vendingMachine.getItems().length];

        for (int i = 0; i < vendingMachine.getItems().length; i++)
        {
            sDispenseItemLblTxt[i] = new JTextPane();
            sDispenseItemLblTxt[i].setEditable(false);
            StyledDocument doc = sDispenseItemLblTxt[i].getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            try {
                if (vendingMachine.getType() == 1) // Will display all the information of the item
                {
                    doc.insertString(0, "SLOT " + (i + 1) + "\nITEM NAME: " + vendingMachine.getItem(i).getItemName() + "\nPRICE: " + vendingMachine.getItem(i).getPrice() +
                     "\nCALORIES: " + vendingMachine.getItem(i).getCalories() + " kcal." +
                     "\nQUANTITY: " + vendingMachine.getItem(i).getQuantity(), center);
                }
                else 
                {
                    if (vendingMachine.getItem(i).isSellable()) { 
                        doc.insertString(0, "SLOT " + (i + 1) + "\nPRICE: " + vendingMachine.getItem(i).getPrice() +
                        "\nCALORIES: " + vendingMachine.getItem(i).getCalories() + " kcal." +
                        "\nQUANTITY: " + vendingMachine.getItem(i).getQuantity(), center);
                    } else { // Displays if an item is non-sellable
                        doc.insertString(0, "SLOT " + (i + 1) + "\nCALORIES: " + vendingMachine.getItem(i).getCalories() +" kcal." + 
                        "\nQUANTITY: " + vendingMachine.getItem(i).getQuantity() + "\nNON-SELLABLE", center);
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Initializes the grpahic contents of the actual vending machine
     * @param vendingMachine The current instance of this vending machine
     */
    public void dispenseItemDisplay (VendingMachine vendingMachine)
    {
        ArrayList<JPanel> itemsLbl =  new ArrayList<JPanel>();

        dispenseItemPanel = new JPanel();
        dispenseItemPanel.setLayout(new GridBagLayout());
        dispenseItemPanel.setPreferredSize(new Dimension(1280, 720));
        dispenseItemPanel.setBackground(new Color(237, 151, 166));

        JPanel dispenseItemRightPanel = new JPanel();
        dispenseItemRightPanel.setLayout(new GridLayout(5, 0, 50, 10));
        dispenseItemRightPanel.setPreferredSize(new Dimension(453, 610));
        dispenseItemRightPanel.setBackground(new Color(162, 57, 80));
        dispenseItemRightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Font font = new Font("Courier", Font.BOLD, 15);

        dispenseAmountInfo = new JTextArea();
        dispenseAmountInfo.setPreferredSize(new Dimension(397, 86));
        dispenseAmountInfo.setEditable(false);
        dispenseAmountInfo.setFont(font);
        dispenseAmountInfo.setText("Current Amount Inserted: " + amountInserted);

        dispenseItemInfo = new JTextArea();
        dispenseItemInfo.setPreferredSize(new Dimension(397, 86));
        dispenseItemInfo.setEditable(false);
        dispenseItemInfo.setFont(font);

        JPanel vmNumBtnPanel = new JPanel(new GridLayout(4, 3, 1, 1));
        vmNumBtnPanel.setOpaque(false);
        vmNumBtnPanel.setPreferredSize(new Dimension(204, 272));

        JButton numBtn[] = new JButton[12];

        for (int i = 0; i < 12; i++)
        {
            if (i < 9)
                numBtn[i] = new JButton(String.valueOf(i+1));
            else if (i == 9)
                numBtn[9] = new JButton("0");
            else if (i == 11)
                numBtn[11] = new JButton("←");
            
            if (i < 10 || i > 10)
            {
                vmNumBtnPanel.add(numBtn[i]);
                numBtn[i].setBorderPainted(false);
            } 
            else
                vmNumBtnPanel.add(enterItemBtn);
        }
        
        dispenseItemRightPanel.add(dispenseItemInfo);
        dispenseItemRightPanel.add(vmNumBtnPanel);
        dispenseItemRightPanel.add(dispenseAmountInfo);
        dispenseItemRightPanel.add(insertMoneyBtn);
        dispenseItemRightPanel.add(exitTestBtn4);

        dispenseItemLeftPanel = new JPanel();
        dispenseItemLeftPanel.setLayout(new GridLayout(3, 1, 0, 5));
        dispenseItemLeftPanel.setPreferredSize(new Dimension(640, 610));
        dispenseItemLeftPanel.setBackground(new Color(255, 255, 255));
        dispenseItemLeftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        setVmLbl(vendingMachine);

        if (vendingMachine.getType() == 1) // Adds the user inputted items if the vending machine type is regular
        {
            for (int i = 0; i < vendingMachine.getItems().length; i++)
            {
                if (vendingMachine.getItem(i).isItemAvailable())
                {
                    JLabel tempPic = new JLabel(new ImageIcon("regitem.png"));
                    tempPic.setOpaque(false);
                    tempPic.setPreferredSize(new Dimension(30, 30));

                    JPanel tempPanel = new JPanel();
                    tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
                    tempPanel.setOpaque(false);
                    tempPanel.add(tempPic);
                    tempPanel.add(sDispenseItemLblTxt[i]);

                    itemsLbl.add(tempPanel);
                }
            }

            for (int i = 0; i < itemsLbl.size(); i++)
            {
                dispenseItemLeftPanel.add(itemsLbl.get(i));
            }
        }
        else 
        {
            dispenseItemLeftPanel.setLayout(new GridLayout(6, 1, 0, 0));

            JLabel cheese =  new JLabel(new ImageIcon("cheese.png"));
            cheese.setOpaque(false);
            cheese.setMaximumSize(new Dimension(415, 402));
            
            JLabel sponge =  new JLabel(new ImageIcon("sponge.png"));
            sponge.setOpaque(false);
            sponge.setMaximumSize(new Dimension(415, 402));

            JLabel red =  new JLabel(new ImageIcon("red.png"));
            red.setOpaque(false);
            red.setMaximumSize(new Dimension(415, 402));

            JLabel chocolate =  new JLabel(new ImageIcon("chocolate.png"));
            chocolate.setOpaque(false);
            chocolate.setMaximumSize(new Dimension(415, 402));
            
            JPanel row1 = new JPanel();
            row1.setOpaque(false);
            row1.setLayout(new GridLayout(1, 4, 50, 0));
            row1.add(cheese);
            row1.add(sponge);
            row1.add(red);
            row1.add(chocolate);

            JLabel strawberry =  new JLabel(new ImageIcon("strawberry.png"));
            strawberry.setOpaque(false);
            strawberry.setMaximumSize(new Dimension(415, 402));
            
            JLabel banana =  new JLabel(new ImageIcon("banana.png"));
            banana.setOpaque(false);
            banana.setMaximumSize(new Dimension(415, 402));

            JLabel ice =  new JLabel(new ImageIcon("icecream.png"));
            ice.setOpaque(false);
            ice.setMaximumSize(new Dimension(415, 402));

            JPanel row2 = new JPanel();
            row2.setOpaque(false);
            row2.setLayout(new GridLayout(1, 3, 50, 0));

            row2.add(strawberry);
            row2.add(banana);
            row2.add(ice);
            
            JLabel sprinkles =  new JLabel(new ImageIcon("sprinkles.png"));
            sprinkles.setOpaque(false);
            sprinkles.setMaximumSize(new Dimension(415, 402));

            JLabel cream =  new JLabel(new ImageIcon("cream.png"));
            cream.setOpaque(false);
            cream.setMaximumSize(new Dimension(415, 402));

            JLabel icing =  new JLabel(new ImageIcon("icing.png"));
            icing.setOpaque(false);
            icing.setMaximumSize(new Dimension(415, 402));

            JPanel row3 = new JPanel();
            row3.setOpaque(false);
            row3.setLayout(new GridLayout(1, 3, 50, 0));

            row3.add(sprinkles);
            row3.add(cream);
            row3.add(icing);

            JPanel row1lbl = new JPanel();
            row1lbl.setOpaque(false);
            row1lbl.setLayout(new GridLayout(1, 4, 50, 0));
            row1lbl.add(sDispenseItemLblTxt[0]);
            row1lbl.add(sDispenseItemLblTxt[1]);
            row1lbl.add(sDispenseItemLblTxt[2]);
            row1lbl.add(sDispenseItemLblTxt[3]);

            JPanel row2lbl = new JPanel();
            row2lbl.setOpaque(false);
            row2lbl.setLayout(new GridLayout(1, 2, 50, 0));
            row2lbl.add(sDispenseItemLblTxt[4]);
            row2lbl.add(sDispenseItemLblTxt[5]);
            row2lbl.add(sDispenseItemLblTxt[6]);

            JPanel row3lbl = new JPanel();
            row3lbl.setOpaque(false);
            row3lbl.setLayout(new GridLayout(1, 4, 50, 0));
            row3lbl.add(sDispenseItemLblTxt[7]);
            row3lbl.add(sDispenseItemLblTxt[8]);
            row3lbl.add(sDispenseItemLblTxt[9]);


            dispenseItemLeftPanel.add(row1);
            dispenseItemLeftPanel.add(row1lbl);
            dispenseItemLeftPanel.add(row2);
            dispenseItemLeftPanel.add(row2lbl);
            dispenseItemLeftPanel.add(row3);
            dispenseItemLeftPanel.add(row3lbl);
               
        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;

        dispenseItemPanel.add(dispenseItemLeftPanel, c);
        
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 50, 0, 0);
        dispenseItemPanel.add(dispenseItemRightPanel, c);

        //Contains ActionListener methods that appends/removes a value on the inserted amount display area

        numBtn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispenseItemInfo.append("1");
            }
        });

        numBtn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispenseItemInfo.append("2");
            }
        });

        numBtn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispenseItemInfo.append("3");
            }
        });

        numBtn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispenseItemInfo.append("4");
            }
        });

        numBtn[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispenseItemInfo.append("5");
            }
        });

        numBtn[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispenseItemInfo.append("6");
            }
        });

        numBtn[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispenseItemInfo.append("7");
            }
        });

        numBtn[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispenseItemInfo.append("8");
            }
        });

        numBtn[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispenseItemInfo.append("9");
            }
        });

        numBtn[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispenseItemInfo.append("0");
            }
        });

        numBtn[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dispenseItemInfo.getText().length() > 0)
                    dispenseItemInfo.setText(dispenseItemInfo.getText().substring(0, dispenseItemInfo.getText().length()-1));
            }
        });

        contPanel.add(dispenseItemPanel, "dispenseItemPanel");
    }
    /**
     * Initializes and adds fields and buttons for the testing features page
     */
    public void testVmDisplay ()
    {
        vendingFtBtn =  new JButton(new ImageIcon("vendingfeatures.png"));
        vendingFtBtn.setMaximumSize(new Dimension(578, 103));
        vendingFtBtn.setContentAreaFilled(false);
        vendingFtBtn.setOpaque(false);
        vendingFtBtn.setBorderPainted(false);

        vendingFtBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vendingFtBtn.setIcon(new ImageIcon("vendingfeatureshover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vendingFtBtn.setIcon(new ImageIcon("vendingfeatures.png"));
            }
        });

        maintenanceFtBtn =  new JButton(new ImageIcon("maintenancefeatures.png"));
        maintenanceFtBtn.setMaximumSize(new Dimension(578, 103));
        maintenanceFtBtn.setContentAreaFilled(false);
        maintenanceFtBtn.setOpaque(false);
        maintenanceFtBtn.setBorderPainted(false);

        maintenanceFtBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                maintenanceFtBtn.setIcon(new ImageIcon("maintenancefeatureshover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                maintenanceFtBtn.setIcon(new ImageIcon("maintenancefeatures.png"));
            }
        });

        mainMenuBtn2 =  new JButton(new ImageIcon("backmain.png"));
        mainMenuBtn2.setMaximumSize(new Dimension(578, 103));
        mainMenuBtn2.setContentAreaFilled(false);
        mainMenuBtn2.setOpaque(false);
        mainMenuBtn2.setBorderPainted(false);

        mainMenuBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuBtn2.setIcon(new ImageIcon("backmainhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainMenuBtn2.setIcon(new ImageIcon("backmain.png"));
            }
        });

        JLabel testOptionBanner = new JLabel(new ImageIcon("vmtestoption.png"));
        testOptionBanner.setSize(new Dimension(578, 135));

        testVmPanel = new JPanel();
        testVmPanel.setLayout(new GridLayout(4, 1, 0, 5));
        testVmPanel.setOpaque(false);

        testVmPanel.add(testOptionBanner);
        testVmPanel.add(vendingFtBtn);
        testVmPanel.add(maintenanceFtBtn);
        testVmPanel.add(mainMenuBtn2);

        contPanel.add(testVmPanel,"testVmPanel");
    }
    /**
     * Displays all the transactions of this vending machine
     * @param vendingMachine The current instance of this vending machine
     */
    public void transactionsDisplay(VendingMachine vendingMachine)
    {
        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new GridBagLayout());
        transactionsPanel.setOpaque(false);

        JTextArea transactionsTxt = new JTextArea();
        transactionsTxt.setEditable(false);

        JTextArea inventoryTxt = new JTextArea();
        inventoryTxt.setEditable(false);

        inventoryTxt.append("\nINVENTORY INFORMATION");

        for (int i = 0; i < vendingMachine.getItems().length; i++)
        {
            try {
                String startItemName = vendingMachine.getStartingInventory()[i].getItemName();
                String endItemName = vendingMachine.getEndingInventory()[i].getItemName();
                int startQuantity = vendingMachine.getStartingInventory()[i].getQuantity();
                int endQuantity = vendingMachine.getEndingInventory()[i].getQuantity();

                if (vendingMachine.getStartingInventory() != null && vendingMachine.getEndingInventory() != null)
                    inventoryTxt.append("\n\nSTARTING QUANTITY OF " + startItemName + ": " + startQuantity + "\t\t\tENDING QUANTITY OF " + endItemName + ": " + endQuantity);
            } catch (Exception e) {
            }
        }

        float totalTransactionsAmount = 0;

        transactionsTxt.append("\nSUMMARY OF TRANSACTIONS");

        for (int i = 0; i < vendingMachine.getTransactions().size(); i++)
        {
            try {

                transactionsTxt.append("\n\nTRANSACTION " + (i+1));
                transactionsTxt.append("\nITEM NAME: " + vendingMachine.getTransactions().get(i).getSoldItemName());
                transactionsTxt.append("\nQUANTITY SOLD: "+ vendingMachine.getTransactions().get(i).getSoldQuantity());
                transactionsTxt.append("\nTOTAL AMOUNT: " + vendingMachine.getTransactions().get(i).getTotalAmount());
                transactionsTxt.append("\n");
                totalTransactionsAmount += vendingMachine.getTransactions().get(i).getTotalAmount();
            } catch (Exception e) {
            }
        }

        transactionsTxt.append("\nTOTAL AMOUNT FOR ALL TRANSACTIONS: " + totalTransactionsAmount);

        transactionsPanel.add(inventoryTxt);
        transactionsPanel.add(transactionsTxt);
        transactionsPanel.add(exitTestBtn5);

        contPanel.add(transactionsPanel, "transactionsPanel");

        JScrollPane inventoryScroll = new JScrollPane(inventoryTxt);
        inventoryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        inventoryScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane transactionsScroll = new JScrollPane(transactionsTxt);
        transactionsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        transactionsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 10, 0);
        c.weightx = 1.0;
        c.weighty = 1.0;
        transactionsPanel.add(inventoryScroll, c);

        c.gridy = 1;
        c.weighty = 1.0;

        transactionsPanel.add(transactionsScroll, c);

        c.gridy = 2;
        c.weighty = 0.0;

        transactionsPanel.add(exitTestBtn5, c);

        contPanel.add(transactionsPanel, "transactionsPanel");
    }
    /**
     * Initializes and adds fields and buttons for create a vending machine page
     */
    public void createVmDisplay ()
    {
        regVmBtn =  new JButton(new ImageIcon("regvm.png"));
        regVmBtn.setMaximumSize(new Dimension(578, 103));
        regVmBtn.setContentAreaFilled(false);
        regVmBtn.setOpaque(false);
        regVmBtn.setBorderPainted(false);

        regVmBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regVmBtn.setIcon(new ImageIcon("regvmhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                regVmBtn.setIcon(new ImageIcon("regvm.png"));
            }
        });

        specialVmBtn =  new JButton(new ImageIcon("specialvm.png"));
        specialVmBtn.setMaximumSize(new Dimension(578, 103));
        specialVmBtn.setContentAreaFilled(false);
        specialVmBtn.setOpaque(false);
        specialVmBtn.setBorderPainted(false);

        specialVmBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                specialVmBtn.setIcon(new ImageIcon("specialvmhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                specialVmBtn.setIcon(new ImageIcon("specialvm.png"));
            }
        });

        mainMenuBtn =  new JButton(new ImageIcon("backmain.png"));
        mainMenuBtn.setMaximumSize(new Dimension(578, 103));
        mainMenuBtn.setContentAreaFilled(false);
        mainMenuBtn.setOpaque(false);
        mainMenuBtn.setBorderPainted(false);

        mainMenuBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuBtn.setIcon(new ImageIcon("backmainhover.png"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainMenuBtn.setIcon(new ImageIcon("backmain.png"));
            }
        });

        JLabel vmOptionBanner = new JLabel(new ImageIcon("vmoption.png"));
        vmOptionBanner.setSize(new Dimension(578, 135));

        vmTypePanel = new JPanel();
        vmTypePanel.setLayout(new GridLayout(4, 1, 0, 5));
        vmTypePanel.setOpaque(false);

        vmTypePanel.add(vmOptionBanner);
        vmTypePanel.add(regVmBtn);
        vmTypePanel.add(specialVmBtn);
        vmTypePanel.add(mainMenuBtn);

        contPanel.add(vmTypePanel,"vmTypePanel");
    }
    /**
     * Initializes a popup for an invalid input
     */
    public void createInvalidPopUp ()
    {
        JLabel popUpPic = new JLabel(new ImageIcon("invalidinput.png"));
        popUpPic.setOpaque(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width/2;
        int y = screenSize.height/2;

        invalidPopUp = popupFactory.getPopup(mainFrame, popUpPic, x, y-300);
    }
    /**
     * Shows the popup popup
     */
    public void showInvalidPopUp ()
    {
        invalidPopUp.show();
    }
    /**
     * Hides the invalid popup
     */
    public void hideInvalidPopUp ()
    {
        invalidPopUp.hide();
    }
    /**
     * Returns the inputted maximum slot number for the regular vending machine instantiation
     * @return the inputted maximum slot number for the regular vending machine instantiation
     */
    public String getSlotNumInput ()
    {
        return maxSlotNumFld.getText();
    }
    /**
     * Returns the inputted item name for the regular vending machine item instantiation
     * @return the inputted item name for the regular vending machine item instantiation
     */
    public String getItemNameInput ()
    {
        return itemNameFld.getText();
    }
    /**
     * Returns the inputted item quantity for the regular vending machine item instantiation
     * @return the inputted item quantity for the regular vending machine item instantiation
     */
    public String getItemQuantityInput ()
    {
        return itemQuantityFld.getText();
    }
    /**
     * Returns the inputted item price for the regular vending machine item instantiation
     * @return the inputted item price for the regular vending machine item instantiation
     */
    public String getItemPriceInput ()
    {
        return itemPriceFld.getText();
    }
    /**
     * Returns the inputted item calories for the regular vending machine item instantiation
     * @return the inputted item calories for the regular vending machine item instantiation
     */
    public String getItemCalorieInput ()
    {
        return itemCalorieFld.getText();
    }
    /**
     * Clears the maximum slot number field of the regular vending machine instantiation page
     */
    public void clearMaxSlotNumFld ()
    {
        maxSlotNumFld.setText("");
    }
    /**
     * Redirects the current page to a new page
     * @param display The keyword of the next page
     */
    public void nextCard (String display)
    {
        cards.show(contPanel, display);
    }

    public void setCreateVmBtnListener(ActionListener actionListener) {
        createVmBtn.addActionListener(actionListener);
    }

    public void setMaxSlotNumBtnListener(ActionListener actionListener) {
        maxSlotNumBtn.addActionListener(actionListener);
    }

    public void setNextItemBtnListener(ActionListener actionListener) {
        nextItemBtn.addActionListener(actionListener);
    }

    public void setTestVmBtnListener(ActionListener actionListener) {
        testVmBtn.addActionListener(actionListener);
    }

    public void setMainMenuBtnListener(ActionListener actionListener) {
        mainMenuBtn.addActionListener(actionListener);
    }

    public void setMainMenuBtn2Listener(ActionListener actionListener) {
        mainMenuBtn2.addActionListener(actionListener);
    }

    public void setMainMenuBtn3Listener(ActionListener actionListener) {
        exitTestBtn.addActionListener(actionListener);
    }

    public void setRegVmBtnListener(ActionListener actionListener) {
        regVmBtn.addActionListener(actionListener);
    }

    public void setSpecialVmBtnListener(ActionListener actionListener) {
        specialVmBtn.addActionListener(actionListener);
    }

    public void setVendingFtBtnListener(ActionListener actionListener) {
        vendingFtBtn.addActionListener(actionListener);
    }

    public void setMaintenanceFtBtnListener(ActionListener actionListener) {
        maintenanceFtBtn.addActionListener(actionListener);
    }

    public void setPayDenominationsBtnListener(ActionListener actionListener) {
        payDenominationsBtn.addActionListener(actionListener);
    }

    public void setDenominationBtnListener(ActionListener actionListener) {
        payDenominationsBtn.addActionListener(actionListener);
    }
    public void setDispenseItemBtnListener(ActionListener actionListener) {
        dispenseItemBtn.addActionListener(actionListener);
    }

    public void setCalorieBtnListener(ActionListener actionListener) {
        calorieBtn.addActionListener(actionListener);
    }

    public void setExitTestBtnListener(ActionListener actionListener) {
        exitTestBtn.addActionListener(actionListener);
    }

    public void setExitTestBtn2Listener(ActionListener actionListener) {
        exitTestBtn2.addActionListener(actionListener);
    }

    public void setExitTestBtn3Listener(ActionListener actionListener) {
        exitTestBtn3.addActionListener(actionListener);
    }

    public void setExitTestBtn4Listener(ActionListener actionListener) {
        exitTestBtn4.addActionListener(actionListener);
    }

    public void setExitTestBtn5Listener(ActionListener actionListener) {
        exitTestBtn5.addActionListener(actionListener);
    }

    public void setExitTestBtn6Listener(ActionListener actionListener) {
        exitTestBtn6.addActionListener(actionListener);
    }

    public void setStockItemBtnListener(ActionListener actionListener) {
        stockItemBtn.addActionListener(actionListener);
    }

    public void setSetPriceBtnListener(ActionListener actionListener) {
        setPriceBtn.addActionListener(actionListener);
    }

    public void setReplenishMoneyBtnListener(ActionListener actionListener) {
        replenishMoneyBtn.addActionListener(actionListener);
    }

    public void setCollectMoneyBtnListener(ActionListener actionListener) {
        collectMoneyBtn.addActionListener(actionListener);
    }

    public void setDisplayTransactionsBtnListener(ActionListener actionListener) {
        displayTransactionsBtn.addActionListener(actionListener);
    }

    public void setEnterItemBtnListener(ActionListener actionListener) {
        enterItemBtn.addActionListener(actionListener);
    }

    public void setInsertMoneyBtnListener(ActionListener actionListener) {
        insertMoneyBtn.addActionListener(actionListener);
    }
}

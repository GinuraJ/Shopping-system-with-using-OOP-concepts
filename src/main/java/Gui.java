import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
public class Gui{
    public Gui(){

        WestminsterShoppingManager w = new WestminsterShoppingManager();

        // Button for open cart window
        viewShoppingCart = new JButton("View Shopping Cart");
        viewShoppingCart.setSize(150,50);
        viewShoppingCart.setLocation(840,100);

        // Button for open main window
        backToHome = new JButton("Back to home");
        backToHome.setSize(150,50);
        backToHome.setLocation(840,100);

        // Button for add products to cart
        addToCart = new JButton("Add to cart");
        addToCart.setSize(150,50);
        addToCart.setLocation(840,800);



        // Button for remove product from shopping cart
        removeProductButton = new JButton("🗑");
        removeProductButton.setSize(50,50);
        removeProductButton.setLocation(940,545);
        removeProductButton.setFont(new Font("Arial", Font.PLAIN, 24));
        removeProductButton.setVisible(false);

        // Main window details
        mainWindow = new JFrame();
        mainWindow.setSize(1000, 900);
        mainWindow.setLayout(null);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.add(viewShoppingCart);
        mainWindow.add(addToCart);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);

        // Create a panel for store product details
        detailsPane = new JPanel();
        detailsPane.add(productDetails);
        detailsPane.add(detailsID);
        detailsPane.add(detailsName);
        detailsPane.add(detailsCategory);
        detailsPane.add(detailsUnique1);
        detailsPane.add(detailsUnique2);
        detailsPane.add(detailsAmount);

        // Set the attributes for detail pane
        detailsPane.setLayout(new GridLayout(7,1));
        detailsPane.setSize(700,300);
        detailsPane.setLocation(10,550);
        detailsPane.setVisible(true);


        // Cart window details
        cartWindow = new JFrame();
        cartWindow.setSize(1000, 900);
        cartWindow.setLayout(null);
        cartWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cartWindow.add(backToHome);
        cartWindow.setLocationRelativeTo(null);
        cartWindow.add(removeProductButton);
        cartWindow.setResizable(false);

        // Logging window
        loggingWindow = new JFrame();
        loggingWindow.setSize(500,500);
        loggingWindow.setLayout(null);
        loggingWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        loggingWindow.setLocationRelativeTo(null);
        loggingWindow.setVisible(false);
        loggingWindow.setResizable(false);

        // Create account window
        createAccountWindow = new JFrame();
        createAccountWindow.setSize(500,500);
        createAccountWindow.setLayout(null);
        createAccountWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        createAccountWindow.setLocationRelativeTo(null);
        createAccountWindow.setVisible(false);
        createAccountWindow.setResizable(false);

        // Make changes for main title in main window
        mainTitleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        mainTitleLabel.setBounds(20,0,700,100);
        mainWindow.add(mainTitleLabel);

        // Make changes for title in the shopping cart window
        cartTitleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        cartTitleLabel.setBounds(20,0,700,100);
        cartWindow.add(cartTitleLabel);

        // Price is that pane which store price details in the shopping cart
        pricePane = new JPanel();
        pricePane.setSize(1000,250);
        pricePane.setLayout(new GridLayout(4,2));
        pricePane.setVisible(true);
        pricePane.setLocation(10,550);


        total.setFont(new Font("Arial", Font.BOLD, 20));
        pricePane.add(total);

        totalAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        pricePane.add(totalAmount);

        firstOrder.setFont(new Font("Arial", Font.BOLD, 20));
        pricePane.add(firstOrder);

        firstOrderDiscount.setFont(new Font("Arial", Font.PLAIN, 18));
        pricePane.add(firstOrderDiscount);

        threeItemsDiscount.setFont(new Font("Arial", Font.BOLD, 20));
        pricePane.add(threeItemsDiscount);

        threeItemsDiscountAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        pricePane.add(threeItemsDiscountAmount);

        finalTotal.setFont(new Font("Arial", Font.BOLD, 24));
        pricePane.add(finalTotal);

        finalToatlAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        pricePane.add(finalToatlAmount);

        cartWindow.add(pricePane);


        buyButton.setBounds(0,800, 1000, 50);
        cartWindow.add(buyButton);

        sortButton.setFont(new Font("Arial", Font.PLAIN, 16));
        sortButton.setBounds(300 ,115,80,30);
        mainWindow.add(sortButton);

        // Create table to store products
        DefaultTableModel productTableModel = new DefaultTableModel(productTableData,productTableHead);
        JTable productTable = new JTable(productTableModel);
        JScrollPane productTableScrollpane = new JScrollPane(productTable);
        productTableScrollpane.setBounds(10, 170, 980, 370);

        // Create a table for cart product
        DefaultTableModel cartTableModel = new DefaultTableModel(cartTableData,cartTableHead);
        JTable cartTable = new JTable(cartTableModel);
        JScrollPane cartTableScrollpane = new JScrollPane(cartTable);
        cartTableScrollpane.setBounds(10, 170, 980, 370);


        // Cart table has four columns, this will do hide the first column of the cart table which include product ID
        cartTable.getColumn("id").setMinWidth(0);
        cartTable.getColumn("id").setMaxWidth(0);
        cartTable.getColumn("id").setWidth(0);
        cartTable.getTableHeader().setBorder(new LineBorder(Color.white));

        // Change table appearance
        JTableHeader headerProductTable = productTable.getTableHeader();
        headerProductTable.setFont(new Font("Arial", Font.BOLD, 20));
        productTable.setRowHeight(40);
        headerProductTable.setPreferredSize(new Dimension(headerProductTable.getWidth(), 50));

        // Change cart table appearance
        JTableHeader headerCartTable = cartTable.getTableHeader();
        headerCartTable.setFont(new Font("Arial", Font.BOLD, 20));
        cartTable.setRowHeight(80);
        headerCartTable.setPreferredSize(new Dimension(headerCartTable.getWidth(), 50));

        mainWindow.add(productTableScrollpane);
        mainWindow.setVisible(true);
        cartWindow.add(cartTableScrollpane);
        cartWindow.setVisible(false);

        mainWindow.add(mainTitleLabel);
        mainWindow.add(productTableScrollpane);
        mainWindow.add(detailsPane);

        addToCart.setVisible(true);

        // Add items into table
        for(int i=0; i<WestminsterShoppingManager.productList.size();i++){
            String id = WestminsterShoppingManager.productList.get(i).getProductID();
            String name = WestminsterShoppingManager.productList.get(i).getProductName();
            double price = WestminsterShoppingManager.productList.get(i).getProductPrice();
            if(WestminsterShoppingManager.productList.get(i) instanceof Electronics){
                String category = "Electronic";
                String brand = ((Electronics) WestminsterShoppingManager.productList.get(i)).getBrandName();
                String warranty = ((Electronics) WestminsterShoppingManager.productList.get(i)).getWarrantyPeriod();
                String[] tableRow = {id,name, category,String.valueOf(price),brand + " , " + warranty};
                productTableModel.addRow(tableRow);

            }else{
                String category = "Clothing";
                String size = ((Clothing) WestminsterShoppingManager.productList.get(i)).getClothSize();
                String color = ((Clothing) WestminsterShoppingManager.productList.get(i)).getColour();
                String[] tableRow = {id,name,category, String.valueOf(price),size + " , " + color};
                productTableModel.addRow(tableRow);

            }


        }

        // Set rows colour red which product available quantity are less than forn
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable productTable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(productTable, value, isSelected, hasFocus, row, column);


                String pID = (String) productTableModel.getValueAt(row,0);
                for (int i=0;i<WestminsterShoppingManager.productList.size();i++){
                    if(WestminsterShoppingManager.productList.get(i).getProductID() == pID){

                        if(isSelected){
                            setBackground(Color.BLUE);
                            break;
                        }if (WestminsterShoppingManager.productList.get(i).getAvailableAmount() <= 3){
                            setBackground(new Color(250, 200, 200));
                            break;
                        }else{
                            setBackground(Color.WHITE);
                        }
                    }
                }
                return this;
            }
        };


        productTable.setDefaultRenderer(Object.class, renderer);

        // Create a action when customer press the 'view shopping cart' button
        viewShoppingCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the shopping cart window
                cartWindow.setVisible(true);
                // Hide the main window
                mainWindow.setVisible(false);
            }
        });

        // Set action for 'back to home' button
        backToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the shopping cart window
                cartWindow.setVisible(false);
                // Open the shopping main window
                mainWindow.setVisible(true);
            }
        });

        // This is the method for adding product to shopping cart
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                selectRowNumberForAddCart = productTable.getSelectedRow();
                
                if(productTable.getSelectedRowCount()>0){
                    selectRowProductID = productTable.getValueAt(selectRowNumberForAddCart,0).toString();
                    shoppingCart.addToCart();

                    cartTableModel.setRowCount(0);
                    for (int c=0;c<ShoppingCart.cartList.size();c++){
                        cartRowDetails = new String[]{ShoppingCart.cartList.get(c).getCartProductID(), ShoppingCart.cartList.get(c).getCartProductName() + " " + ShoppingCart.cartList.get(c).getCartProductInfo1() + " " + ShoppingCart.cartList.get(c).getCartProductInfo2(), String.valueOf(ShoppingCart.cartList.get(c).getCartProductAvailableAmount()), String.valueOf(ShoppingCart.cartList.get(c).getCartProductPrice())};
                        cartTableModel.addRow(cartRowDetails);
                    }

                    shoppingCart.calculatePrice();
                    updatePriceLabels();


                }else{
                    JOptionPane.showMessageDialog(null,"Please select a product","Warning",JOptionPane.PLAIN_MESSAGE);
                }
                


            }
        });

        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting() && productTable.getSelectedRow() != -1){
                    addToCart.setVisible(true);

                    int selectRow = productTable.getSelectedRow();

                    detailsID.setText("Product ID : "+productTable.getValueAt(selectRow,0).toString());
                    detailsName.setText("Name : "+productTable.getValueAt(selectRow,1).toString());
                    detailsCategory.setText("Category : "+productTable.getValueAt(selectRow,2).toString());
                    detailsAmount.setText("Items : "+productTable.getValueAt(selectRow,2).toString());

                    for(int j = 0; j<WestminsterShoppingManager.productList.size(); j++){

                        if(WestminsterShoppingManager.productList.get(j).getAvailableAmount() == 0){
                            detailsAmount.setText("Items available : " + "Out of stock");
                            addToCart.setVisible(false);

                        }else {
                            detailsAmount.setText("Items available : " + WestminsterShoppingManager.productList.get(j).getAvailableAmount());
                            addToCart.setVisible(true);

                        }

                        if(WestminsterShoppingManager.productList.get(j).getProductID() == productTable.getValueAt(selectRow,0).toString()){
                            if(WestminsterShoppingManager.productList.get(j) instanceof Electronics){
                                detailsUnique1.setText("Brand Name : " + ((Electronics) WestminsterShoppingManager.productList.get(j)).getBrandName());
                                detailsUnique2.setText("Warranty period : " + ((Electronics) WestminsterShoppingManager.productList.get(j)).getWarrantyPeriod());
                                break;
                            }else{
                                detailsUnique1.setText("Size : " + ((Clothing) WestminsterShoppingManager.productList.get(j)).getClothSize());
                                detailsUnique2.setText("Colour : " + ((Clothing) WestminsterShoppingManager.productList.get(j)).getColour());
                                break;
                            }
                        }
                    }
                }


            }
        });

        // Identify the row that user select in the cart table
        cartTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                selectRowCart = cartTable.getSelectedRow();
                removeProductButton.setVisible(true);
            }
        });

        // When user click the removeProductButton, that user selected row will be deleted
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                selectRowProductIDForRemoveProcess = cartTable.getValueAt(selectRowCart,0).toString();
                shoppingCart.removeFromCart();

                cartTableModel.removeRow(selectRowCart);
                removeProductButton.setVisible(false);

                shoppingCart.calculatePrice();
                updatePriceLabels();

            }
        });

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                shoppingCart.buyProduct();

                int selectedRowInProductTable = productTable.getSelectedRow();
                String selectedRowInProductTableProductID = productTableModel.getValueAt(selectedRowInProductTable,0).toString();

                for (int i=0;i<WestminsterShoppingManager.productList.size();i++){
                    if(selectedRowInProductTableProductID == WestminsterShoppingManager.productList.get(i).getProductID()){
                        detailsAmount.setText("Items available : " +WestminsterShoppingManager.productList.get(i).getAvailableAmount());

                    }
                }
                cartTableModel.setRowCount(0);
                shoppingCart.setFirstPurchase(true);
                shoppingCart.calculatePrice();
                updatePriceLabels();
                w.fileProduct();
            }

        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setDetailLabelsEmpty();

                String comboValue = selectionBox.getSelectedItem().toString();

                if (comboValue == "Electronic"){
                    productTableModel.setRowCount(0);
                    w.sortProductList();
                    for(int i=0; i<WestminsterShoppingManager.productList.size();i++){
                        String id = WestminsterShoppingManager.productList.get(i).getProductID();
                        String name = WestminsterShoppingManager.productList.get(i).getProductName();
                        double price = WestminsterShoppingManager.productList.get(i).getProductPrice();
                        if(WestminsterShoppingManager.productList.get(i) instanceof Electronics){
                            String category = "Electronic";
                            String brand = ((Electronics) WestminsterShoppingManager.productList.get(i)).getBrandName();
                            String warranty = ((Electronics) WestminsterShoppingManager.productList.get(i)).getWarrantyPeriod();
                            String[] tableRow = {id,name, category,String.valueOf(price),brand + " , " + warranty};
                            productTableModel.addRow(tableRow);
                        }
                    }

                }else if (comboValue == "Clothing"){
                    productTableModel.setRowCount(0);
                    w.sortProductList();
                    for(int i=0; i<WestminsterShoppingManager.productList.size();i++){
                        String id = WestminsterShoppingManager.productList.get(i).getProductID();
                        String name = WestminsterShoppingManager.productList.get(i).getProductName();
                        double price = WestminsterShoppingManager.productList.get(i).getProductPrice();
                        if (WestminsterShoppingManager.productList.get(i) instanceof Clothing){
                            String category = "Clothing";
                            String size = ((Clothing) WestminsterShoppingManager.productList.get(i)).getClothSize();
                            String color = ((Clothing) WestminsterShoppingManager.productList.get(i)).getColour();
                            String[] tableRow = {id,name,category, String.valueOf(price),size + " , " + color};
                            productTableModel.addRow(tableRow);
                        }
                    }
                }else if (comboValue == "All"){
                    productTableModel.setRowCount(0);
                    w.sortProductList();
                    for(int i=0; i<WestminsterShoppingManager.productList.size();i++){
                        String id = WestminsterShoppingManager.productList.get(i).getProductID();
                        String name = WestminsterShoppingManager.productList.get(i).getProductName();
                        double price = WestminsterShoppingManager.productList.get(i).getProductPrice();
                        if(WestminsterShoppingManager.productList.get(i) instanceof Electronics){
                            String category = "Electronic";
                            String brand = ((Electronics) WestminsterShoppingManager.productList.get(i)).getBrandName();
                            String warranty = ((Electronics) WestminsterShoppingManager.productList.get(i)).getWarrantyPeriod();
                            String[] tableRow = {id,name, category,String.valueOf(price),brand + " , " + warranty};
                            productTableModel.addRow(tableRow);

                        }else{
                            String category = "Clothing";
                            String size = ((Clothing) WestminsterShoppingManager.productList.get(i)).getClothSize();
                            String color = ((Clothing) WestminsterShoppingManager.productList.get(i)).getColour();
                            String[] tableRow = {id,name,category, String.valueOf(price),size + " , " + color};
                            productTableModel.addRow(tableRow);

                        }
                    }
                }
            }
        });

        // Change font decoration for details labels
        productDetails.setFont(new Font("Arial",Font.BOLD,24));
        detailsID.setFont(new Font("Arial",Font.PLAIN,18));
        detailsName.setFont(new Font("Arial",Font.PLAIN,18));
        detailsCategory.setFont(new Font("Arial",Font.PLAIN,18));
        detailsUnique1.setFont(new Font("Arial",Font.PLAIN,18));
        detailsUnique2.setFont(new Font("Arial",Font.PLAIN,18));
        detailsAmount.setFont(new Font("Arial",Font.PLAIN,18));

        // Creating and make changes for drop down
        String[] dropDownMenu = {"All","Electronic","Clothing"};
        selectionBox = new JComboBox(dropDownMenu);
        selectionBox.setLocation(10,97);
        selectionBox.setSize(200,70);
        selectionBox.setVisible(true);
        mainWindow.add(selectionBox);

        // Set action when customer press options in the drop down menu
        selectionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comboValue = selectionBox.getSelectedItem().toString();
                // Set product detail labels empty
                setDetailLabelsEmpty();


                if (comboValue == "Electronic"){
                    productTableModel.setRowCount(0);
                    // If customer select 'Electronic', remove the all table rows
                    for(int i=0; i<WestminsterShoppingManager.productList.size();i++){
                        String id = WestminsterShoppingManager.productList.get(i).getProductID();
                        String name = WestminsterShoppingManager.productList.get(i).getProductName();
                        double price = WestminsterShoppingManager.productList.get(i).getProductPrice();
                        if(WestminsterShoppingManager.productList.get(i) instanceof Electronics){
                            String category = "Electronic";
                            String brand = ((Electronics) WestminsterShoppingManager.productList.get(i)).getBrandName();
                            String warranty = ((Electronics) WestminsterShoppingManager.productList.get(i)).getWarrantyPeriod();
                            String[] tableRow = {id,name, category,String.valueOf(price),brand + " , " + warranty};
                            // Add electronic product row
                            productTableModel.addRow(tableRow);
                        }
                    }


                }else if (comboValue == "Clothing"){
                    productTableModel.setRowCount(0);
                    // If customer select 'Clothing', remove the all table rows
                    for(int i=0; i<WestminsterShoppingManager.productList.size();i++){
                        String id = WestminsterShoppingManager.productList.get(i).getProductID();
                        String name = WestminsterShoppingManager.productList.get(i).getProductName();
                        double price = WestminsterShoppingManager.productList.get(i).getProductPrice();
                        if (WestminsterShoppingManager.productList.get(i) instanceof Clothing){
                            String category = "Clothing";
                            String size = ((Clothing) WestminsterShoppingManager.productList.get(i)).getClothSize();
                            String color = ((Clothing) WestminsterShoppingManager.productList.get(i)).getColour();
                            String[] tableRow = {id,name,category, String.valueOf(price),size + " , " + color};
                            // Add clothing product row
                            productTableModel.addRow(tableRow);
                        }
                    }
                }else if (comboValue == "All"){
                    // Add all the products into tables which are in the product list
                    productTableModel.setRowCount(0);
                    for(int i=0; i<WestminsterShoppingManager.productList.size();i++){
                        String id = WestminsterShoppingManager.productList.get(i).getProductID();
                        String name = WestminsterShoppingManager.productList.get(i).getProductName();
                        double price = WestminsterShoppingManager.productList.get(i).getProductPrice();
                        if(WestminsterShoppingManager.productList.get(i) instanceof Electronics){
                            String category = "Electronic";
                            String brand = ((Electronics) WestminsterShoppingManager.productList.get(i)).getBrandName();
                            String warranty = ((Electronics) WestminsterShoppingManager.productList.get(i)).getWarrantyPeriod();
                            String[] tableRow = {id,name, category,String.valueOf(price),brand + " , " + warranty};
                            productTableModel.addRow(tableRow);

                        }else{
                            String category = "Clothing";
                            String size = ((Clothing) WestminsterShoppingManager.productList.get(i)).getClothSize();
                            String color = ((Clothing) WestminsterShoppingManager.productList.get(i)).getColour();
                            String[] tableRow = {id,name,category, String.valueOf(price),size + " , " + color};
                            productTableModel.addRow(tableRow);

                        }
                    }
                }
            }
        });

    }
    ShoppingCart shoppingCart = new ShoppingCart();
    JFrame mainWindow;
    JFrame cartWindow;
    JFrame loggingWindow;
    JFrame createAccountWindow;
    JButton viewShoppingCart;
    JButton backToHome;
    JButton addToCart;
    JButton sortButton = new JButton("Sort");
    JButton removeProductButton;
    Object[][] productTableData;
    Object[] productTableHead = {"Product ID","Name","Category","Price","Info"};
    Object[][] cartTableData;
    Object[] cartTableHead = {"id","Product","Quantity","Price"};
    JLabel mainTitleLabel = new JLabel("Westminster shopping centre");
    JLabel cartTitleLabel = new JLabel("Shopping cart");
    JLabel productDetails= new JLabel("Selected product details");
    JLabel detailsID = new JLabel("");
    JLabel detailsName = new JLabel("");
    JLabel detailsCategory = new JLabel("");
    JLabel detailsUnique1 = new JLabel("");
    JLabel detailsUnique2 = new JLabel("");
    JLabel detailsAmount = new JLabel("");
    JLabel userNameLabel = new JLabel("Ginura");
    JComboBox selectionBox;
    int selectRowCart;
    public static int enteredQuantity = 0;
    JPanel detailsPane;
    JPanel pricePane;
    JLabel finalTotal = new JLabel("Final total : ");
    JLabel finalToatlAmount = new JLabel("LKR 0.00");
    JLabel threeItemsDiscount = new JLabel("Three items in same category (20%) : ");
    JLabel threeItemsDiscountAmount = new JLabel("LKR 0.00");
    JLabel firstOrder = new JLabel("First purchase discount (10%) : ");
    JLabel firstOrderDiscount = new JLabel("LKR 0.00");
    JLabel total = new JLabel("Total : ");
    JLabel totalAmount = new JLabel("LKR 0.00");
    JButton buyButton = new JButton("Place order");
    public static String selectRowProductID;
    public static int selectRowNumberForAddCart;
    public static String[] cartRowDetails = new String[]{"a","a","a","a"};
    public static Boolean alreadyInCart = false;
    public static String selectRowProductIDForRemoveProcess;

    // This will update the price labels in shopping cart window
    public void updatePriceLabels(){
        totalAmount.setText("LKR "+shoppingCart.getTotalPrice());
        firstOrderDiscount.setText("LKR "+shoppingCart.getFirstDiscount());
        threeItemsDiscountAmount.setText("LKR "+ shoppingCart.getSameThreeItemsDiscount());
        finalToatlAmount.setText("LKR "+shoppingCart.getFinalPrice());
    }
    // Set all text empty which represents product details in the main window
    public void setDetailLabelsEmpty(){
        detailsID.setText("");
        detailsName.setText("");
        detailsCategory.setText("");
        detailsUnique1.setText("");
        detailsUnique2.setText("");
        detailsAmount.setText("");
    }


}

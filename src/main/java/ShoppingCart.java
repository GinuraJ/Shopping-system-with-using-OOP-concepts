import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ShoppingCart {
    private String cartProductName;
    private String cartProductID;
    private int cartProductAvailableAmount;
    private String cartProductInfo1;
    private String cartProductInfo2;
    private double cartProductPrice;
    String category;
    private int quantity;
    boolean firstPurchase = false;
    private int electronicCount=0;
    private int clothingCount=0;
    private double totalPrice = 0;
    private double sameThreeItemsDiscount = 0;
    private double firstDiscount = 0;
    private double FinalPrice = 0;

    public static ArrayList<ShoppingCart> cartList = new ArrayList<>();

    User user = new User();

    public ShoppingCart(){};
    public ShoppingCart(String cartProductID, String cartProductName, int cartProductAvailableAmount, double cartProductPrice, String cartProductInfo1, String cartProductInfo2,String category) {
        this.cartProductName = cartProductName;
        this.cartProductID = cartProductID;
        this.cartProductAvailableAmount = cartProductAvailableAmount;
        this.cartProductInfo1 = cartProductInfo1;
        this.cartProductInfo2 = cartProductInfo2;
        this.cartProductPrice = cartProductPrice;
        this.category = category;
    }

    public String getCartProductName() {
        return cartProductName;
    }

    public void setCartProductName(String cartProductName) {
        this.cartProductName = cartProductName;
    }

    public String getCartProductID() {
        return cartProductID;
    }

    public void setCartProductID(String cartProductID) {
        this.cartProductID = cartProductID;
    }

    public int getCartProductAvailableAmount() {
        return cartProductAvailableAmount;
    }

    public void setCartProductAvailableAmount(int cartProductAvailableAmount) {
        this.cartProductAvailableAmount = cartProductAvailableAmount;
    }

    public String getCartProductInfo1() {
        return cartProductInfo1;
    }

    public void setCartProductInfo1(String cartProductInfo1) {
        this.cartProductInfo1 = cartProductInfo1;
    }

    public String getCartProductInfo2() {
        return cartProductInfo2;
    }

    public void setCartProductInfo2(String cartProductInfo2) {
        this.cartProductInfo2 = cartProductInfo2;
    }

    public double getCartProductPrice() {
        return cartProductPrice;
    }

    public void setCartProductPrice(double cartProductPrice) {
        this.cartProductPrice = cartProductPrice;
    }

    public int getElectronicCount() {
        return electronicCount;
    }

    public void setElectronicCount(int electronicCount) {
        this.electronicCount = electronicCount;
    }

    public int getClothingCount() {
        return clothingCount;
    }

    public void setClothingCount(int clothingCount) {
        this.clothingCount = clothingCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getSameThreeItemsDiscount() {
        return sameThreeItemsDiscount;
    }

    public void setSameThreeItemsDiscount(double sameThreeItemsDiscount) {
        this.sameThreeItemsDiscount = sameThreeItemsDiscount;
    }

    public double getFirstDiscount() {
        return firstDiscount;
    }

    public void setFirstDiscount(double firstDiscount) {
        this.firstDiscount = firstDiscount;
    }

    public double getFinalPrice() {
        return FinalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        FinalPrice = finalPrice;
    }

    public boolean isFirstPurchase() {
        return firstPurchase;
    }

    public void setFirstPurchase(boolean firstPurchase) {
        this.firstPurchase = firstPurchase;
    }

    // When user select product and press the add to cart button, this set of code will do that.
    public void addToCart(){

        Gui.alreadyInCart = false;

        for (int i=0; i< WestminsterShoppingManager.productList.size();i++){
            if (Gui.selectRowProductID == WestminsterShoppingManager.productList.get(i).getProductID()){
                while (true) {
                    if (WestminsterShoppingManager.productList.get(i).getAvailableAmount() == 0) {
                        // If current iteration product quantity is zero, user will be notified.
                        JOptionPane.showMessageDialog(null, "Sorry, this product is out of stock", "", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    try {
                        // This state opens small pop up window with the test filed for enter quantity user require
                        Gui.enteredQuantity = Integer.parseInt(JOptionPane.showInputDialog("There are only " + WestminsterShoppingManager.productList.get(i).getAvailableAmount() + " items left. \nEnter quantity."));

                        if (Gui.enteredQuantity <= WestminsterShoppingManager.productList.get(i).getAvailableAmount()) {
                            // If user enter proper quantity loop will be breaked.
                            break;
                        } else {
                            // If user enter quantity more than available quantity, error massage box will be printed.
                            JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a value less than or equal to the available amount.", "", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        // If customer, String value for massage box text field it makes an error, to prevent that,this code was writen.
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }

                if(Gui.enteredQuantity == 0){
                    // If user enter '0' as quantity, indicate massage box will be appeared.
                    JOptionPane.showMessageDialog(null, "Item does not add to cart", "Warning", JOptionPane.WARNING_MESSAGE);
                    break;
                }

                // This set of code maintains the count of electronic and clothing products.
                for (int j=0;j<ShoppingCart.cartList.size();j++){
                    // User enter add one product multiple time, cart list quantity should be increased
                    if (WestminsterShoppingManager.productList.get(i).getProductID().equals(ShoppingCart.cartList.get(j).cartProductID)){
                        // According to user input maintain cart list product quantity.
                        ShoppingCart.cartList.get(j).setCartProductAvailableAmount(ShoppingCart.cartList.get(j).getCartProductAvailableAmount()+Gui.enteredQuantity);

                        if(ShoppingCart.cartList.get(j).category == "electronic"){
                            // If current iteration product is electronic product increase the electronic count
                            setElectronicCount(getElectronicCount()+Gui.enteredQuantity);
                        }else{
                            // If current iteration product is clothing product increase the electronic count
                            setClothingCount((getClothingCount()+Gui.enteredQuantity));
                        }

                        // This variable is used to stop new row adding cart product which already in the cart into shopping cart again
                        Gui.alreadyInCart = true;
                        break;
                    }
                }

                // If user add a product into shopping cart which previously not added, this set of code add a new for that.
                if(Gui.alreadyInCart == false){
                    if(WestminsterShoppingManager.productList.get(i) instanceof Electronics){
                        // Create a object in Shopping class, according to electronic product
                        ShoppingCart s1 = new ShoppingCart(WestminsterShoppingManager.productList.get(i).getProductID(),WestminsterShoppingManager.productList.get(i).getProductName(),Gui.enteredQuantity,WestminsterShoppingManager.productList.get(i).getProductPrice(),((Electronics)WestminsterShoppingManager.productList.get(i)).getBrandName(),((Electronics)WestminsterShoppingManager.productList.get(i)).getWarrantyPeriod(),"electronic");
                        // Created object add to cart list
                        ShoppingCart.cartList.add(s1);
                        // Increase the electronic product count
                        setElectronicCount(getElectronicCount()+Gui.enteredQuantity);
                        break;
                    }else{
                        // Create a object in Shopping class, according to clothing product
                        ShoppingCart s1 = new ShoppingCart(WestminsterShoppingManager.productList.get(i).getProductID(),WestminsterShoppingManager.productList.get(i).getProductName(),Gui.enteredQuantity,WestminsterShoppingManager.productList.get(i).getProductPrice(),((Clothing)WestminsterShoppingManager.productList.get(i)).getClothSize(),((Clothing)WestminsterShoppingManager.productList.get(i)).getColour(),"clothing");
                        // Created object add to cart list
                        ShoppingCart.cartList.add(s1);
                        // Increase the electronic product count
                        setClothingCount(getClothingCount()+Gui.enteredQuantity);
                        break;
                    }
                }
            }
        }
    }

    // When user select the shopping cart product and press the delete button, it should be removed from the table. This set of code will do that
    public void removeFromCart(){
        for (int i=0;i<ShoppingCart.cartList.size();i++){
            // Find the product ID which matching in the cart list
            if (ShoppingCart.cartList.get(i).cartProductID.equals(Gui.selectRowProductIDForRemoveProcess)){
                if (ShoppingCart.cartList.get(i).category == "electronic"){
                    // If it is a electronic product, electronic product count should be reduced
                    setElectronicCount(getElectronicCount() - ShoppingCart.cartList.get(i).getCartProductAvailableAmount());
                    // That product is removed from the cart list
                    ShoppingCart.cartList.remove(i);
                    break;
                }else{
                    // If it is a electronic product, clothing product count should be reduced
                    setClothingCount(getClothingCount() - ShoppingCart.cartList.get(i).getCartProductAvailableAmount());
                    // That product is removed from the cart list
                    ShoppingCart.cartList.remove(i);
                    break;
                }

            }
        }
    }

    // The price should be calculated according to changes that customer do
    public void calculatePrice(){

        // Set the electronic product count to zero, when user add or remove product from the cart
        setElectronicCount(0);
        // Set the clothing product count to zero, when user add or remove product from the cart
        setClothingCount(0);
        // Set the total price to zero, when user add or remove product from the cart
        setTotalPrice(0);
        // Set the discount price to zero, when user add or remove product from the cart
        setSameThreeItemsDiscount(0);
        // Set the final price count to zero, when user add or remove product from the cart
        setFinalPrice(0);


        // Calculate the total price
        for (int k=0;k<ShoppingCart.cartList.size();k++){
            setTotalPrice(getTotalPrice()+(ShoppingCart.cartList.get(k).cartProductPrice*ShoppingCart.cartList.get(k).getCartProductAvailableAmount()));
            if(ShoppingCart.cartList.get(k).category == "electronic"){
                setElectronicCount(getElectronicCount()+ShoppingCart.cartList.get(k).getCartProductAvailableAmount());
            }else
                setClothingCount(getClothingCount()+ShoppingCart.cartList.get(k).getCartProductAvailableAmount());
        }


        // determine whether if there are three or more product under any category
        if (getElectronicCount()>=3 || getClothingCount()>=3){
            // Calculate the three item discount (20%)
            setSameThreeItemsDiscount((getTotalPrice()*20)/100);
        }else{
            // If there are no three products under any category, discount will be zero
            setSameThreeItemsDiscount(0);
        }

        // If user has not pressed the place order button first purchase discount will be applicable
        if(firstPurchase == false){
            // Calculated the discount (10%)
            setFirstDiscount((getTotalPrice()*10)/100);
        }else{
            // If user has pressed the place order button before, discount set to zero
            setFirstDiscount(0);
        }

        // Calculate the final product according to the discount prices
        setFinalPrice(getTotalPrice()-(getSameThreeItemsDiscount()+getFirstDiscount()));


    }

    // This set of code when customer press the place order, shopping cart should be empty and prices set to zero.
    public void buyProduct(){

        for (int i=0;i<ShoppingCart.cartList.size();i++){
            for (int j=0;j<WestminsterShoppingManager.productList.size();j++){
                if (WestminsterShoppingManager.productList.get(j).getProductID() == ShoppingCart.cartList.get(i).getCartProductID()){
                    // The customer ordered product's quantity will be reduced from the inventory
                    WestminsterShoppingManager.productList.get(j).setAvailableAmount(WestminsterShoppingManager.productList.get(j).getAvailableAmount()-ShoppingCart.cartList.get(i).getCartProductAvailableAmount());
                    break;
                }
            }
        }
        // This code is used to remove all the rows in the shopping cart table.
        ShoppingCart.cartList.clear();


    }

}

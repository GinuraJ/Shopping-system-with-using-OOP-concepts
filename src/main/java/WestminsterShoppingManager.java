import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager{
    Scanner input = new Scanner(System.in);
    public static ArrayList<Product> productList = new ArrayList<>();

    @Override
    public void addProduct() {

        // Check the system has more than 50 products
        if(productList.size()>= 50){
            // If there are 50 products in the system, notify manager that more product cannot be added
            System.out.println("Only 50 items can be added to system. System has reached to maximum amount");
        }
        // If current product amount is less than to 50, product will be added
        else{
            // ask the product category
            System.out.print("Enter product category   : ");
            String category = input.nextLine();
            System.out.println();

            // If it is a electronic product create a electronic object
            if(category.equals("electronic")){
                // call the add method in the clothing class
                Product ele = new Electronics();
                ele.addProductToSystem();
            // If it is a clothing product create a clothing object
            } else if (category.equals("clothing") ) {
                Product clo = new Clothing();
                // call the add method in the electronic class
                clo.addProductToSystem();
            }else
                System.out.println("wrong input");
        }


    }
    // This method is called when manager enter 'add' option

    @Override
    public void removeProductToSystem() {

        boolean removeProductNotFound = true;

        // Asks the product ID which manager wants to remove
        System.out.print("Enter the product ID you want to remove : ");
        String removeID = input.nextLine();
        // This loop find the that product ID in the productList array list
        for(int i = 0; i<productList.size(); i++){
            if(productList.get(i).getProductID().equals(removeID)){
                // The object which contains the that product ID wis removed
                productList.remove(i);
                // This statement will notify the manager that product has been added
                System.out.println(removeID + " removed successfully. Currently, "+ productList.size()+" product in the shop");
                // This variable is used to determine, if user enter invalid product ID
                removeProductNotFound = false;
                break;
            }else{
                // If loop unable to find the product match with user entered product ID, this set to true
                removeProductNotFound = true;
            }
        }
        // If user enter invalid product ID, this massage will prints
        if (removeProductNotFound == true){
            System.out.println("Invalid product ID");
        }
    }
    // This method is called when manager enter 'remove' option

    @Override
    public void productListToSystem() {
        System.out.println("Product list ____________________________ \n");
        for(int j = 0; j<productList.size(); j++){
            System.out.println(productList.get(j).toString());
        }
        System.out.println("_________________________________________");
    }
    // Print all the product with the relevant details.
    @Override
    public void fileProduct() {

        try {
            // Create a FileWriter object to write to the file "w1998731DataFile.txt"
            PrintWriter w1998731File = new PrintWriter("w1998731DataFile.txt");
            // Write all the details line by line in the txt file
            for(int i=0;i<productList.size();i++){
                if (productList.get(i) instanceof Electronics){
                    w1998731File.println("Electronic");
                    w1998731File.println(productList.get(i).getProductID());
                    w1998731File.println(productList.get(i).getProductName());
                    w1998731File.println(productList.get(i).getAvailableAmount());
                    w1998731File.println(productList.get(i).getProductPrice());
                    w1998731File.println(((Electronics) productList.get(i)).getBrandName());
                    w1998731File.println(((Electronics) productList.get(i)).getWarrantyPeriod());
                }else {
                    w1998731File.println("Clothing");
                    w1998731File.println(productList.get(i).getProductID());
                    w1998731File.println(productList.get(i).getProductName());
                    w1998731File.println(productList.get(i).getAvailableAmount());
                    w1998731File.println(productList.get(i).getProductPrice());
                    w1998731File.println(((Clothing) productList.get(i)).getClothSize());
                    w1998731File.println(((Clothing) productList.get(i)).getColour());
                }
            }

            w1998731File.flush(); // Flush the buffer to ensure data is written to the file

            // Close the file to release resources
            w1998731File.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }
    // Save current product and details in the product list in txt file.

    @Override
    public void sortProductList() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getProductID().compareTo(p2.getProductID());
            }
            // Sort products in product list according to product ID
        });
    }
    // Sort products in product list according to product ID

    @Override
    public void loadFile(){
        try {

            //
            Scanner infile = new Scanner(new File("w1998731DataFile.txt"));

            while (infile.hasNextLine()){
                String category = infile.nextLine().toString();

                if(category == null){
                    break;
                }else {
                    if(category.equals("Electronic")){
                        String ID = infile.nextLine();
                        String name = infile.nextLine();
                        int quantity = Integer.parseInt(infile.nextLine());
                        double price = Double.parseDouble(infile.nextLine());
                        String brand = infile.nextLine();
                        String warranty = infile.nextLine();
                        Product e = new Electronics(ID,name,quantity,price,brand,warranty);
                        productList.add(e);

                    }else {
                        String ID = infile.nextLine();
                        String name = infile.nextLine();
                        int quantity = Integer.parseInt(infile.nextLine());
                        double price = Double.parseDouble(infile.nextLine());
                        String size = infile.nextLine();
                        String colour = infile.nextLine();

                        Product e = new Clothing(ID,name,quantity,price,size,colour);
                        productList.add(e);

                    }
                }


            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    // This method recall the all information saved in  the txt file

}

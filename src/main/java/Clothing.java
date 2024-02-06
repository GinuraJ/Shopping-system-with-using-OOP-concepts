import java.util.InputMismatchException;
import java.util.Scanner;

public class Clothing extends Product{
    private String clothSize;
    private String colour;

    Scanner input = new Scanner(System.in);

    public Clothing(){}

    public Clothing(String productID, String productName, int availableAmmount, double productPrice,String clothSize,String colour) {
        super(productID, productName, availableAmmount, productPrice);
        this.clothSize = clothSize;
        this.colour = colour;
    }

    public String getClothSize() {
        return clothSize;
    }

    public void setClothSize(String clothSize) {
        this.clothSize = clothSize;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return super.toString() + "Cloth size : " + clothSize + ", Cloth colour : " + colour;
    }

    @Override
    void addProductToSystem() {
        System.out.print("Enter product productID  : ");
        String productID = input.nextLine();
        System.out.println();
        System.out.print("Enter  Product name      : ");
        String productName = input.nextLine();
        System.out.println();
        int numOfProduct=0;
        boolean numOfProductInt = true;
        while (numOfProductInt) {
            try {
                System.out.print("Enter no of products     : ");
                numOfProduct = input.nextInt();
                System.out.println();
                numOfProductInt = false;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer");
                System.out.println();
                input.nextLine();
            }
        }



        double priceProduct = 0;
        boolean priceProductDouble = true;

        while (priceProductDouble){
            try {
                System.out.print("Enter product price      : ");
                priceProduct = input.nextDouble();
                System.out.println();
                priceProductDouble = false;
            }catch (InputMismatchException e){
                System.out.println("Please enter double value");
                System.out.println();
                input.nextLine();
            }
        }

        input.nextLine();
        System.out.print("Enter cloth size         : ");
        String clothSize = input.nextLine();
        System.out.println();
        System.out.print("Enter cloth colour       : ");
        String clothColour = input.nextLine();
        System.out.println();

        Product c = new Clothing(productID,productName,numOfProduct,priceProduct,clothSize,clothColour);

        System.out.println("Product added successfully");
        WestminsterShoppingManager.productList.add(c);
    }

}

import java.util.InputMismatchException;
import java.util.Scanner;

public class Electronics extends Product {
    private String brandName;
    private String warrantyPeriod;

    Scanner input = new Scanner(System.in);

    public Electronics(){}
    public Electronics(String productID, String productName, int availableAmmount, double productPrice, String brandName, String warrantyPeriod) {
        super(productID, productName, availableAmmount, productPrice);
        this.brandName = brandName;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return super.toString() + "Brand name : " + brandName + ", Warranty Period : " + warrantyPeriod;
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

        System.out.print("Enter product warranty   : ");
        String warrantyProduct = input.nextLine();
        System.out.println();
        System.out.print("Enter product brand name : ");
        String productBrandName = input.nextLine();
        System.out.println();

        Product e = new Electronics(productID,productName,numOfProduct,priceProduct,productBrandName,warrantyProduct);

        System.out.println("Product added successfully");
        WestminsterShoppingManager.productList.add(e);
    }
}

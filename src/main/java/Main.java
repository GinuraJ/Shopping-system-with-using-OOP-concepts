import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        WestminsterShoppingManager w = new WestminsterShoppingManager();

        // This variable is used for main while loop
        boolean wantMenu = true;
        boolean managerWants = true;
        // This variable is used for choosing another option menu
        boolean anotherOption = true;
        Scanner scanner = new Scanner(System.in);
        // By calling this method, saved data in file automatically update when program initiate
        w.loadFile();
        // This loop is used for determine user is a customer or a manager
        while (wantMenu == true) {
            System.out.print("Enter 'customer', if you are a customer \nEnter 'manager', if you are a manager \nEnter 'exit', if you want to exit : ");
            String customerOrManager = scanner.nextLine();
            System.out.println();
            if (customerOrManager.equals("customer")) {
                // If user select customer, GUI will be opened
                SwingUtilities.invokeLater(() -> new Gui());
                anotherOption = true;
            } else if (customerOrManager.equals("manager")) {
                //If user select manager, manager options will be printed by this
                managerWants = true;
                while (managerWants){
                    System.out.println("--------------- Option menu --------------");
                    System.out.println("Add products to 0the system       : add");
                    System.out.println("Remove products from the system   : remove");
                    System.out.println("Want to know list of the products : list");
                    System.out.println("Save in the file                  : Save");
                    System.out.println("------------------------------------------");

                    System.out.println();
                    // Ask what option, manager requires
                    System.out.print("Enter option : ");
                    String option = scanner.nextLine();
                    System.out.println();

                    if (option.equals("add")) {
                        // When manager enter 'add', the method which is adding product into system will be called.
                        w.addProduct();
                    } else if (option.equals("remove")) {
                        // When manager enter 'remove', the method which is removing product from the system will be called.
                        w.removeProductToSystem();
                    } else if (option.equals("list")) {
                        // When manager enter 'list', the method which is printed all the products in the system.
                        w.productListToSystem();
                    } else if (option.equals("gui")) {
                        SwingUtilities.invokeLater(() -> new Gui());
                    }else if (option.equals("save")) {
                        // When manager enter 'save', current products in the ProductList arraylist, will be saved in the txt file.
                        w.fileProduct();
                    }else{
                        System.out.println("Invalid input");
                        continue;
                    }
                    // After calling selected method, system asks from the manager whether wants to continue or not.
                    while (anotherOption){
                        System.out.println();
                        System.out.print("Do you want other option(yes/no) : ");
                        String wantOption = scanner.nextLine();
                        System.out.println();

                        if (wantOption.equals("no")) {
                            // If manager enter 'no', these variable set false to stop manager option loop and this loop to stop.
                            wantMenu = true;
                            managerWants = false;
                            anotherOption = false;
                            System.out.println("Thank you manager!!");
                            System.out.println();
                        } else if (wantOption.equals("yes")) {
                            // If manager enter 'yes', again manager options will be shown.
                            managerWants = true;
                            break;
                        }else{
                            // If manager enter invalid option instead of entering 'yes' or 'no', this massage print and ask again option.
                            System.out.println("Invalid option. (yes/no)");
                        }
                    }
                }

            }else{
                // If manager enter invalid input rather than entering 'customer' or 'manager', this error massage print and ask main option again.
                System.out.println("Please enter whether your a manager or a customer.");
                System.out.println();
            }


        }

    }
}
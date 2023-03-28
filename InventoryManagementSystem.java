import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an empty inventory
        Inventory inventory = new Inventory();

        while (true) {
            // Display the menu options
            System.out.println("Inventory Management System");
            System.out.println("---------------------------");
            System.out.println("1. Add a product");
            System.out.println("2. Edit a product");
            System.out.println("3. Delete a product");
            System.out.println("4. View inventory report");
            System.out.println("5. Exit");

            // Get the user's choice
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add a product to the inventory
                    System.out.println("Enter the name of the product:");
                    String name = scanner.nextLine();
                    System.out.println("Enter the quantity of the product:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the location of the product:");
                    String location = scanner.nextLine();
                    inventory.addProduct(new Product(name, quantity, location));
                    System.out.println("Product added successfully.");
                    break;

                case 2:
                    // Edit a product in the inventory
                    System.out.println("Enter the name of the product to edit:");
                    String searchName = scanner.nextLine();
                    System.out.println("Enter the new quantity of the product:");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the new location of the product:");
                    location = scanner.nextLine();
                    if (inventory.editProduct(searchName, new Product(searchName, quantity, location))) {
                        System.out.println("Product edited successfully.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    // Delete a product from the inventory
                    System.out.println("Enter the name of the product to delete:");
                    String deleteName = scanner.nextLine();
                    if (inventory.deleteProduct(deleteName)) {
                        System.out.println("Product deleted successfully.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    // View an inventory report
                    inventory.generateReport();
                    break;

                case 5:
                    // Exit the program
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Inventory {
    private ArrayList<Product> products;

    public Inventory() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public boolean editProduct(String searchName, Product newProduct) {
        for (int i = 0; i < this.products.size(); i++) {
            Product product = this.products.get(i);
            if (product.getName().equals(searchName)) {
                this.products.set(i, newProduct);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String deleteName) {
        for (int i = 0; i < this.products.size(); i++) {
            Product product = this.products.get(i);
            if (product.getName().equals(deleteName)) {
                this.products.remove(i);
                return true;
            }
        }
        return false;
    }

    public void generateReport() {
        System.out.println("Inventory Report");
        System.out.println("----------------");
        System.out.printf("%-20s %-10s %-10s\n", "Product Name", "Quantity", "Location");
        for (Product product : this.products) {
            System.out.printf("%-20s %-10d %-10s\n", product.getName(), product.getQuantity(), product.getLocation());
        }
    }
}

class Product {
    private String name;
    private int quantity;
    private String location;

    public Product(String name, int quantity, String location) {
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getLocation() {
        return this.location;
    }
}

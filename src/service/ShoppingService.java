package service;

import model.Product;
import model.CartItem;
import model.User;
import util.Validation;
import util.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ShoppingService {

    private Scanner input = new Scanner(System.in);
    private CartService cartService = new CartService();

    private User user = null;
    private boolean couponUsed = false;
    private double discountPercent = 0;

    private String[] categories = {"Men", "Women", "Kids", "Electronics"};

    private String[][] products = {
            {"T-Shirt", "Jeans", "Jacket"},
            {"Dress", "Skirt", "Bag"},
            {"Kids Shirt", "Toy Car", "School Bag"},
            {"Laptop", "Headphones", "Phone"}
    };

    private double[][] prices = {
            {200, 350, 500},
            {300, 250, 150},
            {120, 80, 220},
            {8000, 450, 6000}
    };

    public void start() {
        int choice;
        do {
            printMenu();
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> displayCategories();
                case 2 -> displayProducts();
                case 3 -> addToCart();
                case 4 -> viewCart();
                case 5 -> applyDiscount();
                case 6 -> checkout();
                case 0 -> System.out.println("Thank you for shopping!");
                default -> System.out.println("Invalid option!");
            }

        } while (choice != 0);
    }

    private void printMenu() {
        System.out.println("\n===== ONLINE SHOPPING SYSTEM =====");
        System.out.println("1. Display Categories");
        System.out.println("2. Display Products");
        System.out.println("3. Add to Cart");
        System.out.println("4. View Cart");
        System.out.println("5. Apply Discount");
        System.out.println("6. Checkout");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    public void displayCategories() {
        System.out.println("\n=== Categories ===");
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
         System.out.println("\nPress Enter to return to menu...");
    input.nextLine();
    }

    public void displayProducts() {
        displayCategories();
        System.out.print("Select category: ");
        int c = input.nextInt();
        input.nextLine();

        if (c < 1 || c > categories.length) {
            System.out.println("Invalid category!");
            return;
        }

        int index = c - 1;

        System.out.println("\n=== Products in " + categories[index] + " ===");
        for (int i = 0; i < products[index].length; i++) {
            System.out.println((i + 1) + ". " +
                    products[index][i] +
                    " - " + prices[index][i] + " EGP");
        }
         System.out.println("\nPress Enter to return to menu...");
    input.nextLine();
    }

    public void addToCart() {
        displayProducts();

        System.out.print("Enter category number: ");
        int c = input.nextInt();

        System.out.print("Enter product number: ");
        int p = input.nextInt();

        System.out.print("Enter quantity: ");
        int q = input.nextInt();

        int catIndex = c - 1;
        int prodIndex = p - 1;

        if (catIndex < 0 || catIndex >= products.length ||
                prodIndex < 0 || prodIndex >= products[catIndex].length) {
            System.out.println("Invalid product!");
            return;
        }

        Product prod = new Product(
                products[catIndex][prodIndex],
                prices[catIndex][prodIndex]
        );

        cartService.addProduct(prod, q);

        System.out.println(q + " x " + prod.getName() + " added to cart!");
    }

    public void viewCart() {
        System.out.println("\n=== CART ===");

        if (cartService.isEmpty()) {
            System.out.println("Cart is empty!");
             System.out.println("\nPress Enter to return to menu...");
    input.nextLine();
            return;
        }

        double total = 0;

        for (CartItem item : cartService.getItems()) {
            System.out.println(item.getProduct().getName() +
                    " x " + item.getQuantity() +
                    " = " + item.getTotalPrice() + " EGP");
            total += item.getTotalPrice();
        }

        System.out.println("TOTAL: " + total + " EGP");
         System.out.println("\nPress Enter to return to menu...");
    input.nextLine();
    }

    public void applyDiscount() {

        if (couponUsed) {
            System.out.println("Coupon already used!");
            return;
        }

        System.out.print("Enter coupon code: ");
        String code = input.nextLine().trim().toUpperCase();

        switch (code) {
            case "SALE10" -> discountPercent = 10;
            case "VIP20" -> discountPercent = 20;
            case "MEGA30" -> discountPercent = 30;
            default -> {
                System.out.println("Invalid coupon!");
                 System.out.println("\nPress Enter to return to menu...");
    input.nextLine();
                return;
            }
        }

        couponUsed = true;
        System.out.println("Discount applied: " + discountPercent + "%");
         System.out.println("\nPress Enter to return to menu...");
    input.nextLine();
    }

    public void checkout() {

        if (cartService.isEmpty()) {
            System.out.println("Cart is empty!");
             System.out.println("\nPress Enter to return to menu...");
    input.nextLine();
            return;
        }

        if (user == null) {

            String name;
            do {
                System.out.print("Enter your name: ");
                name = input.nextLine();
                if (Validation.isEmpty(name)) {
                    System.out.println("Name cannot be empty!");
                }
            } while (Validation.isEmpty(name));


            String phone;
            do {
                System.out.print("Enter phone: ");
                phone = input.nextLine();
                if (!Validation.isValidPhone(phone)) {
                    System.out.println("Phone must be 11 digits!");
                }
            } while (!Validation.isValidPhone(phone));


            String address;
            do {
                System.out.print("Enter address: ");
                address = input.nextLine();
                if (Validation.isEmpty(address)) {
                    System.out.println("Address cannot be empty!");
                }
            } while (Validation.isEmpty(address));

            user = new User(name, phone, address);
        }

        double total = cartService.getTotal();
        double finalPrice = total - (total * discountPercent / 100);

        // Print invoice on screen
        Utils.printLine();
        System.out.println("Order Number: " + Utils.generateOrderNumber());
        Utils.printLine();
        System.out.println("Customer: " + user.getName());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Address: " + user.getAddress());
        Utils.printLine();

        for (CartItem item : cartService.getItems()) {
            System.out.println(item.getProduct().getName() +
                    " x " + item.getQuantity() +
                    " = " + item.getTotalPrice() + " EGP");
        }

        Utils.printLine();
        System.out.println("Total: " + total + " EGP");
        System.out.println("Discount: " + discountPercent + "%");
        System.out.println("Final Price: " + finalPrice + " EGP");
        Utils.printLine();

        saveInvoice(total, finalPrice);
         System.out.println("\nPress Enter to return to menu...");
    input.nextLine();
    }

    private void saveInvoice(double total, double finalPrice) {

        try {
            FileWriter fw = new FileWriter("invoice.txt");

            fw.write("===== INVOICE =====\n");
            fw.write("Name: " + user.getName() + "\n");
            fw.write("Phone: " + user.getPhone() + "\n");
            fw.write("Address: " + user.getAddress() + "\n");
            fw.write("----------------------------\n");

            for (CartItem item : cartService.getItems()) {
                fw.write(item.getProduct().getName() +
                        " x " + item.getQuantity() +
                        " = " + item.getTotalPrice() + " EGP\n");
            }

            fw.write("----------------------------\n");
            fw.write("Total: " + total + " EGP\n");
            fw.write("Discount: " + discountPercent + "%\n");
            fw.write("Final Price: " + finalPrice + " EGP\n");
            fw.write("============================\n");

            fw.close();

            System.out.println("Invoice saved to invoice.txt");

        } catch (IOException e) {
            System.out.println("Error saving invoice file!");
        }
    }
}

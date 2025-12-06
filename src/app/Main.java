package app;

import service.ShoppingService;

public class Main {
    public static void main(String[] args) {
       
        ShoppingService shop = new ShoppingService();
        shop.start();
    }
}

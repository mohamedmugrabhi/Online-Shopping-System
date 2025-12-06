package service;

import model.CartItem;
import model.Product;
import java.util.ArrayList;

public class CartService {

    private ArrayList<CartItem> cart = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        cart.add(new CartItem(product, quantity));
    }

    public ArrayList<CartItem> getItems() {
        return cart;
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public double getTotal() {
        double sum = 0;
        for (CartItem item : cart) {
            sum += item.getTotalPrice();
        }
        return sum;
    }
}

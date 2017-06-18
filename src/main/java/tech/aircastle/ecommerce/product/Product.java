package tech.aircastle.ecommerce.product;

import tech.aircastle.ecommerce.CartItem;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class Product extends CartItem {

    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

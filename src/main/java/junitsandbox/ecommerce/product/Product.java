package junitsandbox.ecommerce.product;

import junitsandbox.ecommerce.CartItem;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class Product extends CartItem {

    final String name;
    final double price;

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

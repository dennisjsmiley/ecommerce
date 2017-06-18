package tech.aircastle;

import tech.aircastle.ecommerce.Cart;

/**
 * Created by Dennis Smiley on 1/30/17.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("This is main.");
        Cart cart = new Cart();
        boolean isNullInstanceOfCart = null instanceof Cart;
        System.out.println("isNullInstanceOfCart: " + isNullInstanceOfCart);
    }
}

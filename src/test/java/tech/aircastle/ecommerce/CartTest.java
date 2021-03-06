package tech.aircastle.ecommerce;

import tech.aircastle.ecommerce.coupon.FlatAmountOffCartAfterNthProductCoupon;
import tech.aircastle.ecommerce.coupon.PercentOffAllCartItemsCoupon;
import tech.aircastle.ecommerce.coupon.PercentOffNextProductCoupon;
import tech.aircastle.ecommerce.product.Product;
import tech.aircastle.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class CartTest {

    final static double maxDelta = Util.MAX_DELTA;

    @Test
    public void testFlatAmountOffCartAfterNthProductCoupon() {
        Cart cart = new Cart();

        for (int i = 0; i < 2; i ++) {
            cart.addProduct(new Product("Jacket", 100));
        }

        assertEquals(200, cart.calculateTotal(), maxDelta);

        cart.addCoupon(new FlatAmountOffCartAfterNthProductCoupon(10, "Jacket", 2));

        assertEquals(190, cart.calculateTotal(), maxDelta);

        cart.addCoupon(new FlatAmountOffCartAfterNthProductCoupon(10, "Jacket", 2));

        assertEquals(180, cart.calculateTotal(), maxDelta);
    }

    @Test
    public void testPercentOffAllCartItemsCoupon() {
        Cart cart = new Cart();

        cart.addProduct(new Product("Jacket", 100));

        cart.addCoupon(new PercentOffAllCartItemsCoupon(10));

        assertEquals(90, cart.calculateTotal(), maxDelta);

        cart.addProduct(new Product("Jacket", 100));

        assertEquals(190, cart.calculateTotal(), maxDelta);

        cart.addProduct(new Product("Watch", 10));

        assertEquals(200, cart.calculateTotal(), maxDelta);

        cart.addCoupon(new PercentOffAllCartItemsCoupon(50));

        assertEquals(100, cart.calculateTotal(), maxDelta);

    }

    @Test
    public void testPercentOffNextProductCoupon() {
        Cart cart = new Cart();

        cart.addProduct(new Product("Jacket", 100));

        assertEquals(100, cart.calculateTotal(), maxDelta);

        cart.addCoupon(new PercentOffNextProductCoupon(10));

        assertEquals(100, cart.calculateTotal(), maxDelta);

        cart.addProduct(new Product("Jacket", 100));

        assertEquals(190, cart.calculateTotal(), maxDelta);

        cart.addProduct(new Product("Jacket", 100));

        assertEquals(290, cart.calculateTotal(), maxDelta);
    }

    @Test
    public void testCartMain() {
        Cart cart = new Cart();

        // 1.
        for (int i = 0; i < 3; i++) {
            cart.addProduct(new Product("T-Shirt", 20));
        }

        // 2.
        assertEquals(60, cart.calculateTotal(), maxDelta);

        // 3.
        cart.addCoupon(new PercentOffNextProductCoupon(20.0));

        // 4.
        assertEquals(60, cart.calculateTotal(), maxDelta);

        // 5.
        for (int i = 0; i < 2; i++) {
            cart.addProduct(new Product("Jacket", 100));
        }

        // 6.
        assertEquals(240, cart.calculateTotal(), maxDelta);

        // 7.
        for (int i = 0; i < 10; i++) {
            cart.addProduct(new Product("Bracelet", 10));
        }

        // 8.
        assertEquals(340, cart.calculateTotal(), maxDelta);

        // 9.
        cart.addCoupon(new PercentOffAllCartItemsCoupon(10.0));

        // 10.
        assertEquals(306, cart.calculateTotal(), maxDelta);

        // 11.
        for (int i = 0; i < 2; i++) {
            cart.addCoupon(new FlatAmountOffCartAfterNthProductCoupon(75, "Jacket", 5));
        }

        // 12.
        assertEquals(306, cart.calculateTotal(), maxDelta);

        // 13.
        for (int i = 0; i < 2; i++) {
            cart.addProduct(new Product("Jacket", 100));
        }

        // 14.
        assertEquals(506, cart.calculateTotal(), maxDelta);

        // 15.
        cart.addProduct(new Product("Jacket", 100));

        // 16.
        assertEquals(456, cart.calculateTotal(), maxDelta);

    }

}

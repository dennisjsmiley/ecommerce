package junitsandbox.ecommerce;

import junitsandbox.ecommerce.coupon.FlatAmountOffCartAfterNthProductCoupon;
import junitsandbox.ecommerce.coupon.PercentOffAllCartItemsCoupon;
import junitsandbox.ecommerce.coupon.PercentOffNextCartItemCoupon;
import junitsandbox.ecommerce.product.Product;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class CartTest {

    final static double maxDelta = 0.00001;

    @Test
    public void testCart() {
        Cart cart = new Cart();

        // 1.
        for (int i = 0; i < 3; i++) {
            cart.addProduct(new Product("T-Shirt", 20));
        }

        // 2.
        assertEquals(60, cart.calculateTotal(), maxDelta);

        // 3.
        cart.addCoupon(new PercentOffNextCartItemCoupon(20.0));

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

package tech.aircastle.ecommerce.coupon;

import tech.aircastle.ecommerce.CartItem;
import tech.aircastle.ecommerce.product.Product;
import tech.aircastle.util.Util;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class CouponTest {

    static final double maxDelta = Util.MAX_DELTA;


    // FlatAmountOffCartAfterNthProductCoupon tests
    @Test
    public void testFlatAmountOffCartAfterNthProductCouponApplicable() {
        List<CartItem> basket = new ArrayList<>();
        Coupon coupon = new FlatAmountOffCartAfterNthProductCoupon(5, "Jacket", 2);

        assertEquals(false, coupon.isApplicable(basket));

        basket.add(new Product("Jacket", 100));

        assertEquals(false, coupon.isApplicable(basket));

        basket.add(new Product("Jacket", 100));

        assertEquals(true, coupon.isApplicable(basket));

        basket.add(new Product("Jacket", 100));

        assertEquals(true, coupon.isApplicable(basket));
    }

    @Test
    public void testFlatAmountOffCartAfterNthProductCouponApply() {

        List<CartItem> basket = new ArrayList<>();
        Coupon coupon = new FlatAmountOffCartAfterNthProductCoupon(5, "Jacket", 2);

        assertEquals(-5, coupon.apply(0, 0), maxDelta);
    }

    // PercentOffAllCartItemsCoupon tests
    @Test
    public void testPercentOffAllCartItemsCouponApplicable() {
        List<CartItem> basket = new ArrayList<>();
        Coupon coupon = new PercentOffAllCartItemsCoupon(10);

        assertEquals(true, coupon.isApplicable(basket));

        basket.add(new Product("Jacket", 100));
        assertEquals(true, coupon.isApplicable(basket));
    }

    @Test
    public void testPercentOffAllCartItemsCouponApply() {
        Coupon coupon = new PercentOffAllCartItemsCoupon(10);
        assertEquals(-10, coupon.apply(100, 0), maxDelta);

        assertEquals(-20, coupon.apply(200, 0), maxDelta);

        coupon = new PercentOffAllCartItemsCoupon(5);

        assertEquals(-5, coupon.apply(100, 0), maxDelta);

        assertEquals(-10, coupon.apply(200, 0), maxDelta);
    }

    // PercentOffNextProductCoupon
//    disabled -- PercentOffNextProductCoupon is always disabled because the Cart will decide when to apply
//    @Test
//    public void testPercentOffNextProductCouponApplicable() {
//
//        List<CartItem> basket = new ArrayList<>();
//        Coupon coupon = new PercentOffNextProductCoupon(10);
//
//        basket.add(new Product("Jacket", 100));
//
//        assertEquals(false, coupon.isApplicable(basket));
//
//        basket.add(new Product("Watch", 100));
//
//        assertEquals(false, coupon.isApplicable(basket));
//
//        basket.add(new Product("Bracelet", 100));
//
//        assertEquals(false, coupon.isApplicable(basket));
//    }

    @Test
    public void testPercentOffNextProductCouponApply() {
        List<CartItem> basket = new ArrayList<>();
        Coupon coupon = new PercentOffNextProductCoupon(10);

        assertEquals(-10, coupon.apply(0, 100), maxDelta);

        assertEquals(-20, coupon.apply(0, 200), maxDelta);

        coupon = new PercentOffNextProductCoupon(5);

        assertEquals(-5, coupon.apply(0, 100), maxDelta);

        assertEquals(-10, coupon.apply(0, 200), maxDelta);
    }
}

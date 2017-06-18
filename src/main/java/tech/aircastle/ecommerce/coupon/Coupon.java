package tech.aircastle.ecommerce.coupon;

import tech.aircastle.ecommerce.CartItem;

import java.util.List;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public abstract class Coupon extends CartItem {

    public abstract boolean isApplicable(List<CartItem> basket);

    public abstract double apply(double currentTotal, double currentItemPrice);
}

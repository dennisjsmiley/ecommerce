package tech.aircastle.ecommerce.coupon;

import tech.aircastle.ecommerce.CartItem;

import java.util.List;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class PercentOffNextProductCoupon extends NextProductCoupon {

    private final double percent;

    public PercentOffNextProductCoupon(double percent) {
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    @Override
    public boolean isApplicable(List<CartItem> basket) {
        return false; // Cart will decide when to apply this
    }

    @Override
    public double apply(double currentTotal, double currentItemPrice) {
        return - currentItemPrice * percent / 100;
    }

}

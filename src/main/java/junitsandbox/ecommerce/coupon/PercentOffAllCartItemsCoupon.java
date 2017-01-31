package junitsandbox.ecommerce.coupon;

import junitsandbox.ecommerce.CartItem;
import junitsandbox.util.Util;

import java.util.List;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class PercentOffAllCartItemsCoupon extends Coupon {

    private final double percent;

    public PercentOffAllCartItemsCoupon(double percent) {
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    @Override
    public boolean isApplicable(List<CartItem> basket) {
        return true;
    }

    @Override
    public double apply(double currentTotal, double currentItemPrice) {
        return - currentTotal * (percent / 100);
    }

}

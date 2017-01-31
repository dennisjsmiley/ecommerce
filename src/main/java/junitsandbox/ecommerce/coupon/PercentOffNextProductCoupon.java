package junitsandbox.ecommerce.coupon;

import junitsandbox.ecommerce.CartItem;
import junitsandbox.util.Util;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class PercentOffNextProductCoupon extends Coupon {

    double percent;

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

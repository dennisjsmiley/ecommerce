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
    List<CartItem> originalBasket = null;
    boolean applied = false;


    public PercentOffNextProductCoupon(double percent) {
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public List<CartItem> getOriginalBasket() {
        return originalBasket;
    }

    public boolean isApplied() {
        return applied;
    }

    @Override
    public boolean isApplicable(List<CartItem> basket) {

        if (applied) {
            return false;
        } else if (originalBasket == null) {
            originalBasket = basket;
            return false;
        } else {
            // all the items in the first basket are equal to the current basket, except the last product
            boolean allEqualExceptLast = false;
            if (allEqualExceptLast) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public double apply(double currentTotal, double currentItemPrice) {
        applied = true;
        return - currentItemPrice * percent / 100;
    }

    public boolean equals(Object other) {
        if (other instanceof PercentOffNextProductCoupon) {
            PercentOffNextProductCoupon coupon = (PercentOffNextProductCoupon) other;
            boolean isEqual = (Util.isEqual(percent, coupon.getPercent()) && applied == coupon.isApplied());

            isEqual &= originalBasket.size() == coupon.getOriginalBasket().size(); // todo: ensure each object within the list is equal

            return isEqual;
        } else {
            return false;
        }
    }
}

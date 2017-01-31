package junitsandbox.ecommerce;

import junitsandbox.ecommerce.coupon.Coupon;
import junitsandbox.ecommerce.coupon.PercentOffNextProductCoupon;
import junitsandbox.ecommerce.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class Cart {

    private final List<CartItem> basket = new ArrayList<>();

    double calculateTotal() {
        double total = 0;

        List<Coupon> unappliedCoupons = new ArrayList<>();
        List<Coupon> nextProductCoupons = new ArrayList<>();
        List<CartItem> curBasket = new ArrayList<>();

        int basketSize = basket.size();
        for (int i = 0; i < basketSize; i++) {

            CartItem item = basket.get(i);
            curBasket.add(item);

            if (item instanceof Product)
            {
                double currentItemPrice = ((Product) item).getPrice();

                List<Coupon> tmpUnappliedCoupons = new ArrayList<>();
                for (Coupon coupon : unappliedCoupons) {
                    if (coupon.isApplicable(curBasket)) {
                        currentItemPrice += coupon.apply(total, currentItemPrice);
                    } else {
                        tmpUnappliedCoupons.add(coupon);
                    }
                }

                if (!nextProductCoupons.isEmpty()) {
                    for (Coupon coupon : nextProductCoupons) {
                        currentItemPrice += coupon.apply(total, currentItemPrice);
                    }
                    nextProductCoupons = new ArrayList<>();
                }

                total += currentItemPrice;
            }
            else if (item instanceof Coupon)
            {
                Coupon coupon = (Coupon) item;
                if (coupon.isApplicable(curBasket)) {
                    total += coupon.apply(total, 0);
                } else {
                    if (coupon instanceof PercentOffNextProductCoupon) {
                        nextProductCoupons.add(coupon);
                    } else {
                        unappliedCoupons.add(coupon);
                    }
                }
            }
        }

        return total;
    }

    void addProduct(Product product) {
        basket.add(product);
    }

    void addCoupon(Coupon coupon) {
        basket.add(coupon);
    }

}

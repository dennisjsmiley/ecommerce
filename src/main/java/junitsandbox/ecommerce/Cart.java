package junitsandbox.ecommerce;

import junitsandbox.ecommerce.coupon.Coupon;
import junitsandbox.ecommerce.coupon.NextProductCoupon;
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

                List<Coupon> potentialCoupons = new ArrayList<>(unappliedCoupons);
                unappliedCoupons = new ArrayList<>();

                for (Coupon coupon : potentialCoupons)
                {
                    if (coupon.isApplicable(curBasket))
                    {
                        currentItemPrice += coupon.apply(total, currentItemPrice);
                    }
                    else
                    {
                        unappliedCoupons.add(coupon);
                    }
                }

                if (!nextProductCoupons.isEmpty())
                {
                    for (Coupon coupon : nextProductCoupons)
                    {
                        currentItemPrice += coupon.apply(total, currentItemPrice);
                    }
                    nextProductCoupons = new ArrayList<>();
                }

                total += currentItemPrice;
            }
            else if (item instanceof Coupon)
            {
                Coupon coupon = (Coupon) item;
                if (coupon instanceof NextProductCoupon)
                {
                    nextProductCoupons.add(coupon);
                }
                else if (coupon.isApplicable(curBasket))
                {
                        total += coupon.apply(total, 0);
                }
                else
                {
                        unappliedCoupons.add(coupon);
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

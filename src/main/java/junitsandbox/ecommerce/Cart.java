package junitsandbox.ecommerce;

import junitsandbox.ecommerce.coupon.Coupon;
import junitsandbox.ecommerce.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class Cart {

    List<CartItem> basket = new ArrayList<>();

    double calculateTotal() {
        double total = 0;

        int basketSize = basket.size();
        for (int i = 0; i < basketSize; i++) {
            CartItem item = basket.get(i);
            if (item instanceof Product) {
                total += ((Product)item).getPrice();
            } else if (item instanceof Coupon) {
                Coupon coupon = (Coupon) item;
                // todo: update total
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

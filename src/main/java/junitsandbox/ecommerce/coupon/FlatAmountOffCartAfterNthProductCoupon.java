package junitsandbox.ecommerce.coupon;

import junitsandbox.ecommerce.CartItem;
import junitsandbox.ecommerce.product.Product;
import junitsandbox.util.Util;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class FlatAmountOffCartAfterNthProductCoupon extends Coupon {

    double flatAmount;
    String productName;
    int N;


    public FlatAmountOffCartAfterNthProductCoupon(double flatAmount, String productName, int N) {
        this.flatAmount = flatAmount;
        this.productName = productName;
        this.N = N;
    }

    public double getFlatAmount() {
        return flatAmount;
    }

    public String getProductName() {
        return productName;
    }

    public int getN() {
        return N;
    }

    @Override
    public boolean isApplicable(List<CartItem> basket) {
        int productCount = 0;

        for (CartItem cartItem : basket) {
            if (cartItem instanceof Product) {
                Product product = (Product) cartItem;
                if (productName.equals(product.getName())) {
                    productCount ++;
                }
            }
        }

        return productCount >= N;
    }

    @Override
    public double apply(double currentTotal, double currentItemPrice) {
        return -flatAmount;
    }

    public boolean equals(Object other) {
        if (other instanceof FlatAmountOffCartAfterNthProductCoupon) {
            FlatAmountOffCartAfterNthProductCoupon coupon = (FlatAmountOffCartAfterNthProductCoupon) other;
            return (
                    Util.isEqual(flatAmount, coupon.getFlatAmount()) &&
                    productName.equals(coupon.getProductName()) &&
                    N == coupon.getN()
            );
        } else {
            return false;
        }
    }
}

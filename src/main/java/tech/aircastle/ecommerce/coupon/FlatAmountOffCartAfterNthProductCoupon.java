package tech.aircastle.ecommerce.coupon;

import tech.aircastle.ecommerce.CartItem;
import tech.aircastle.ecommerce.product.Product;

import java.util.List;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class FlatAmountOffCartAfterNthProductCoupon extends Coupon {

    private final double flatAmount;
    private final String productName;
    private final int N;


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

}

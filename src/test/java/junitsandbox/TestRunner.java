package junitsandbox;

import junitsandbox.ecommerce.CartTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by Dennis Smiley on 1/30/17.
 */
public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CartTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}

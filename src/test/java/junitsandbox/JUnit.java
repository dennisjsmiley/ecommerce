package junitsandbox;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Dennis Smiley on 1/30/17.
 */
public class JUnit {

    static int value = 0;

    @Test
    public void testCase() {
        System.out.println("1. value: "+ value);
        value = 5;
        assertEquals("a", "ab");
//        assertNotEquals("a", "ab");

    }

    @Test
    public void testCase2() {
        System.out.println("2. value: " + value);
        assertEquals(true, true);
    }
}

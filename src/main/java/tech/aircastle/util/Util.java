package tech.aircastle.util;

/**
 * Created by Dennis Smiley on 1/31/17.
 */
public class Util {
    public static final double MAX_DELTA = 0.0001;

    public static boolean isEqual(double d1, double d2) {
        double delta;
        if (d1 >= d2) {
            delta = d1 - d2;
        } else {
            delta = d2 - d1;
        }

        return delta <= Util.MAX_DELTA;
    }
}

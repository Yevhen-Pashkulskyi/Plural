package util;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class Utility {
    public static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    public static double randomNumber(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static double rounderDouble(double rounder) {
        BigDecimal bd = new BigDecimal(rounder);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return Double.parseDouble(bd.toString());
    }

}

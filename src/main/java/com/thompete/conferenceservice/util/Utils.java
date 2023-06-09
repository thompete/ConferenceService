package com.thompete.conferenceservice.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    public static double round(double number, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(number);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

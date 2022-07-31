package com.argentinaprograma.portfolio.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
    public static <T> List<T> getListOf(T... items){
        return Arrays.asList(items);
    }
}
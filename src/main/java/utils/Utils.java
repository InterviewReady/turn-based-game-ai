package utils;

import placements.DefensivePlacement;
import placements.Placement;

import java.util.function.Supplier;

public class Utils {

    public static Object getIfNull(Object o, Supplier<Object> supplier) {
        if (o == null) {
            return supplier.get();
        }
        return o;
    }
}

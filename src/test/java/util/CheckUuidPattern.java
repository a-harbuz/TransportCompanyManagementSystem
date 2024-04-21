package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckUuidPattern {
    public static String getUuidPattern() {
        return "^([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}$";
    }
}

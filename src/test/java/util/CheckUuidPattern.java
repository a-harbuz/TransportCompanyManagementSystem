package util;

public class CheckUuidPattern {
    public static String getUuidPattern() {
        return "^([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}$";
    }
}

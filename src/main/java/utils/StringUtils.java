package utils;

public class StringUtils {
    public static boolean isStringEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static String replaceWithStars(String value) {
        return value.replaceAll("[^\n]", "*");
    }
}

package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EqualUtils {

    public static final Logger LOGGER = LogManager.getLogger(EqualUtils.class);

    public static boolean equalFields(String name, String actualValue, String expectedValue) {
        boolean isActualEmpty = StringUtils.isStringEmpty(actualValue);
        actualValue = actualValue.trim();
        expectedValue = expectedValue.trim();
        if (isActualEmpty == StringUtils.isStringEmpty(expectedValue) && (isActualEmpty || actualValue.equalsIgnoreCase(expectedValue))) {
            LOGGER.info("EQUAL PARAMETER: " + name + " actual: '" + actualValue + "' = expected: '" + expectedValue + "'");
            return true;
        } else {
            LOGGER.warn("DIFFERENT PARAMETER: " + name + " actual: '" + actualValue + "' != expected: '" + expectedValue + "'");
            return false;
        }
    }

    public static Boolean equalBooleanFields(String name, Boolean b1, Boolean b2) {
        if (b1 == b2) {
            LOGGER.info("EQUAL PARAMETER - " + name + " '" + (b1 == null ? "null" : b1) + "' = '"
                    + (b2 == null ? "null" : b2) + "'");
            return true;
        } else {
            LOGGER.warn("DIFFERENT PARAMETER - " + name + " actual: '" + (b1 == null ? "null" : b1) + "' != expected: '"
                    + (b2 == null ? "null" : b2) + "'");
            return false;
        }
    }

    public static Boolean equalTrue(String name, Boolean b1) {
        return equalBooleanFields(name, b1, true);
    }

    public static Boolean equalFalse(String name, Boolean b1) {
        return equalBooleanFields(name, b1, false);
    }
}

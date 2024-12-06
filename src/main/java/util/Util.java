package util;

public class Util {
    public static boolean isCurrencyValid(String code, String name, String sign) {
        if (code == null || name == null || sign == null) {
            return false;
        }
        if (code.isEmpty() || name.isEmpty() || sign.isEmpty()) {
            return false;
        }
        if (code.length() != 3 || name.length() > 54 || sign.length() != 1){
            return false;
        }
        return true;
    }
}

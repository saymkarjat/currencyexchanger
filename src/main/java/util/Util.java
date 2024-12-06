package util;

public class Util {
    public static boolean isCurrencyValid(String code, String name, String sign) {
        if (code == null || name == null || sign == null) {
            return false;
        }
        if (code.isEmpty() || name.isEmpty() || sign.isEmpty()) {
            return false;
        }
        if (code.length() != 3 || name.length() > 54 || sign.length() > 3){
            return false;
        }
        return true;
    }
    public static boolean isCodeValid(String code){
        if (code.length() != 4){
            return false;
        }
        String currentCode = code.substring(1);
        for (char c : currentCode.toCharArray()) {
            if (!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }
    public static String getFormattedCode(String code){
        String substring = code.substring(1);
        return substring.toUpperCase();
    }
}

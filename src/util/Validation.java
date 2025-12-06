package util;

public class Validation {

    public static boolean isEmpty(String text){
        return text == null || text.trim().isEmpty();
    }

    public static boolean isValidPhone(String phone){
        return phone.matches("\\d{11}");
    }
}

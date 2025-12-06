package util;

public class Utils {

    public static void printLine(){
        System.out.println("------------------------------------");
    }

    public static String generateOrderNumber(){
        return "ORD-" + (int)(Math.random() * 100000);
    }
}

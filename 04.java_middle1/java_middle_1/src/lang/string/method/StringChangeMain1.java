package lang.string.method;

public class StringChangeMain1 {
    public static void main(String[] args) {

        String str = "Hello, Java! Welcome to Java";

        System.out.println(str.substring(7));
        System.out.println(str.substring(7, 11));
        System.out.println(str.concat("!!!") );
        System.out.println(str.replace("Java", "Phyton"));
        System.out.println(str.replaceFirst("Java", "Phyton"));
        System.out.println(str.replaceAll("Java", "Phyton"));
    }
}

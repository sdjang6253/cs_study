package lang.string.method;

public class StringInfoMain {

    public static void main(String[] args) {
        String str = "hello java";
        System.out.println("length = " + str.length());
        System.out.println("toUpperCase = " + str.toUpperCase());
        System.out.println("toLowerCase = " + str.toLowerCase());
        System.out.println("charAt = " + str.charAt(6));
        System.out.println("substring = " + str.substring(6));
        System.out.println("substring = " + str.substring(0, 5));
        System.out.println("replace = " + str.replace("java", "world"));
        System.out.println("contains = " + str.contains("java"));
        System.out.println("indexOf = " + str.indexOf("java"));
        System.out.println("indexOf = " + str.indexOf("java2"));
        System.out.println("isBlank = " + str.isBlank());
        System.out.println("isEmpty = " + str.isEmpty());
        System.out.println("trim = " + "   hello   ".trim());
        System.out.println("trim = " + "   hello   ".strip());
        System.out.println("trim = " + "   hello   ".stripLeading());
    }
}

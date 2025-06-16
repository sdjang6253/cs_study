package lang.string.immutable;

public class StringImmutable2 {
    public static void main(String[] args) {
        String str = "hello";
        System.out.println("str = " + str);
        String str2 = str.concat(" world");
        System.out.println("str = " + str2);
    }
}

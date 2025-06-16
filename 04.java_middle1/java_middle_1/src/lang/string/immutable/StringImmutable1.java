package lang.string.immutable;

public class StringImmutable1 {
    public static void main(String[] args) {
        String str = "hello";
        System.out.println("str = " + str);
        str.concat(" world");
        System.out.println("str = " + str);
    }
}

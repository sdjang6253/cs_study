package lang.string.test;

public class TestString2 {

    public static void main(String[] args) {
        String[] arr = {"hello", "java", "jvm", "spring", "jpa"};
        int count = 0;
        for (String s : arr) {
            System.out.println(s +  ":" + s.length());
            count += s.length();
        }
        System.out.println("sum = " + count);
    }
}

package lang.string;

public class StringConcatMain {

    public static void main(String[] args) {
        String a  = "hi";
        String b = "대구";

        String result1 = a.concat(b);
        String result2 = a + b;

        System.out.println(result1 + " _ " +  result2);
    }
}

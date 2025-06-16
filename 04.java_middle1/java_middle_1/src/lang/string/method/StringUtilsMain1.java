package lang.string.method;

public class StringUtilsMain1 {

    public static void main(String[] args) {
        int num = 100;
        boolean bool = true;
        Object obj = new Object();
        String str = "자바";

        String numString = String.valueOf(num);
        System.out.println("숫자의 문자열 " + numString);
        String boolString = String.valueOf(bool);
        System.out.println("boolean의 문자열 " + boolString);
        String objString = String.valueOf(obj);
        System.out.println("객체의 문자열 " + objString);

        String numString2 = "" + num;
        System.out.println(numString2);

    }
}

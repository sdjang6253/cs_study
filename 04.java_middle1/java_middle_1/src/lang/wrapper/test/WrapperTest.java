package lang.wrapper.test;

public class WrapperTest {

    public static void main(String[] args) {
            test1();
            test3();
            test4();
    }

    public static void test1(){
        String str1 = "10";
        String str2 = "20";

        int num1 = Integer.parseInt(str1);
        int num2 = Integer.parseInt(str2);
        int sum = num1 + num2;
        System.out.println("두 수의 합: " + sum);
    }

    public static void test3(){
        String str = "100";
        //String -> Integer
        Integer integer1 = Integer.valueOf(str);
        System.out.println("integer1 = " + integer1);
        //Integer -> int
        int intValue = integer1.intValue();
        System.out.println("intValue = " + intValue);

        Integer integer2 = Integer.valueOf(intValue);
        System.out.println("integer2 = " + integer2);
    }

    public static void test4(){
        String str = "100";
        //String -> Integer
        Integer integer1 = Integer.valueOf(str);
        System.out.println("integer1 = " + integer1);
        //Integer -> int
        int intValue = integer1;
        System.out.println("intValue = " + intValue);
        //int -> Integer
        Integer integer2 = intValue;
        System.out.println("integer2 = " + integer2);
    }
}

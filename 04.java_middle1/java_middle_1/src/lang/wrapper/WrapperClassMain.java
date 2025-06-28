package lang.wrapper;

public class WrapperClassMain {
    public static void main(String[] args) {
        Integer integer = new Integer(10);
        Integer integerObj = Integer.valueOf(10);
        Integer integerObj2 = Integer.valueOf(10);

        Long longObj = Long.valueOf(1000L);
        Double doubleObj = Double.valueOf(10.123);

        System.out.println(integer);
        System.out.println(integerObj);
        System.out.println(longObj);
        System.out.println(doubleObj);

        int intValue = integerObj.intValue();
        System.out.println(intValue);

        System.out.println(integer == integerObj);
        System.out.println(integer.equals(integerObj));
        System.out.println(integerObj == integerObj2); // caching 함

    }
}

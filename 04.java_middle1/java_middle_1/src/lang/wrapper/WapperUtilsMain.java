package lang.wrapper;

public class WapperUtilsMain {
    public static void main(String[] args) {

        Integer i1 = Integer.valueOf(10);
        Integer i2 = Integer.valueOf("10");
        int intValue = Integer.parseInt("10");

        int compareResult = i1.compareTo(20);
        System.out.println("compareResult =" + compareResult );


        System.out.println(Integer.sum(i1 , i2));
    }
}

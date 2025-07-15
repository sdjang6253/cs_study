package generic.ex1;

public class BoxMain2 {
    public static void main(String[] args) {
        ObjectBox intBox = new ObjectBox();
        intBox.set(10);

        int intValue = (int) intBox.get();
        System.out.println("intValue = " + intValue);

        ObjectBox strBox = new ObjectBox();
        strBox.set("hello");

        String strValue = (String) strBox.get();
        System.out.println("strValue = " + strValue);
    }
}

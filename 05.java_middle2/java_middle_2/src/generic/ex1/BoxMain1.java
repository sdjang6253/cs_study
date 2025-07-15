package generic.ex1;

public class BoxMain1 {

    public static void main(String[] args) {
        IntegerBox integerBox = new IntegerBox();
        integerBox.set(10);
        Integer intValue = integerBox.get();
        System.out.println("intValue = " + intValue);

        StringBox stringBox = new StringBox();
        stringBox.set("hello");
        String strValue = stringBox.get();
        System.out.println("strValue = " + strValue);
    }
}

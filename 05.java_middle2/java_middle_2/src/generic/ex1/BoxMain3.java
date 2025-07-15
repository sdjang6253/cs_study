package generic.ex1;

public class BoxMain3 {
    public static void main(String[] args) {
        GenericBox<Integer> integerBox = new GenericBox<>();
        integerBox.set(10);
        Integer i = integerBox.get();
        System.out.println("i = " + i);


        GenericBox<String> stringBox = new GenericBox<>();
        stringBox.set("10");
        String str = stringBox.get();
        System.out.println("str = " + str);

        GenericBox<Double> doubleBox = new GenericBox<>();
        doubleBox.set(10.55);
        Double doubleValue = doubleBox.get();
        System.out.println("doubleValue = " + doubleValue);
    }
}

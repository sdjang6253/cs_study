package lang.string.chaining;

public class MethodChainingMain2 {

    public static void main(String[] args) {
        ValueAdder adder = new ValueAdder();
        ValueAdder add = adder.add(1);
        ValueAdder add1 = add.add(2);
        ValueAdder add2 = add1.add(3);
        ValueAdder add3 = add2.add(4);

        int value = add3.getValue();
        System.out.println("value = " + value);
    }
}

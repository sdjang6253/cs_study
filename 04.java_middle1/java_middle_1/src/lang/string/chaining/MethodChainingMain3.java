package lang.string.chaining;

public class MethodChainingMain3 {

    public static void main(String[] args) {
        ValueAdder adder = new ValueAdder();

        int value = adder.add(1).add(2).add(3).add(4).getValue();
        System.out.println("value = " + value);
    }
}

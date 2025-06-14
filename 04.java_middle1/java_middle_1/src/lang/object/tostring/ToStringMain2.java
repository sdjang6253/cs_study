package lang.object.tostring;

public class ToStringMain2 {

    public static void main(String[] args) {
        Car car = new Car("Model Y");
        Dog dog1 = new Dog("Dog1", 2);
        Dog dog2 = new Dog("Dog2", 5);

        System.out.println(car);
        System.out.println(dog1);
        System.out.println(dog2);

        ObjectPrinter.print(car);
        ObjectPrinter.print(dog1);
        ObjectPrinter.print(dog2);

        String hexString = Integer.toHexString(System.identityHashCode(car));
        System.out.println("hexString = " + hexString);
        String hexString2 = Integer.toHexString(System.identityHashCode(dog1));
        System.out.println("hexString2 = " + hexString2);
        String hexString3 = Integer.toHexString(System.identityHashCode(dog2));
        System.out.println("hexString3 = " + hexString3);
    }

}

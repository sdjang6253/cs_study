package lang.immutable.address;

public class RefMain1_3 {

    public static void main(String[] args) {
        Address a = new Address("서울");
        Address b = a;

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        changeValue(b ,"대구");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
    private static void changeValue(Address address , String chgAddress) {
        address.setValue(chgAddress);
    }
}

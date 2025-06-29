package enumeration.ref3;


public class EnumRefMain3_2 {
    public static void main(String[] args) {
        int price = 10000;

        int basic = Grade.BASIC.discount(price);
        int gold = Grade.BASIC.discount(price);
        int diamond = Grade.DIAMOND.discount(price);

        System.out.println("BASIC = " + basic);
        System.out.println("GOLD = " + gold);
        System.out.println("DIAMOND = " + diamond);
    }
}

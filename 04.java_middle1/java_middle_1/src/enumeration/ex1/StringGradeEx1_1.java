package enumeration.ex1;

import enumeration.ex1.DiscountService;

public class StringGradeEx1_1 {

    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();
        int basic = discountService.discount(StringGrade.BASIC , price);
        int gold = discountService.discount(StringGrade.GOLD, price);
        int diamond = discountService.discount(StringGrade.DIAMOND, price);

        System.out.println("BASIC = " + basic);
        System.out.println("GOLD = " + gold);
        System.out.println("DIAMOND = " + diamond);
    }
}

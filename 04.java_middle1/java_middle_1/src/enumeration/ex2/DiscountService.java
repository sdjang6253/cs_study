package enumeration.ex2;

public class DiscountService {
    public int discount(ClassGrade classGrade , int price){
        int discountPercent = 0;

       if(classGrade == ClassGrade.BASIC){
            discountPercent = 10;
        } else if(classGrade == ClassGrade.GOLD){
            discountPercent = 20;
        } else if(classGrade == ClassGrade.DIAMOND){
            discountPercent = 30;
        } else {
            System.out.println(classGrade + ": 할인 적용 안됨");
        }

        return price * discountPercent / 100;
    }
}

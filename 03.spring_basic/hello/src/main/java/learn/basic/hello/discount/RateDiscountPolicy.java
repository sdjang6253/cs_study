package learn.basic.hello.discount;

import learn.basic.hello.annotation.MainDiscountPolicy;
import learn.basic.hello.member.Grade;
import learn.basic.hello.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if( member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}

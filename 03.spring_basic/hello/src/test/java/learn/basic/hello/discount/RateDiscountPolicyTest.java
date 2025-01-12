package learn.basic.hello.discount;

import learn.basic.hello.member.Grade;
import learn.basic.hello.member.Member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 는 10% 할인이 적용 되어야 한다.")
    void vip_o(){
        //given
        Member member = new Member(1L , "memberVIP" , Grade.VIP);

        //when
        int discount = discountPolicy.discount(member,10000);

        //then
        assertThat(discount).isEqualTo( 1000);
    }

    @Test
    @DisplayName("VIP 가 아닌경우 10% 할인이 적용 되지 않아야 한다.")
    void vip_x(){
        //given
        Member member = new Member(2L , "memberBASIC" , Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member,10000);

        //then
        assertThat(discount).isEqualTo( 0);
    }
}
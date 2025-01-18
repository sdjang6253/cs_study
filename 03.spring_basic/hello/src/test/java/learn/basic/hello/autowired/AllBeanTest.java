package learn.basic.hello.autowired;

import learn.basic.hello.AutoAppConfig;
import learn.basic.hello.discount.DiscountPolicy;
import learn.basic.hello.member.Grade;
import learn.basic.hello.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

class AllBeanTest {

    @Test
    void typeAllBean(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class , DiscountService.class);

        DiscountService discountService = context.getBean(DiscountService.class);
        Member member = new Member(1L , "userA" , Grade.VIP);
        int discountPrice = discountService.discount(member , 10000 , "fixDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        int discountRatePrice = discountService.discount(member , 20000 , "rateDiscountPolicy");
        Assertions.assertThat(discountRatePrice).isEqualTo(2000);


    }

    static class DiscountService {
        private final Map<String , DiscountPolicy> policyMap;
        private final List<DiscountPolicy> polices;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> polices) {
            this.policyMap = policyMap;
            this.polices = polices;
            System.out.println("policyMap = " + policyMap);
            System.out.println("polices = " + polices);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}

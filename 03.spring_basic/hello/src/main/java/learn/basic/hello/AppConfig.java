package learn.basic.hello;

import learn.basic.hello.discount.DiscountPolicy;
import learn.basic.hello.discount.FixDiscountPolicy;
import learn.basic.hello.member.MemberService;
import learn.basic.hello.member.MemberServiceImpl;
import learn.basic.hello.member.MemoryMemberRepository;
import learn.basic.hello.order.OrderService;
import learn.basic.hello.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        return new FixDiscountPolicy();
    }
}


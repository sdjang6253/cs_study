package learn.basic.hello;

import learn.basic.hello.discount.DiscountPolicy;
import learn.basic.hello.discount.FixDiscountPolicy;
import learn.basic.hello.member.MemberService;
import learn.basic.hello.member.MemberServiceImpl;
import learn.basic.hello.member.MemoryMemberRepository;
import learn.basic.hello.order.OrderService;
import learn.basic.hello.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}


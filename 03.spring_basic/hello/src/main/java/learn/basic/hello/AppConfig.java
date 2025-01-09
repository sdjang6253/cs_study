package learn.basic.hello;

import learn.basic.hello.discount.FixDiscountPolicy;
import learn.basic.hello.member.MemberService;
import learn.basic.hello.member.MemberServiceImpl;
import learn.basic.hello.member.MemoryMemberRepository;
import learn.basic.hello.order.OrderService;
import learn.basic.hello.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository() , new FixDiscountPolicy());
    }
}


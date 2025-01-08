package learn.basic.hello;

import learn.basic.hello.member.Grade;
import learn.basic.hello.member.Member;
import learn.basic.hello.member.MemberService;
import learn.basic.hello.member.MemberServiceImpl;
import learn.basic.hello.order.Order;
import learn.basic.hello.order.OrderService;
import learn.basic.hello.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId , "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId , "itemA" , 10000);
        System.out.println("order = " + order);

    }
}

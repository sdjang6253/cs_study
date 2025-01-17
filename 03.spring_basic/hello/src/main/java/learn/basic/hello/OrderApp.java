package learn.basic.hello;

import learn.basic.hello.member.Grade;
import learn.basic.hello.member.Member;
import learn.basic.hello.member.MemberService;
import learn.basic.hello.member.MemberServiceImpl;
import learn.basic.hello.order.Order;
import learn.basic.hello.order.OrderService;
import learn.basic.hello.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId , "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId , "itemA" , 10000);
        System.out.println("order = " + order);

    }
}

package learn.basic.hello.order;

import learn.basic.hello.AppConfig;
import learn.basic.hello.member.Grade;
import learn.basic.hello.member.Member;
import learn.basic.hello.member.MemberService;
import learn.basic.hello.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        //given
        Long memberId = 1L;
        Member member = new Member(memberId , "memberA", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId , "itemA" , 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}

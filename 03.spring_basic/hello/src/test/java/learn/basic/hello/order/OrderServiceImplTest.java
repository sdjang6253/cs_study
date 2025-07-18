package learn.basic.hello.order;

import learn.basic.hello.discount.FixDiscountPolicy;
import learn.basic.hello.member.Grade;
import learn.basic.hello.member.Member;
import learn.basic.hello.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L , "name" , Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository , new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
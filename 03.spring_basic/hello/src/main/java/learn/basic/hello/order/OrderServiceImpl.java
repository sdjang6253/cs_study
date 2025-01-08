package learn.basic.hello.order;

import learn.basic.hello.discount.DiscountPolicy;
import learn.basic.hello.discount.FixDiscountPolicy;
import learn.basic.hello.member.Member;
import learn.basic.hello.member.MemberRepository;
import learn.basic.hello.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member , itemPrice);

        return new Order (memberId , itemName , itemPrice , discountPrice);
    }
}

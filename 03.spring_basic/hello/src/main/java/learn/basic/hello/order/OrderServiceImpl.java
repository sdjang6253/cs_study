package learn.basic.hello.order;

import learn.basic.hello.discount.DiscountPolicy;
import learn.basic.hello.discount.FixDiscountPolicy;
import learn.basic.hello.discount.RateDiscountPolicy;
import learn.basic.hello.member.Member;
import learn.basic.hello.member.MemberRepository;
import learn.basic.hello.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member , itemPrice);

        return new Order (memberId , itemName , itemPrice , discountPrice);
    }
}

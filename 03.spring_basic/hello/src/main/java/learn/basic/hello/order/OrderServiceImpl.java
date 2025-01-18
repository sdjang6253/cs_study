package learn.basic.hello.order;

import learn.basic.hello.annotation.MainDiscountPolicy;
import learn.basic.hello.discount.DiscountPolicy;
import learn.basic.hello.member.Member;
import learn.basic.hello.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final 붙은 아이들의 생성자를 만들어 준다.
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member , itemPrice);

        return new Order (memberId , itemName , itemPrice , discountPrice);
    }

    //for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

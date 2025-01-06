package learn.basic.hello;

import learn.basic.hello.member.Grade;
import learn.basic.hello.member.Member;
import learn.basic.hello.member.MemberService;
import learn.basic.hello.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
        System.out.println("find member = " + (findMember == member));

    }
}

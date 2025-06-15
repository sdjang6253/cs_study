package lang.immutable.address;

public class MemberMainV2 {

    public static void main(String[] args) {
        ImmutableAddress address = new ImmutableAddress("서울");

        MemberV2 memberA = new MemberV2("홍길동", address);
        MemberV2 memberB = new MemberV2("홍길동", address);

        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);

        ImmutableAddress addressB = new ImmutableAddress("대구");
        memberB.setImmutableAddress(addressB);

        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);

    }
}

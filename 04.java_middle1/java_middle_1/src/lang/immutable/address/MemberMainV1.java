package lang.immutable.address;

public class MemberMainV1 {

    public static void main(String[] args) {
        Address address = new Address("서울");

        MemberV1 memberA = new MemberV1("홍길동", address);
        MemberV1 memberB = new MemberV1("홍길동", address);

        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);

        Address addressB = memberB.getAddress();
        addressB.setValue("대구");

        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);

    }
}

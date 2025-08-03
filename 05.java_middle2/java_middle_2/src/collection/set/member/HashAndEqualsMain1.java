package collection.set.member;

import collection.set.MyHashSetV2;

public class HashAndEqualsMain1 {
    public static void main(String[] args) {
        //중복 등록 됨
        MyHashSetV2 set = new  MyHashSetV2(10);
        MemberNoHashEq m1 = new  MemberNoHashEq("A");
        MemberNoHashEq m2 = new  MemberNoHashEq("A");
        System.out.println("m1.hashCode() = " + m1.hashCode());
        System.out.println("m2.hashCode() = " + m2.hashCode());
        System.out.println("m1.equals(m2): " + m1.equals(m2));

        set.add(m1);
        set.add(m2);
        System.out.println(set);

        MemberNoHashEq searchValue = new MemberNoHashEq("A");
        System.out.println("searchValue.hashCode() = " + searchValue.hashCode());
        boolean contains = set.contains(searchValue);
        System.out.println("contains = " + contains);
        System.out.println("searchValue.equals(m1): " + searchValue.equals(m1));
    }
}

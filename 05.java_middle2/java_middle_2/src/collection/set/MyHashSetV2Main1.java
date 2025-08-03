package collection.set;

public class MyHashSetV2Main1 {
    public static void main(String[] args) {
        MyHashSetV2 set =  new MyHashSetV2();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");
        set.add("ab");
        set.add("set");
        System.out.println(set);

        System.out.println("A.hashCode() = " + "A".hashCode());
        System.out.println("B.hashCode() = " + "B".hashCode());
        System.out.println("AB.hashCode() = " + "AB".hashCode());
        System.out.println("SET.hashCode() = " + "SET".hashCode());
        System.out.println("E.hashCode() = " + "E".hashCode());

        String searchValue = "SET";
        boolean result = set.contains(searchValue);
        System.out.println("result = " + result); 
    }
}

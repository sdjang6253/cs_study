package collection.array;

public class MyArrayListV3Main {
    public static void main(String[] args) {
        MyArrayListV3 list = new MyArrayListV3();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("list = " + list);

        System.out.println("addList");
        list.add(3 , "addList"); // O(1)
        System.out.println("list = " + list);

        System.out.println("addFirstList");
        list.add(0, "addFirstList"); // O(n)
        System.out.println("list = " + list);

        Object removed1 = list.remove(4); //O(1)
        System.out.println("removed1 = " + removed1);
        System.out.println(list);

        Object removed2 = list.remove(0);
        System.out.println("removed2 = " + removed2); // O(n)
        System.out.println(list);
    }
}

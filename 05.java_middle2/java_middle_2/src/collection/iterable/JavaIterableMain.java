package collection.iterable;

import java.util.*;

public class JavaIterableMain {
    public static void main(String[] args) {

        List<Integer> myList = new ArrayList<Integer>();
        myList.add(1);
        myList.add(2);
        myList.add(3);

        printAll(myList.iterator());
        foreach(myList);

        Set<Integer> mySet = new HashSet<Integer>();
        mySet.add(1);
        mySet.add(2);
        mySet.add(3);

        printAll(mySet.iterator());
        foreach(mySet);

    }

    private static void foreach(Iterable<Integer> iterable) {
        System.out.println("iterable = " + iterable.getClass());
        for(Integer value : iterable){
            System.out.println("value = " + value);
        }
    }

    private static void printAll(Iterator<Integer> iterator){
        System.out.println("iterator = " + iterator.getClass());
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

package collection.set.test;

import java.util.*;

public class UniqueNamesTest3 {
    public static void main(String[] args) {
        Set<Object> hashSet = new TreeSet<>(List.of(30, 20, 20, 10, 10));

        Iterator<Object> iterator = hashSet.iterator();
        while ( iterator.hasNext() ) {
            System.out.println(iterator.next());
        }
    }
}

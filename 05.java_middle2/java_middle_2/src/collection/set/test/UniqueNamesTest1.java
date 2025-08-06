package collection.set.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UniqueNamesTest1 {
    public static void main(String[] args) {
        Integer[] inputArr = {30, 20, 20, 10, 10};
        Set<Object> hashSet = new HashSet<>();

        for (Integer i : inputArr) {
            hashSet.add(i);
        }

        Iterator<Object> iterator = hashSet.iterator();
        while ( iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

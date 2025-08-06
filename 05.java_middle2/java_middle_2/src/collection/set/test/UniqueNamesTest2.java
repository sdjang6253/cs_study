package collection.set.test;

import java.util.*;

public class UniqueNamesTest2 {
    public static void main(String[] args) {
        Integer[] inputArr = {30, 20, 20, 10, 10};
        Set<Object> hashSet = new LinkedHashSet<>(List.of(inputArr));

        Iterator<Object> iterator = hashSet.iterator();
        while ( iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

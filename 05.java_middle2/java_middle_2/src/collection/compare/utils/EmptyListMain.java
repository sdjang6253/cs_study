package collection.compare.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EmptyListMain {
    public static void main(String[] args) {
        //빈 가변 리스트 생성
        List<Integer> list1  = new ArrayList<>();
        LinkedList<Object> list2 = new LinkedList<>();

        List<Integer> list3 = Collections.emptyList(); //JAVA 5
        List<Integer> list4 = List.of(); // JAVA 1.9

        System.out.println("list3.getClass() = " + list3.getClass());
        System.out.println("list4.getClass() = " + list4.getClass());
    }
}

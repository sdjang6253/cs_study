package collection.compare.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImutableMain {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3);

        
        //가변 리스트 만들기
        ArrayList<Integer> mutableList  = new ArrayList<>(list);
        mutableList.add(4);
        System.out.println("mutableList = " + mutableList);
        System.out.println("mutableList.getClass() = " + mutableList.getClass());

        //불변 리스트 만들기
        List<Integer> unmodifiableList = Collections.unmodifiableList(list);
        System.out.println("unmodifiableList class = " + unmodifiableList.getClass());

    }
}

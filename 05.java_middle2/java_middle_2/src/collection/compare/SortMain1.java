package collection.compare;

import java.util.Arrays;

public class SortMain1 {
    public static void main(String[] args) {
        Integer[] myArray = {3,2,1};
        System.out.println(Arrays.toString(myArray));

        System.out.println("기본 정렬 후");
        Arrays.sort(myArray);
        System.out.println(Arrays.toString(myArray));
    }

}

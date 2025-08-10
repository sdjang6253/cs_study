
package collection.compare;

import java.util.Arrays;
import java.util.Comparator;

public class SortMain2 {
    public static void main(String[] args) {
        Integer[] myArray = {3,2,1};
        System.out.println(Arrays.toString(myArray));
        System.out.println("compare 비교");
        Arrays.sort(myArray , new AscComparator());
        System.out.println("AscCompartor : " + Arrays.toString(myArray));
        Arrays.sort(myArray , new DescComparator());
        System.out.println("DescCompartor : " + Arrays.toString(myArray));

        Arrays.sort(myArray , new AscComparator().reversed());
        System.out.println("AscCompartor.reversed() : " + Arrays.toString(myArray));
    }

    static class AscComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            System.out.println("o1= " + o1 + " o2= " + o2);
            return (o1 < o2) ?  -1 : (o1 == o2) ? 0 : 1;
        }
    }

    static class DescComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            System.out.println("o1= " + o1 + " o2= " + o2);
            return (o1 < o2) ? 1 : (o1 == o2) ? 0 : -1;
        }
    }

}

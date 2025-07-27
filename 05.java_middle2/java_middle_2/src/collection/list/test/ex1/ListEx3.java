package collection.list.test.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListEx3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        int total=0;
        System.out.println("n 개의 정수를 입력하세요 (0 은 종료)");
        while (true) {
            int n = sc.nextInt();

            if( n == 0 ){
                break;
            }
            list.add(n);
        }

        for (int i = 0; i < list.size(); i++) {
            total += list.get(i);
        }
        double average = (double) total / list.size();
        System.out.println("입력한 정수 총합: " + total);
        System.out.println("입력한 정수 평균: " + average);


    }
}

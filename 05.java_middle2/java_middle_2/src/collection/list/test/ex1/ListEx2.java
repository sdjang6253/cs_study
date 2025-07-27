package collection.list.test.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListEx2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        System.out.println("n 개의 정수를 입력하세요 (0 은 종료)");
        while (true) {
            int n = sc.nextInt();

            if( n == 0 ){
                break;
            }
            list.add(n);
        }

        for (int i = 0; i < list.size(); i++) {
            if(i != 0 ){
                System.out.print("," + list.get(i));
            }else{
                System.out.print(list.get(i));
            }

        }



    }
}

package collection.map.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Map<String, String> map = new HashMap<String, String>();

        while(true){
            System.out.print("영어 단어를 입력하세요 (종료는 'q'): ");
            String word = sc.nextLine();

            if(word.equals("q")){
                break;
            }
            System.out.print("한글 뜻을 입력하세요: ");
            String mean = sc.nextLine();
            map.put(word, mean);
        }

        while(true){
            System.out.print("찾을 영어 단어를 입력하세요 (종료는 'q'): ");
            String search = sc.nextLine();

            if(search.equals("q")){
                return;
            }
            if(map.containsKey(search)){
                System.out.println(search +" 의 뜻 : " + map.get(search));
            } else {
                System.out.println(search +" 은(는) 사전에 없는 단어 입니다. ");
            }
        }

    }
}

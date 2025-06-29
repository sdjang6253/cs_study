package nested.annonymous.ex;

import java.util.Random;

public class Ex1Main {

    public static void main(String[] args) {
        helloDice();
        helloSum();
    }
    public static void helloDice(){
        System.out.println("프로그램 시작");

        int randomValue = new Random().nextInt(6) +1;
        System.out.println("randomValue = " + randomValue);

        System.out.println("프로그램 종료");
    }

    public static void helloSum(){
        System.out.println("프로그램 시작");

        for (int i = 0; i < 3; i++) {
            System.out.println("i = " + i);

        }
        System.out.println("프로그램 종료");
    }
}

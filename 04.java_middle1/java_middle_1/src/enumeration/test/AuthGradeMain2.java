package enumeration.test;

import enumeration.ex3.Grade;

import java.util.Scanner;

public class AuthGradeMain2 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("당신의 등급을 입력하세요[GUEST, LOGIN, ADMIN]: ");
        String input = in.nextLine();

        AuthGrade authGrade = AuthGrade.valueOf(input.toUpperCase());
        System.out.println("당신의 등급은 " + authGrade.getDescription() + " 입니다 ");
        System.out.println("==메뉴 목록=");

        switch (authGrade) {
            case AuthGrade.GUEST :
                System.out.println("-메인 화면");
                break;
            case AuthGrade.LOGIN:
                System.out.println("-메인 화면");
                System.out.println("-이메일 관리 화면");
                break;
            case AuthGrade.ADMIN:
                System.out.println("-메인 화면");
                System.out.println("-이메일 관리 화면");
                System.out.println("-관리자화면");
                break;
        }



    }
}

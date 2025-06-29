package nested.annonymous.ex;

public class Ex0RefMain {
    public static void main(String[] args) {
        hello("Java");
        hello("Spring");
    }

    public static void hello(String text){
        System.out.println("프로그램 시작");
        System.out.println("Hello " + text);
        System.out.println("프로그램 종료");
    }

}
package lang.string.test;

public class TestString4 {

    public static void main(String[] args) {
        String str = "hello.txt";
        int startSub = str.indexOf(".txt") ;
        String filename = str.substring(0, startSub);
        String extName = str.substring(startSub , str.length());
        System.out.println("filename = " + filename);
        System.out.println("extName = " + extName);
    }
}

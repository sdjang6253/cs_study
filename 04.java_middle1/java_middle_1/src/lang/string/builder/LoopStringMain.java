package lang.string.builder;

public class LoopStringMain {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        String result = "";
        for(int i = 0; i <= 100000; i++) {
            result += "test";
        }
        long end = System.currentTimeMillis();
        //System.out.println("result = " + result);
        System.out.println("소요시간 = " + (end - start) + "ms");
    }
}

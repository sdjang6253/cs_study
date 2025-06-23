package lang.string.builder;

public class LoopStringBuilderMain {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= 100000; i++) {
            sb.append("test");
        }
        long end = System.currentTimeMillis();
        System.out.println("result = " + sb.toString());
        System.out.println("소요시간 = " + (end - start) + "ms");
    }
}

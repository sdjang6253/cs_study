package lang.string.builder;

public class StringBuilderMain1_2 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("hello").append("java").insert(5, " ").delete(5, 6).reverse();
        String result = sb.toString();
        System.out.println(result);

    }
}

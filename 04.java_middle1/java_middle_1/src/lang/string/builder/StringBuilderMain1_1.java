package lang.string.builder;

public class StringBuilderMain1_1 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("hello").append("java");
        String result = sb.toString();
        System.out.println(result);

        sb.insert(5, " ");
        System.out.println(sb);

        sb.delete(5, 6);
        System.out.println(sb);

        sb.reverse();
        System.out.println(sb);

        String str = sb.toString();
        System.out.println(str);

    }
}

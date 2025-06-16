package lang.string.method;

public class StringSplitJoinMain {
    public static void main(String[] args) {

        String str = "Apple,Banana,대구";
        String[] splitStr = str.split(",");
        for (String s : splitStr) {
            System.out.println(s);
        }

        String joinedStr = String.join("-", splitStr);
        System.out.println(joinedStr);

    }
}

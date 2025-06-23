package lang.string.test;

public class TestString6 {
    public static void main(String[] args) {
        String str = "start hello java, hello spring, hello jpa";
        String key = "hello";
        int cnt = 0;
        int idx = str.indexOf(key);
        while (idx != -1) {
            cnt++;
            idx = str.indexOf(key, idx + key.length());
        }
        System.out.println("cnt = " + cnt);
    }
}

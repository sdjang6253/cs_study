package lang.object.equals;

public class EqualsMainV2 {

    public static void main(String[] args) {
        UserV2 user1 = new UserV2("id-001");
        UserV2 user2 = new UserV2("id-001");

        System.out.println(user1 == user2);
        System.out.println(user1.equals(user2));
    }
}

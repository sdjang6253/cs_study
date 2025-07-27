package collection.link;

public class NodeMain2 {

    public static void main(String[] args) {
        Node first = new Node("first");
        first.next = new Node("second");
        first.next.next = new Node("third");

        System.out.println(first);
        System.out.println(first.next);
    }
}

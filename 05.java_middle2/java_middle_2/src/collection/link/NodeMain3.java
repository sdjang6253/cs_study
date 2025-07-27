package collection.link;

public class NodeMain3 {
    public static void main(String[] args) {
        Node first = new Node("first");
        first.next = new Node("second");
        first.next.next = new Node("third");

        System.out.println("모든 노드 탐색하기");
        printAll(first);

        Node lastNode = getLastNode(first);
        System.out.println("lastNode = " + lastNode);

        int index = 2;
        Node index2Node = getNode(first, index);
        System.out.println("index2Node = " + index2Node);

        System.out.println("데이터 추가하기");
        add(first , "forth");
        System.out.println(first);

        System.out.println("데이터 추가하기");
        add(first , "fifth");
        System.out.println(first);
    }

    private static void printAll(Node first) {
        Node x = first;
        while (x != null) {
            System.out.println(x.item);
            x = x.next;
        }
    }

    private static Node getLastNode(Node first) {
        Node x = first;
        while (x.next != null) {
            x = x.next;
        }
        return x;
    }

    private static Node getNode(Node first, int index) {
        Node x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private static void add(Node first, String forth) {
        getLastNode(first).next = new Node(forth);
    }
}

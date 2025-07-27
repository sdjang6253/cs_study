package collection.link;

import java.net.InetSocketAddress;

public class MyLinkedListV2 {

    private Node first;
    private int size;

    public void add(Object e){
        Node newNode = new Node(e);
        if(first == null){
            first = newNode;
        }
        else{
            Node lastNode = getLastNode();
            lastNode.next = newNode;
        }
        size++;
    }

    private Node getLastNode() {
        Node x = first;
        while(x.next != null){
            x = x.next;
        }
        return x;
    }

    public Object set(int index , Object element){
        Node x = getNode(index);
        Object oldValue = x.item;
        x.item = element;
        return oldValue;
    }

    public Object get(int index){
        Node node = getNode(index);
        return node.item;
    }

    private Node getNode(int index) {
        Node x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public int indexOf(Object e){
        int index = 0;

        for (Node x = first ; x != null ; x=x.next) {
            if (x.item.equals(e)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString() {
        return "MyLinkedListV1{" +
                "first=" + first +
                ", size=" + size +
                '}';
    }

    public void add(int index , Object e){
        Node newNode = new Node(e);

        if(index == 0 ){
            if(first != null){
                newNode.next = first;
            }
            first = newNode;
        } else {
            Node prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }
    public Object remove (int index){
        Node removeNode = getNode(index);
        Object removedItem = removeNode.item;
        if(index == 0){
            first = removeNode.next;
        } else{
            Node prev = getNode(index - 1);
            prev.next = removeNode.next;
        }
        removeNode.item = null;
        removeNode.next = null;
        size --;
        return removedItem;
    }
}

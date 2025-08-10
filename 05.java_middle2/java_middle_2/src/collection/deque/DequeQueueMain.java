package collection.deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

public class DequeQueueMain {
    public static void main(String[] args) {
        Queue<Integer> deque = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();

        deque.offer(1);
        deque.offer(2);
        deque.offer(3);
        System.out.println("deque = " + deque);

        System.out.println("deque.peek() = " + deque.peek());

        System.out.println("deque.poll() = " + deque.poll());
        System.out.println("deque.poll() = " + deque.poll());
        System.out.println("deque.poll() = " + deque.poll());
        System.out.println("deque = " + deque);
    }
}

package collection.map.test.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class TaskScheduler {
    Deque<Task> deque = new ArrayDeque<>();

    public void addTask(Task task) {
        deque.add(task);
    }

    public int getRemainingTasks() {
        return deque.size();
    }

    public void processNextTask() {
        if (deque.isEmpty()) {
            return;
        }
        Task task = deque.poll();
        task.execute();
    }
}

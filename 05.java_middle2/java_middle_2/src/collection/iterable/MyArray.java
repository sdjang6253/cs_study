package collection.iterable;

import java.util.Iterator;

public class MyArray implements Iterable<Integer>{
    private int[] numbers;


    @Override
    public Iterator<Integer> iterator() {
        return new MyArrayIterator(numbers);
    }

    public MyArray(int[] numbers) {
        this.numbers = numbers;
    }
}

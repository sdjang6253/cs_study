package collection.set;

import java.util.Arrays;
import java.util.LinkedList;

public class MyHashSetV3<E> implements MySet<E>{

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    private LinkedList<E>[] buckets;
    private int size = 0;
    private int capacity = DEFAULT_INITIAL_CAPACITY;

    public MyHashSetV3() {
        initBuckets();
    }

    public MyHashSetV3(int capacity) {
        this.capacity = capacity;
        initBuckets();
    }

    private void initBuckets() {
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hashIndex(E value){
        return Math.abs(value.hashCode()) % capacity;
    }

    public boolean add(E value){
        int hashIndex = hashIndex(value);
        LinkedList<E> bucket = buckets[hashIndex];
        if(!bucket.contains(value)){
            bucket.add(value);
            size++;
            return true;
        }else {
            return false;
        }
    }
    public boolean contains(E searchValue){
        int hashIndex = hashIndex(searchValue);
        LinkedList<E> bucket = buckets[hashIndex];
        return bucket.contains(searchValue);
    }

    public boolean remove(E value){
        int hashIndex = hashIndex(value);
        LinkedList<E> bucket = buckets[hashIndex];
        boolean isRemoved = bucket.remove(value);
        if(isRemoved){
            size--;
        }
        return isRemoved;
    }

    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        return "MyHashSetV3{" +
                "buckets=" + Arrays.toString(buckets) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}

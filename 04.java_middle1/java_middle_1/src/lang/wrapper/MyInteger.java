package lang.wrapper;

public class MyInteger {
    private final int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int compareTo(int target){
        if(value < target){
            return -1;
        } else if(value == target){
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "MyInteger{" +
                "value=" + value +
                '}';
    }
}

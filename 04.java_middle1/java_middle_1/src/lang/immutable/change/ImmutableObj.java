package lang.immutable.change;

public class ImmutableObj {
    private final int value;

    public ImmutableObj(int value) {
        this.value = value;
    }

    public ImmutableObj add(int addingValue) {
        return new ImmutableObj(value + addingValue);
    }

    public int getValue() {
        return value;
    }
}

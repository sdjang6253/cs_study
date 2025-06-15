package lang.immutable.address;

import java.util.Objects;

public class ImmutableAddress {
    private final String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ImmutableAddress{" +
                "value='" + value + '\'' +
                '}';
    }

    public ImmutableAddress(String value) {
        this.value = value;
    }
}

package lang.object.equals;

import java.util.Objects;

public class UserV2 {
    String id;

    public UserV2(String id) {
        this.id = id;
    }

    /*
    @Override
    public boolean equals(Object o) {
        UserV2 user = (UserV2) o;
        return id.equals(user.id);
    }
     */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserV2 userV2 = (UserV2) o;
        return Objects.equals(id, userV2.id);
    }

}

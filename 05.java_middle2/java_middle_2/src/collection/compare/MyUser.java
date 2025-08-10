package collection.compare;

import java.util.Objects;

public class MyUser implements Comparable<MyUser>{

    private String id;
    private int age;

    public MyUser(String id, int age) {
        this.id = id;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        MyUser myUser = (MyUser) object;
        return age == myUser.age && Objects.equals(id, myUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age);
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id='" + id + '\'' +
                ", age=" + age +
                '}';
    }
    @Override
    public int compareTo(MyUser o) {
        return this.age < o.age ? -1 : ( this.age == o.age) ? 0 : 1 ;
    }
}

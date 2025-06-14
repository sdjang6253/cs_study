package lang.object;

public class ObjectMain {
    public static void main(String[] args) {

        Child child = new Child();
        child.parentMethod();
        child.childMethod();

        String str = child.toString();
        System.out.println(str);
    }
}

package collection.array;

public class MyArrayListV4Main {

    public static void main(String[] args) {
        MyArrayListV4<String> strList = new MyArrayListV4<>();
        strList.add("a");
        strList.add("b");
        strList.add("c");
        String strResult = strList.get(0);
        System.out.println("strResult = " + strResult);

        MyArrayListV4<Integer> intList = new MyArrayListV4<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        Integer intResult = intList.get(0);
        System.out.println("intResult = " + intResult);
    }
}


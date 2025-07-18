package generic.ex4;

import generic.animal.Cat;
import generic.animal.Dog;

public class MethodMain2 {
    public static void main(String[] args) {

        Dog dog = new Dog("멍멍", 100);
        Cat cat = new Cat("냐옹", 200);

        AnimalMethod.checkup(dog);
        AnimalMethod.checkup(cat);

        Dog bigDog = new Dog("큰멍멍", 200);
        Dog bigger = AnimalMethod.bigger(dog, bigDog);
        System.out.println("bigger = " + bigger);
    }
}

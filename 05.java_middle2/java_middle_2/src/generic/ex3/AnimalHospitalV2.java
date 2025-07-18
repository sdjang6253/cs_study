package generic.ex3;

import generic.animal.Animal;

public class AnimalHospitalV2<T> {
    private T animal;

    public void set(T animal) {
        this.animal = animal;
    }

    public void checkup(){
        // T 의 타입을 매서드 정의 시점에 알수가 없다. -> Object 기능만 사용 가능
        //System.out.println("동물 이름 : " + animal.getName());
        //System.out.println("동물 크기 : " + animal.getSize());
        //animal.sound();
    }

    public T bigger(T target){
        //return animal.getSize() > target.getSize() ? animal : target;
        return null;
    }
}

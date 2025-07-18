package generic.ex5;

import generic.animal.Animal;

public class EraserBox <T>{
    public boolean instanceCheck(Object param){
        // Generic 은 타입을 지워버리기 때문에, 컴파일 될때는 저 T 는 Object 가 되어서 이건 불가능
        // return param instanceof T;
        return false;
    }
    public T create(){
        // Generic 은 타입을 지워버리기 때문에, 컴파일 될때는 저 T 는 Object 가 되어서 이건 불가능
        //return new T();
        return  null;
    }
}

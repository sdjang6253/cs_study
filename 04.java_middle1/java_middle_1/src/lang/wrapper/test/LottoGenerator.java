package lang.wrapper.test;

import java.util.Arrays;
import java.util.Random;

public class LottoGenerator {

    private final Random random  = new Random();
    private int[] lottoNumbers;
    private int count;

    public int[] generate(){
        lottoNumbers = new int[6];
        count = 0;

        while (count < 6){
            int number = random.nextInt(45)+ 1;

            //please increase int 'number' to lottoNumbers when number not conatins lottoNumbers array
            if(checkUnique(number)){
                lottoNumbers[count] = number;
                count++;
            }
        }
        return lottoNumbers;
    }

    private boolean checkUnique(int number) {
        for(int i = 0 ; i < count ; i++){
            if(lottoNumbers[i] == number){
              return false;
            }
        }
        return true;
    }
}

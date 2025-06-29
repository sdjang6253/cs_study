package time;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class ChangeTimePlusMain {

    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2018, 2, 2, 13, 30, 59);
        System.out.println(dt);

        LocalDateTime plusDt1 = dt.plus(10 , ChronoUnit.YEARS);
        System.out.println(plusDt1);

        LocalDateTime plusDt2 = dt.plusYears(10);
        System.out.println(plusDt2);

        Period period = Period.ofYears(10);
        LocalDateTime plusDt3 = dt.plus(period);
        System.out.println(plusDt3);
    }
}

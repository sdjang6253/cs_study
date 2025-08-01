package time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitMain {
    public static void main(String[] args) {
        ChronoUnit[] values = ChronoUnit.values();

        for (ChronoUnit value : values) {
            System.out.println("value = " + value);
        }
        System.out.println("HOURS = " + ChronoUnit.HOURS);

        System.out.println("HOURS.duration = " + ChronoUnit.HOURS.getDuration().getSeconds());
        System.out.println("ChronoUnit.DAYS = " + ChronoUnit.DAYS);
        System.out.println("ChronoUnit.DAYS.getDuration().getSeconds() = " + ChronoUnit.DAYS.getDuration().getSeconds());

        LocalTime lt1 = LocalTime.of(1 , 10 , 0);
        LocalTime lt2 = LocalTime.of(1,20,0);

        long secondsBetween = ChronoUnit.SECONDS.between(lt1, lt2);
        System.out.println(secondsBetween);
        long minutesBetween = ChronoUnit.MINUTES.between(lt1, lt2);
        System.out.println(minutesBetween);

    }
}

package time;

import java.time.LocalDate;
import java.time.Period;

public class PeriodMain {

    public static void main(String[] args) {

        Period period = Period.ofDays(20);
        System.out.println("period = " + period);

        LocalDate localDate = LocalDate.of(2030, 1, 1);
        LocalDate plusDate = localDate.plus(period);
        System.out.println("localDate = " + localDate);
        System.out.println("plusDate = " + plusDate);

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        Period between = Period.between(startDate, endDate);
        System.out.println(between.getYears() + " 년 "  + between.getMonths() + " 월 " +  between.getDays() + " 일 ");
    }
}

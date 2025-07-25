package time;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.next;

public class ChangeTimeWithMain {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2018,1,1,13,30,59);
        System.out.println("dt = " + dt);

        LocalDateTime changeDt1 = dt.with(ChronoField.YEAR, 2020);
        System.out.println("changeDt1 = " + changeDt1);

        LocalDateTime changedDt2 = dt.withYear(2021);
        System.out.println("changedDt2 = " + changedDt2);

        //다음주 금요일
        LocalDateTime with1 = dt.with(next(DayOfWeek.FRIDAY));
        System.out.println("기준 날짜 " + dt);
        System.out.println("다음주 금요일 " + with1);

        //이번달의 마지막 일요일
        LocalDateTime with2 = dt.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println("같은 달의 마지막 일요일 " + with2);

    }
}

package time.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestZone {
    public static void main(String[] args) {
        ZonedDateTime seoulTime = ZonedDateTime.of(LocalDate.of(2024, 1, 1), LocalTime.of(9, 0), ZoneId.of("Asia/Seoul"));
        ZonedDateTime londonTime = seoulTime.withZoneSameInstant(ZoneId.of("Europe/London"));
        ZonedDateTime newyorkTime = seoulTime.withZoneSameInstant(ZoneId.of("America/New_York"));

        System.out.println("seoulTime = " + seoulTime);
        System.out.println("londonTime = " + londonTime);
        System.out.println("newyorkTime = " + newyorkTime);
    }
}

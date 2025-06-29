package time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeMain {
    public static void main(String[] args) {
        ZonedDateTime nowZdt = ZonedDateTime.now();
        System.out.println("nowZdt = " + nowZdt);

        LocalDateTime localDateTime= LocalDateTime.of(2024,10,11,3,58,1);
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(localDateTime, ZoneId.of("Asia/Seoul"));
        System.out.println("zonedDateTime1 = " + zonedDateTime1);

        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(2028,1,5,13,25,47,0, ZoneId.of("Asia/Seoul"));
        System.out.println("zonedDateTime2 = " + zonedDateTime2);

        ZonedDateTime utcZdt = zonedDateTime2.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("utcZdt = " + utcZdt);

    }
}

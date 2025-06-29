package time;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class OffsetDateTimeMain {
    public static void main(String[] args) {
        OffsetDateTime nowOdt = OffsetDateTime.now();
        System.out.println("nowOdt = " + nowOdt);

        LocalDateTime ldt = LocalDateTime.of(2030,1,1,22,33,44);
        System.out.println("ldt = " + ldt);
        OffsetDateTime odt = OffsetDateTime.of(ldt , ZoneOffset.of("+02:00"));
        System.out.println("odt = " + odt);
    }
}

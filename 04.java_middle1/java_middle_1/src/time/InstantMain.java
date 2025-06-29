package time;

import java.time.Instant;
import java.time.ZonedDateTime;

public class InstantMain {

    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println("now = " + now);

        ZonedDateTime zdt = ZonedDateTime.now();
        Instant from = Instant.from(zdt);
        System.out.println("from = " + from);

        Instant epochSecond = Instant.ofEpochSecond(0);
        System.out.println("epochSecond = " + epochSecond);

        Instant later = epochSecond.plusSeconds(2333);
        System.out.println("later = " + later);

        Long laterEpochSecond = later.getEpochSecond();
        System.out.println("laterEpochSecond = " + laterEpochSecond);
    }
}

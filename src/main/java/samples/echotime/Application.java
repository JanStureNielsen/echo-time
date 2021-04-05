package samples.echotime;

import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) {
        Arguments arguments = Arguments.parse(args);

        for (long atNextNanos = System.nanoTime(), count = arguments.counts; count > 0;) {
            if (System.nanoTime() >= atNextNanos) {
                System.out.println(LocalDateTime.now());

                atNextNanos += arguments.delayNanos;
                count--;
            }
        }
    }

}

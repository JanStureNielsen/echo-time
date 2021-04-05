package samples.echotime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ArgumentsTest {
    @Test
    public void usage() {
        assertThat(Arguments.usage()).contains("Usage:");
    }

    @Test
    public void nullArgs() {
        assertThrows(NullPointerException.class, () -> Arguments.parse(null));
    }

    @Test
    public void defaultArguments() {
        Arguments args = Arguments.parse(new String[0]);

        assertThat(args.counts).isEqualTo(1);
        assertThat(args.delayNanos).isEqualTo(0);
    }

    @Test
    public void unknownOption() {
        assertThrows(IllegalArgumentException.class, () -> Arguments.parse(new String[] {"--unknown-option"}));
    }

    @Test
    public void missingCounts() {
        assertThrows(IllegalArgumentException.class, () -> Arguments.parse(new String[] {"--counts"}));
        assertThrows(IllegalArgumentException.class, () -> Arguments.parse(new String[] {"-c"}));
    }

    @Test
    public void missingDelay() {
        assertThrows(IllegalArgumentException.class, () -> Arguments.parse(new String[] {"--delay"}));
        assertThrows(IllegalArgumentException.class, () -> Arguments.parse(new String[] {"-d"}));
    }

    @Test
    public void arguments() {
        Arguments args = Arguments.parse(new String[] {"--count", "2", "--delay", "3"});

        assertThat(args.counts).isEqualTo(2);
        assertThat(args.delayNanos).isEqualTo(3);
    }

}

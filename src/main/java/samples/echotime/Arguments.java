package samples.echotime;

public class Arguments {
    final long counts;
    final long delayNanos;

    private Arguments(long counts, long delayNanos) {
        this.counts = counts;
        this.delayNanos = delayNanos;
    }

    public static Arguments parse(String[] args) {
        long counts = 1;
        long delayNanos = 0;

        for (int i = 0; i < args.length; i++) {
            switch(args[i]) {

            case "--help":
            case "-h":
            case "-?":
                System.out.println(usage());
                System.exit(1);
            case "--count":
            case "-c":
                counts = parseCounts(args, i);
                i++;
                continue;

            case "--delay":
            case "-d":
                delayNanos = parseDelay(args, i);
                i++;
                continue;
            default:
                throw illegalArgument(String.format("'%s' is unknown", args[i]));
            }
        }

        return new Arguments(counts, delayNanos);
    }

    public static String usage() {
        return "Usage:  echo-time [-c <count>] [-d <nano-interval>]\n\n" +
               "   -c, --count <n>  iterations to perform\n" +
               "   -d, --delay <n>  nanoseconds between iterations\n";
    }

    private static long parseCounts(String[] args, int i) {
        String value = argumentValue(args, i, "count");

        return Long.parseLong(value);
    }

    private static long parseDelay(String[] args, int i) {
        String value = argumentValue(args, i, "delay");

        //TODO: handle trailing: 'H', 'M', 's', 'm', 'u', 'n', etc.

        return Long.parseLong(value);
    }

    private static String argumentValue(String[] args, int i, String argument) {
        if ((i+1) >= args.length) {
            throw illegalArgument(String.format("--%s is missing a value.", argument));
        }

        return args[i+1];
    }

    private static IllegalArgumentException illegalArgument(String message) {
        return new IllegalArgumentException(message);
    }

}

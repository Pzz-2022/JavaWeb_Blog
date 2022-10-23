package util;

public class SnowIDUtils {
    private static final long START_TIME = 1661961600000L - 1000L * 60 * 60 * 24 * 10;
    private static final long sequenceMax = 3L;
    private static long lastTime = -1L;
    private static long sequence = 0;

    public static synchronized long getSnowID() {
        long currentTime = System.currentTimeMillis();
        if (currentTime < lastTime) {
            System.out.println("出问题了");
            return 0;
        }
        if (currentTime == lastTime) {
            sequence = (sequence + 1) & sequenceMax;
            if (sequence == 0L) {
                currentTime = getNextTime();
                lastTime = currentTime;
            }
        } else {
            sequence = 0L;
            lastTime = currentTime;
        }

        return (currentTime - START_TIME) << 2
                | sequence;
    }

    private static long getNextTime() {
        long nextTime;
        do {
            nextTime = System.currentTimeMillis();
        } while (nextTime <= lastTime);
        return nextTime;
    }
}

package game;

import java.util.concurrent.TimeUnit;

public class User {
    String id;
    long lastActiveTime;

    public boolean activeAfter(int threshold, TimeUnit timeUnit) {
        return System.currentTimeMillis() - lastActiveTime > timeUnit.toMillis(threshold);
    }
}

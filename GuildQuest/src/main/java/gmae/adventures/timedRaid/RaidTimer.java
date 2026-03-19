package gmae.adventures.timedRaid;

import gmae.core.entity.WorldTime;
import gmae.core.time.WorldClock;

public class RaidTimer {
    private static final int MINUTES_PER_TURN = 5;

    private final WorldClock worldClock;
    private final WorldTime startTime;
    private final WorldTime timeLimit;
    private WorldTime elapsed;
    private boolean expired;

    public RaidTimer(int timeLimitMinutes) {
        this.worldClock = WorldClock.getInstance();
        this.startTime = worldClock.now();
        this.timeLimit = new WorldTime(0, 0, timeLimitMinutes);
        this.elapsed = new WorldTime(0, 0, 0);
        this.expired = false;
    }

    public void tick() {
        if (expired) {
            return;
        }

        worldClock.advance(MINUTES_PER_TURN);
        elapsed = elapsed.add(MINUTES_PER_TURN);

        if(elapsed.toTotalMinutes() >= timeLimit.toTotalMinutes()) {
            expired = true;
        }
    }

    public boolean isExpired() {
        return expired;
    }
    public WorldTime getElapsed() {
        return elapsed;
    }
    public WorldTime getTimeLimit() {
        return timeLimit;
    }

    public WorldTime getTimeRemaining() {
        if (expired) {
            return new WorldTime(0, 0, 0);
        }
        int remainingMinutes = timeLimit.toTotalMinutes() - elapsed.toTotalMinutes();
        return new WorldTime(0, 0,  remainingMinutes);
    }

    public String getFormattedTimeRemaining() {
        return getTimeRemaining().toString();
    }

    public int getPercentElapsed() {
        if (timeLimit.toTotalMinutes() == 0) {
            return 100;
        }
        int pct = (elapsed.toTotalMinutes() * 100) / timeLimit.toTotalMinutes();
        return Math.min(pct, 100);
    }

    public void reset(int timeLimitMinutes) {
        this.elapsed = new WorldTime(0, 0, 0);
        this.expired = false;
        worldClock.setTime(startTime);
    }

}

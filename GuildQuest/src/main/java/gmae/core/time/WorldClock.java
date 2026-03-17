package gmae.core.time;

import gmae.core.entity.WorldTime;

public class WorldClock {
    // ── REUSED: Singleton pattern from prior assignment ──────────

    private static WorldClock instance;
    private WorldTime currentTime;

    private WorldClock() {
        this.currentTime = new WorldTime(1, 0, 0);
    }

    public static WorldClock getInstance() {
        if (instance == null) {
            instance = new WorldClock();
        }
        return instance;
    }

    public WorldTime now() {
        return currentTime;
    }

    public void advance(int minutes) {
        currentTime = currentTime.plusMinutes(minutes);
    }

    public void setTime(WorldTime newTime) {
        this.currentTime = newTime;
    }
}

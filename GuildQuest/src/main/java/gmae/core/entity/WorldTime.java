package gmae.core.entity;

public class WorldTime {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int MINUTES_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR;

    private final int days;
    private final int hours;
    private final int minutes;

    public WorldTime(int days, int hours, int minutes) {
        WorldTime normalized = fromTotalMinutes(toTotalMinutes(days, hours, minutes));
        this.days = normalized.days;
        this.hours = normalized.hours;
        this.minutes = normalized.minutes;
    }

    // ── Private helpers ───────────

    private static int toTotalMinutes(int days, int hours, int minutes) {
        return days * MINUTES_PER_DAY + hours * MINUTES_PER_HOUR + minutes;
    }

    private static WorldTime fromTotalMinutes(int totalMinutes) {
        int d = totalMinutes / MINUTES_PER_DAY;
        int remaining = totalMinutes % MINUTES_PER_DAY;
        int h = remaining / MINUTES_PER_HOUR;
        int m = remaining % MINUTES_PER_HOUR;

        return new WorldTime(d, h, m, true);
    }

    /** Private constructor that skips normalization */
    private WorldTime(int days, int hours, int minutes, boolean alreadyNormalized) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes
    }

    // ── API ───────────
    public int getDays() { return days; }
    public int getHours() { return hours; }
    public int getMinutes() { return minutes; }

    public int toTotalMinutes() {
        return toTotalMinutes(days, hours, minutes);
    }

    public WorldTime add(WorldTime other) {
        return fromTotalMinutes(this.totalMinutes() + other.toTotalMinutes());
    }

    public WorldTime subtract(WorldTime other) {
        return fromTotalMinutes(this.totalMinutes() - other.toTotalMinutes());
    }

    // ── Object overrides ───────────
    @Override
    public String toString() {
        String timeStr = String.format("%02d:%02d", hours, minutes);
        if (days > 0) {
            return "Day " + days + ", " + timeStr;
        }
        return timeStr;
    }

    /** Makes it so equal times have same hash.
     *  If two objects are equal using equals(),
     *  They return same hash
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof WorldTime)) return false;
        WorldTime other = (WorldTime) obj;
        return days == other.days && hours == other.hours && minutes == other.minutes;
    }

    @Override
    public int hashCode() {
        return toTotalMinutes();
    }
}
package gmae.core.time;

import gmae.core.entity.WorldTime;
import gmae.core.entity.Realm;

// ── Displays both world time and realm's local time ──────────
public class BothTimeStrategy implements TimeDisplayStrategy {
    @Override
    public String format(WorldTime worldTime, Realm realm) {
        WorldTime local = realm.getLocalTime(worldTime);
        return "WORLD: " + worldTime + " | LOCAL: " + local;
    }
}

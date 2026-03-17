package gmae.core.time;

import gmae.core.entity.WorldTime;
import gmae.core.entity.Realm;

// ── Time derived from realm's time offset ──────────
public class LocalOnlyStrategy implements TimeDisplayStrategy {
    @Override
    public String format(WorldTime worldTime, Realm realm) {
        WorldTime local = realm.getLocalTime(worldTime);
        return "LOCAL: " + local;
    }
}

package gmae.core.time;

import gmae.core.entity.WorldTime;
import gmae.core.entity.Realm;

// ── Display only world time; ignore realm offsets ──────────
public class WorldOnlyStrategy implements TimeDisplayStrategy {
    @Override
    public String format(WorldTime worldTime, Realm realm) {
        return "WORLD: " + worldTime;
    }
}


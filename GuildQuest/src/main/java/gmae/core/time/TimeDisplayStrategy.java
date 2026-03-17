package gmae.core.time;

import gmae.core.entity.WorldTime;
import gmae.core.entity.Realm;

// ── Strategy interface for formatting time display ──────────
public interface TimeDisplayStrategy {
    String format(WorldTime worldTime, Realm realm);
}

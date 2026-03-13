package gmae.engine;

import gmae.api.MiniAdventure;
import gmae.adventures.relicHunt.RelicHuntAdventure;
import gmae.adventures.timedRaid.TimedRaidAdventure;

// REUSED: Factory Method pattern from StandardRealmFactory (prior assignment)
public class StandardAdventureFactory extends MiniAdventureFactory {

    @Override
    public MiniAdventure createAdventure(String type) {
        switch (type) {
            case "RelicHunt":
                return new RelicHuntAdventure();
            case "TimedRaid":
                return new TimedRaidAdventure();
            default:
                throw new IllegalArgumentException(
                    "[Factory] Unknown adventure type: " + type);
        }
    }
}
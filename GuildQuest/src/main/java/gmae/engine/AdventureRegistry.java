package gmae.engine;

import java.util.ArrayList;
import java.util.List;

public class AdventureRegistry {

    private final List<String> registeredTypes;

    public AdventureRegistry() {
        this.registeredTypes = new ArrayList<>();
        registeredTypes.add("RelicHunt");
        registeredTypes.add("TimedRaid");
    }

    public List<String> getRegisteredTypes() {
        return registeredTypes;
    }

    public void register(String adventureType) {
        if (!registeredTypes.contains(adventureType)) {
            registeredTypes.add(adventureType);
        }
    }
}
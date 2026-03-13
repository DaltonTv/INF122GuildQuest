package gmae.engine;

import gmae.api.MiniAdventure;

// REUSED: Factory Method pattern from RealmFactory (prior assignment)
public abstract class MiniAdventureFactory {
    public abstract MiniAdventure createAdventure(String type);
}
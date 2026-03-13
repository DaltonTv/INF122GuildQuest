package gmae.engine;

import gmae.api.MiniAdventure;
import gmae.core.entity.Realm;
import gmae.core.entity.Campaign;
import gmae.core.entity.QuestEvent;
import gmae.core.entity.WorldTime;
import gmae.core.time.WorldClock;
import gmae.core.enums.PermissionLevel;
import gmae.observer.GameObserver;
import gmae.profile.PlayerProfile;

import java.util.ArrayList;
import java.util.List;

public class GuildQuestManager {

    // ── REUSED: Singleton pattern from prior assignment ──────────
    private static GuildQuestManager instance;

    // ── REUSED: core domain lists from prior GuildQuestManager ───
    private List<Realm> realms;
    private List<GameObserver> observers;

    // ── NEW: GMAE-specific fields ────────────────────────────────
    private final SessionStateStore sessionStore;
    private final AdventureRegistry registry;
    private final WorldClock worldClock; // REUSED: Singleton WorldClock

    private GuildQuestManager() {
        this.realms = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.sessionStore = SessionStateStore.getInstance();
        this.registry = new AdventureRegistry();
        this.worldClock = WorldClock.getInstance(); // REUSED
    }

    public static GuildQuestManager getInstance() {
        if (instance == null) {
            instance = new GuildQuestManager();
        }
        return instance;
    }

    // ── REUSED: Realm management from prior assignment ───────────
    public Realm findRealm(String realmId) {
        for (Realm realm : realms) {
            if (realm.getRealmId().equals(realmId)) {
                return realm;
            }
        }
        return null;
    }

    public List<Realm> getRealms() {
        return realms;
    }

    public WorldClock getWorldClock() {
        return worldClock;
    }

    // ── NEW: Session Management ──────────────────────────────────
    public void startSession(PlayerProfile p1, PlayerProfile p2) {
        sessionStore.setPlayers(p1, p2);
        sessionStore.setSessionActive(true);
        System.out.println("[GMAE] Session started: "
                + p1.getName() + " & " + p2.getName());
    }

    public void endSession() {
        sessionStore.reset();
        System.out.println("[GMAE] Session ended.");
    }

    public boolean isSessionActive() {
        return sessionStore.isSessionActive();
    }

    // ── NEW: Adventure Management ────────────────────────────────
    public void launchAdventure(String adventureType) {
        MiniAdventureFactory factory = new StandardAdventureFactory();
        MiniAdventure adventure = factory.createAdventure(adventureType);
        sessionStore.setActiveAdventure(adventure);
        adventure.initialize(
                sessionStore.getPlayer1(),
                sessionStore.getPlayer2()
        );
        System.out.println("[GMAE] Launched: " + adventureType);
    }

    public List<String> getAvailableAdventures() {
        return registry.getRegisteredTypes();
    }

    // ── REUSED: Observer pattern from prior assignment ───────────
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String campaignName, String eventTitle) {
        for (GameObserver observer : observers) {
            observer.onGameEvent(campaignName, eventTitle);
        }
    }
}
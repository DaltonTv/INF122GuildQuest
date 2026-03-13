package gmae.engine;

import gmae.profile.PlayerProfile;
import gmae.api.MiniAdventure;

public class SessionStateStore {

    private static SessionStateStore instance;

    private PlayerProfile player1;
    private PlayerProfile player2;
    private MiniAdventure activeAdventure;
    private boolean sessionActive;

    private SessionStateStore() {
        this.sessionActive = false;
    }

    public static SessionStateStore getInstance() {
        if (instance == null) {
            instance = new SessionStateStore();
        }
        return instance;
    }

    public void setPlayers(PlayerProfile p1, PlayerProfile p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public PlayerProfile getPlayer1() { return player1; }
    public PlayerProfile getPlayer2() { return player2; }

    public void setActiveAdventure(MiniAdventure adventure) {
        this.activeAdventure = adventure;
    }

    public MiniAdventure getActiveAdventure() { return activeAdventure; }

    public void setSessionActive(boolean active) { this.sessionActive = active; }
    public boolean isSessionActive() { return sessionActive; }

    public void reset() {
        player1 = null;
        player2 = null;
        activeAdventure = null;
        sessionActive = false;
    }
}
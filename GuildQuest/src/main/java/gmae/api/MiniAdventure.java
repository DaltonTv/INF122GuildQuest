package gmae.api;

import gmae.profile.PlayerProfile;

public interface MiniAdventure {
    void initialize(PlayerProfile p1, PlayerProfile p2);
    TurnResult advanceTurn(PlayerInput input);
    GameState getState();
    boolean isComplete();
    String getWinner();
    void reset();

}

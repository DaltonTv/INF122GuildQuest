package gmae.adventures.timedRaid;

import gmae.api.GameState;
import gmae.api.MiniAdventure;
import gmae.api.PlayerInput;
import gmae.api.TurnResult;
import gmae.profile.PlayerProfile;

public class TimedRaidAdventure implements MiniAdventure {
    @Override
    public void initialize(PlayerProfile p1, PlayerProfile p2) {

    }

    @Override
    public TurnResult advanceTurn(PlayerInput input) {
        return null;
    }

    @Override
    public GameState getState() {
        return null;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public String getWinner() {
        return "";
    }

    @Override
    public void reset() {

    }
}

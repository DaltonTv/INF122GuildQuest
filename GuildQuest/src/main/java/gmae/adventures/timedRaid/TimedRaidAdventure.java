package gmae.adventures.timedRaid;

import gmae.api.GameState;
import gmae.api.MiniAdventure;
import gmae.api.PlayerInput;
import gmae.api.TurnResult;
import gmae.core.entity.GridPosition;
import gmae.core.entity.Realm;
import gmae.core.entity.WorldTime;
import gmae.profile.PlayerProfile;

import java.util.List;

public class TimedRaidAdventure implements MiniAdventure {
    private static final int TIME_LIMIT_MINUTES = 60;
    private static final int GRID_SIZE = 5;

    private TimedRaidState state;
    private PlayerProfile p1;
    private PlayerProfile p2;

    @Override
    public void initialize(PlayerProfile p1, PlayerProfile p2) {
        this.p1 = p1;
        this.p2 = p2;

        Realm raidRealm = new Realm("Shadow Keep", "Dark fortress", 0, GRID_SIZE, GRID_SIZE);
        WorldTime start = new WorldTime(1, 0, 0);

        List<RaidObjective> objectives = List.of(
                new RaidObjective.Builder("Destroy the Altar", 2, 2)
                        .realm(raidRealm).startTime(start).build(),
                new RaidObjective.Builder("Seize the Vault", 1, 3)
                        .realm(raidRealm).startTime(start).build(),
                new RaidObjective.Builder("Defeat the Guardian", 3, 1)
                        .realm(raidRealm).startTime(start).build()
        );

        this.state = new TimedRaidState(objectives, TIME_LIMIT_MINUTES);
    }

    @Override
    public TurnResult advanceTurn(PlayerInput input) {
        if (state.isComplete()) {
            return new TurnResult("Game already over!", getState(), true);
        }
        if (input.getPlayerId() != state.getCurrentPlayerTurn()) {
            return new TurnResult("Not your turn, Player " + input.getPlayerId() + "!", getState(),false);
        }
        //1. Move current player
        moveCurrentPlayer(input.getAction());

        //2. Try completing an objective at new position
        GridPosition position = state.getCurrentPlayerPosition();
        boolean scored = state.tryCompleteObjectiveAt(position.getX(), position.getY());
        if (scored) {
           state.setStatusMessage("Player " + state.getCurrentPlayerTurn()
                   + " completed an objective! "
                   + state.getObjectivesCompleted() + "/"
                   +state.getTotalObjectives() + " done.");
        }

        //3. Tick timer
        state.tickTimer();

        //4. Check win/loss
        if (state.allObjectivesComplete()) {
            state.setComplete(true);
            state.setSuccess(true);
            state.setStatusMessage("Victory! All objectives completed in time!");
        }
        else if (state.isTimedOut()) {
            state.setComplete(true);
            state.setSuccess(false);
            state.setStatusMessage("Time's up! The raid has failed. "
                    + state.getObjectivesCompleted() + "/"
                    +state.getTotalObjectives() + " objectives completed");
        }
        else {
            state.advanceTurn();;
            if (!scored) {
                state.setStatusMessage("Turn done. Player "
                        + state.getCurrentPlayerTurn() + "'s turn. Time left: "
                        + state.getFormattedTimeRemaining());
            }
        }

        return new TurnResult(state.getStatusMessage(), getState(), state.isComplete());
    }

    @Override
    public GameState getState() {
        return new GameState(
                state.buildBoardDisplay(p1, p2),
                0,
                0,
                state.getCurrentPlayerTurn(),
                state.getStatusMessage()
        );
    }

    @Override
    public boolean isComplete() {
        return state.isComplete();
    }

    @Override
    public String getWinner() {
        if (!state.isComplete()) {
            return null;
        }
        return state.isSuccess() ?
                p1.getCharacterName() + " & " + p2.getCharacterName() :
                "Nobody - raid failed.";
    }

    @Override
    public void reset() {
        if (p1 != null && p2 != null) {
            initialize(p1, p2);
        }
    }

    private void moveCurrentPlayer(PlayerInput.Action action) {
        GridPosition position = state.getCurrentPlayerPosition();
        int x = position.getX();
        int y = position.getY();

        switch (action) {
            case MOVE_UP:
                if (y > 0) {
                    position.setY(y - 1);
                }
                break;
            case MOVE_DOWN:
                if (y < GRID_SIZE - 1) {
                    position.setY(y + 1);
                }
                break;
            case MOVE_LEFT:
                if (x > 0) {
                    position.setX(x - 1);
                }
                break;
            case MOVE_RIGHT:
                if (x < GRID_SIZE - 1) {
                    position.setX(x + 1);
                }
                break;
            default:
                break;
        }
    }
}

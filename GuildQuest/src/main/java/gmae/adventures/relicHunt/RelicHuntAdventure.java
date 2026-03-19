package gmae.adventures.relicHunt;

import gmae.api.GameState;
import gmae.api.MiniAdventure;
import gmae.api.PlayerInput;
import gmae.api.TurnResult;
import gmae.profile.PlayerProfile;

public class RelicHuntAdventure implements MiniAdventure {

    private PlayerProfile player1;
    private PlayerProfile player2;

    private RelicHuntMap map;
    private RelicHuntState state;

    @Override
    public void initialize(PlayerProfile p1, PlayerProfile p2) {
        this.player1 = p1;
        this.player2 = p2;

        this.map = new RelicHuntMap(5, 5);
        this.state = new RelicHuntState();

        map.addRelic(new Relic("Ancient Orb", 1, 1));
        map.addRelic(new Relic("Crystal Skull", 2, 3));
        map.addRelic(new Relic("Lost Crown", 3, 1));
    }

    @Override
    public TurnResult advanceTurn(PlayerInput input) {
        if (state.isComplete()) {
            return new TurnResult("The game is already over.", getState(), true);
        }

        if (input.getPlayerId() != state.getCurrentPlayerTurn()) {
            return new TurnResult(
                    "It is not Player " + input.getPlayerId() + "'s turn.",
                    getState(),
                    false
            );
        }

        moveCurrentPlayer(input.getAction());
        collectRelicIfPresent();
        updateGameStatus();

        return new TurnResult(state.getStatusMessage(), getState(), state.isComplete());
    }

    private void moveCurrentPlayer(PlayerInput.Action action) {
        int x;
        int y;

        if (state.getCurrentPlayerTurn() == 1) {
            x = state.getPlayer1X();
            y = state.getPlayer1Y();
        } else {
            x = state.getPlayer2X();
            y = state.getPlayer2Y();
        }

        int newX = x;
        int newY = y;

        if (action == PlayerInput.Action.MOVE_UP) {
            newY = y - 1;
        }
        else if (action == PlayerInput.Action.MOVE_DOWN) {
            newY = y + 1;
        }
        else if (action == PlayerInput.Action.MOVE_LEFT) {
            newX = x - 1;
        }
        else if (action == PlayerInput.Action.MOVE_RIGHT) {
            newX = x + 1;
        }

        if (map.isInsideMap(newX, newY)) {
            if (state.getCurrentPlayerTurn() == 1) {
                state.setPlayer1X(newX);
                state.setPlayer1Y(newY);
            } else {
                state.setPlayer2X(newX);
                state.setPlayer2Y(newY);
            }
        }
    }

    private void collectRelicIfPresent() {
        int x;
        int y;
        String playerName;

        if (state.getCurrentPlayerTurn() == 1) {
            x = state.getPlayer1X();
            y = state.getPlayer1Y();
            playerName = player1.getCharacterName();
        } else {
            x = state.getPlayer2X();
            y = state.getPlayer2Y();
            playerName = player2.getCharacterName();
        }

        Relic relic = map.getRelicAt(x, y);

        if (relic != null) {
            relic.collect();

            if (state.getCurrentPlayerTurn() == 1) {
                state.addPlayer1Score();
            } else {
                state.addPlayer2Score();
            }

            state.setStatusMessage(playerName + " found the relic: " + relic.getName() + "!");
        }
    }

    private void updateGameStatus() {
        if (state.getPlayer1Score() >= 2) {
            state.setComplete(true);
            state.setWinner(player1.getCharacterName());
            state.setStatusMessage(state.getWinner() + " wins Relic Hunt!");
            return;
        }

        if (state.getPlayer2Score() >= 2) {
            state.setComplete(true);
            state.setWinner(player2.getCharacterName());
            state.setStatusMessage(state.getWinner() + " wins Relic Hunt!");
            return;
        }

        if (map.getRemainingRelicCount() == 0) {
            state.setComplete(true);

            if (state.getPlayer1Score() > state.getPlayer2Score()) {
                state.setWinner(player1.getCharacterName());
                state.setStatusMessage(state.getWinner() + " wins Relic Hunt!");
            }
            else if (state.getPlayer2Score() > state.getPlayer1Score()) {
                state.setWinner(player2.getCharacterName());
                state.setStatusMessage(state.getWinner() + " wins Relic Hunt!");
            }
            else {
                state.setWinner("Tie");
                state.setStatusMessage("The game ended in a tie.");
            }

            return;
        }

        if (state.getCurrentPlayerTurn() == 1) {
            state.setCurrentPlayerTurn(2);
        } else {
            state.setCurrentPlayerTurn(1);
        }

        state.setStatusMessage("Turn complete. Player " + state.getCurrentPlayerTurn() + "'s turn.");
    }

    @Override
    public GameState getState() {
        return new GameState(
                map.buildBoardDisplay(
                        state.getPlayer1X(),
                        state.getPlayer1Y(),
                        state.getPlayer2X(),
                        state.getPlayer2Y()
                ),
                state.getPlayer1Score(),
                state.getCurrentPlayerTurn(),
                state.getPlayer2Score(),
                state.getStatusMessage()
        );
    }

    @Override
    public boolean isComplete() {
        return state.isComplete();
    }

    @Override
    public String getWinner() {
        return state.getWinner();
    }

    @Override
    public void reset() {
        if (player1 != null && player2 != null) {
            initialize(player1, player2);
        }
    }
}
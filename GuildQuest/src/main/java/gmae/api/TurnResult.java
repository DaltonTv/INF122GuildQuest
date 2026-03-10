package gmae.api;

public class TurnResult {
    private final String message;
    private final GameState newState;
    private final boolean gameOver;

    public TurnResult(String message, GameState newState, boolean gameOver) {
        this.message = message;
        this.newState = newState;
        this.gameOver = gameOver;
    }

    public String getMessage() {
        return message;
    }
    public GameState getNewState() {
        return newState;
    }
    public boolean isGameOver() {
        return gameOver;
    }
}

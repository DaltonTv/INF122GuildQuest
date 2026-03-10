package gmae.api;

public class GameState {
    private final String boardDisplay;
    private final int player1Score;
    private final int player2Score;
    private final int currentPlayerTurn;
    private final String statusMessage;

    public GameState(String boardDisplay, int p1Score,
                     int currentPlayerTurn, int p2Score, String statusMessage) {
        this.boardDisplay = boardDisplay;
        this.player1Score = p1Score;
        this.player2Score = p2Score;
        this.currentPlayerTurn = currentPlayerTurn;
        this.statusMessage = statusMessage;
    }

    public String getBoardDisplay() {
        return boardDisplay;
    }
    public int getPlayer1Score() {
        return player1Score;
    }
    public int getPlayer2Score() {
        return player2Score;
    }
    public int getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }
    public String getStatusMessage() {
        return statusMessage;
    }
}

package gmae.adventures.relicHunt;

public class RelicHuntState 
{
    private int player1X;
    private int player1Y;
    private int player2X;
    private int player2Y;

    private int player1Score;
    private int player2Score;

    private int currentPlayerTurn;
    private boolean complete;
    private String winner;
    private String statusMessage;

    public RelicHuntState() 
    {
        this.player1X = 0;
        this.player1Y = 0;
        this.player2X = 4;
        this.player2Y = 4;
        this.player1Score = 0;
        this.player2Score = 0;
        this.currentPlayerTurn = 1;
        this.complete = false;
        this.winner = "";
        this.statusMessage = "Relic Hunt started! Player 1 goes first.";
    }

    public int getPlayer1X() 
    {
        return player1X;
    }

    public void setPlayer1X(int player1X) 
    {
        this.player1X = player1X;
    }

    public int getPlayer1Y() 
    {
        return player1Y;
    }

    public void setPlayer1Y(int player1Y)
    {
        this.player1Y = player1Y;
    }

    public int getPlayer2X() 
    {
        return player2X;
    }

    public void setPlayer2X(int player2X) 
    {
        this.player2X = player2X;
    }

    public int getPlayer2Y() 
    {
        return player2Y;
    }

    public void setPlayer2Y(int player2Y) 
    {
        this.player2Y = player2Y;
    }

    public int getPlayer1Score() 
    {
        return player1Score;
    }

    public void addPlayer1Score() 
    {
        this.player1Score = this.player1Score + 1;
    }

    public int getPlayer2Score() 
    {
        return player2Score;
    }

    public void addPlayer2Score() 
    {
        this.player2Score = this.player2Score + 1;
    }

    public int getCurrentPlayerTurn() 
    {
        return currentPlayerTurn;
    }

    public void setCurrentPlayerTurn(int currentPlayerTurn) 
    {
        this.currentPlayerTurn = currentPlayerTurn;
    }

    public boolean isComplete() 
    {
        return complete;
    }

    public void setComplete(boolean complete) 
    {
        this.complete = complete;
    }

    public String getWinner() 
    {
        return winner;
    }

    public void setWinner(String winner) 
    {
        this.winner = winner;
    }

    public String getStatusMessage() 
    {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) 
    {
        this.statusMessage = statusMessage;
    }
}
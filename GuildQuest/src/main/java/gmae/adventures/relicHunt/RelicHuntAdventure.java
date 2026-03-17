package gmae.adventures.relicHunt;

import java.util.HashSet;
import java.util.Set;

import gmae.api.GameState;
import gmae.api.MiniAdventure;
import gmae.api.PlayerInput;
import gmae.api.TurnResult;
import gmae.profile.PlayerProfile;

public class RelicHuntAdventure implements MiniAdventure 
{

    private PlayerProfile player1;
    private PlayerProfile player2;

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

    private final int width = 5;
    private final int height = 5;

    private Set<String> relicPositions;

    @Override
    public void initialize(PlayerProfile p1, PlayerProfile p2) 
    {
        this.player1 = p1;
        this.player2 = p2;

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

        this.relicPositions = new HashSet<String>();
        relicPositions.add("1,1");
        relicPositions.add("2,3");
        relicPositions.add("3,1");
    }

    @Override
    public TurnResult advanceTurn(PlayerInput input) 
    {
        if (complete) {
            return new TurnResult("The game is already over.", getState(), true);
        }

        if (input.getPlayerId() != currentPlayerTurn) 
            {
            return new TurnResult("It is not Player " + input.getPlayerId() + "'s turn.", getState(), false);
        }

        moveCurrentPlayer(input.getAction());
        collectRelicIfPresent();

        if (player1Score >= 2) 
            {
            complete = true;
            winner = player1.getCharacterName();
            statusMessage = winner + " wins Relic Hunt!";
        } 
        else if (player2Score >= 2) 
        {
            complete = true;
            winner = player2.getCharacterName();
            statusMessage = winner + " wins Relic Hunt!";
        } 
        else if (relicPositions.isEmpty()) 
        {
            complete = true;

            if (player1Score > player2Score) 
            {
                winner = player1.getCharacterName();
                statusMessage = winner + " wins Relic Hunt!";
            } 
            else if (player2Score > player1Score) 
            {
                winner = player2.getCharacterName();
                statusMessage = winner + " wins Relic Hunt!";
            } 
            else 
            {
                winner = "Tie";
                statusMessage = "The game ended in a tie.";
            }
        } 
        else 
        {
            if (currentPlayerTurn == 1) 
            {
                currentPlayerTurn = 2;
            } 
            else 
            {
                currentPlayerTurn = 1;
            }

            statusMessage = "Turn complete. Player " + currentPlayerTurn + "'s turn.";
        }

        return new TurnResult(statusMessage, getState(), complete);
    }

    private void moveCurrentPlayer(PlayerInput.Action action) 
    {
        if (currentPlayerTurn == 1) 
        {
            if (action == PlayerInput.Action.MOVE_UP && player1Y > 0) 
            {
                player1Y--;
            } 
            else if (action == PlayerInput.Action.MOVE_DOWN && player1Y < height - 1) 
            {
                player1Y++;
            } 
            else if (action == PlayerInput.Action.MOVE_LEFT && player1X > 0) 
            {
                player1X--;
            } 
            else if (action == PlayerInput.Action.MOVE_RIGHT && player1X < width - 1) 
            {
                player1X++;
            }
        } 
        else 
        {
            if (action == PlayerInput.Action.MOVE_UP && player2Y > 0) 
            {
                player2Y--;
            } 
            else if (action == PlayerInput.Action.MOVE_DOWN && player2Y < height - 1) 
            {
                player2Y++;
            } 
            else if (action == PlayerInput.Action.MOVE_LEFT && player2X > 0) 
            {
                player2X--;
            } 
            else if (action == PlayerInput.Action.MOVE_RIGHT && player2X < width - 1) 
            {
                player2X++;
            }
        }
    }

    private void collectRelicIfPresent() 
    {
        String currentPosition;

        if (currentPlayerTurn == 1) 
        {
            currentPosition = player1X + "," + player1Y;

            if (relicPositions.contains(currentPosition)) 
            {
                relicPositions.remove(currentPosition);
                player1Score++;
                statusMessage = player1.getCharacterName() + " found a relic!";
            }
        } 
        else 
        {
            currentPosition = player2X + "," + player2Y;

            if (relicPositions.contains(currentPosition)) 
            {
                relicPositions.remove(currentPosition);
                player2Score++;
                statusMessage = player2.getCharacterName() + " found a relic!";
            }
        }
    }

    @Override
    public GameState getState() 
    {
        return new GameState(
                buildBoardDisplay(),
                player1Score,
                currentPlayerTurn,
                player2Score,
                statusMessage
        );
    }

    @Override
    public boolean isComplete() 
    {
        return complete;
    }

    @Override
    public String getWinner() 
    {
        return winner;
    }

    @Override
    public void reset() 
    {
        if (player1 != null && player2 != null) 
        {
            initialize(player1, player2);
        }
    }

    private String buildBoardDisplay() 
    {
        StringBuilder board = new StringBuilder();

        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < width; x++) 
            {
                if (player1X == x && player1Y == y && player2X == x && player2Y == y) 
                {
                    board.append("B ");
                } 
                else if (player1X == x && player1Y == y) 
                {
                    board.append("1 ");
                } 
                else if (player2X == x && player2Y == y) 
                {
                    board.append("2 ");
                } 
                else if (relicPositions.contains(x + "," + y)) 
                {
                    board.append("R ");
                } 
                else 
                {
                    board.append(". ");
                }
            }
            board.append("\n");
        }

        return board.toString();
    }
}

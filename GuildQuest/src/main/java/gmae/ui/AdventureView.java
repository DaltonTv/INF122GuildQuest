package gmae.ui;

import java.util.Scanner;

import gmae.api.GameState;
import gmae.api.PlayerInput;

public class AdventureView {

    public void showState(GameState state) {
        System.out.println();
        System.out.println("----------- Adventure State -----------");
        System.out.println(state.getBoardDisplay());
        System.out.println("Player 1 Score: " + state.getPlayer1Score());
        System.out.println("Player 2 Score: " + state.getPlayer2Score());
        System.out.println("Current Turn: Player " + state.getCurrentPlayerTurn());
        System.out.println("Status: " + state.getStatusMessage());
        System.out.println("---------------------------------------");
        System.out.println();
    }

    public PlayerInput getPlayerInput(Scanner scanner, int playerId) {
        System.out.println("Player " + playerId + " turn.");
        System.out.println("Enter move: W = up, S = down, A = left, D = right");
        System.out.print("Your move: ");

        String raw = scanner.nextLine().trim();
        return PlayerInput.fromString(playerId, raw);
    }

    public void showTurnResult(String message) {
        System.out.println(message);
        System.out.println();
    }

    public void showWinner(String winner) {
        System.out.println("Game Over!");
        System.out.println("Winner: " + winner);
        System.out.println();
    }
}
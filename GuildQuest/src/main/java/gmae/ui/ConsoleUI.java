package gmae.ui;

import java.util.List;
import java.util.Scanner;

import gmae.api.GuildQuestFacade;
import gmae.api.MiniAdventure;
import gmae.api.PlayerInput;
import gmae.api.TurnResult;
import gmae.profile.PlayerProfile;

public class ConsoleUI {

    private GuildQuestFacade facade;
    private Scanner scanner;
    private MainMenuView mainMenuView;
    private ProfileSetupView profileSetupView;
    private AdventureView adventureView;

    public ConsoleUI() {
        this.facade = new GuildQuestFacade();
        this.scanner = new Scanner(System.in);
        this.mainMenuView = new MainMenuView();
        this.profileSetupView = new ProfileSetupView();
        this.adventureView = new AdventureView();
    }

    public void run() {
        mainMenuView.showWelcome();

        PlayerProfile player1 = profileSetupView.createPlayerProfile(scanner, 1);
        PlayerProfile player2 = profileSetupView.createPlayerProfile(scanner, 2);

        facade.startSession(player1, player2);

        boolean running = true;

        while (running) {
            List<String> adventures = facade.getAvailableAdventures();
            mainMenuView.showAdventureMenu(adventures);

            System.out.print("Choose an adventure: ");
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                running = false;
            }
            else 
            {
                int choice = parseNumber(input);

                if (choice < 1 || choice > adventures.size()) 
                {
                    mainMenuView.showInvalidChoice();
                }
                else 
                {
                    String selectedAdventure = adventures.get(choice - 1);
                    launchAndPlayAdventure(selectedAdventure);
                }
            }
        }

        facade.endSession();
        System.out.println("Thanks for playing GuildQuest!");
    }

    private void launchAndPlayAdventure(String adventureType) {
        facade.launchAdventure(adventureType);

        MiniAdventure adventure = facade.getActiveAdventure();

        if (adventure == null) {
            System.out.println("Could not launch adventure.");
            System.out.println();
            return;
        }

        while (!adventure.isComplete()) {
            adventureView.showState(adventure.getState());

            int currentPlayer = adventure.getState().getCurrentPlayerTurn();
            PlayerInput input = adventureView.getPlayerInput(scanner, currentPlayer);

            TurnResult result = adventure.advanceTurn(input);
            adventureView.showTurnResult(result.getMessage());
        }

        adventureView.showState(adventure.getState());
        adventureView.showWinner(adventure.getWinner());
    }

    private int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
}
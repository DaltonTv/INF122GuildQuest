package gmae.ui;

import java.util.List;
import java.util.Scanner;

import gmae.api.GuildQuestFacade;
import gmae.api.MiniAdventure;
import gmae.api.PlayerInput;
import gmae.api.TurnResult;
import gmae.profile.PlayerProfile;
import gmae.profile.ProfileManager;
import java.io.IOException;

public class ConsoleUI {

    private GuildQuestFacade facade;
    private Scanner scanner;
    private MainMenuView mainMenuView;
    private ProfileSetupView profileSetupView;
    private AdventureView adventureView;
    private PlayerProfile p1;
    private PlayerProfile p2;

    public ConsoleUI() {
        this.facade = new GuildQuestFacade();
        this.scanner = new Scanner(System.in);
        this.mainMenuView = new MainMenuView();
        this.profileSetupView = new ProfileSetupView();
        this.adventureView = new AdventureView();
    }

    public void run() {
        mainMenuView.showWelcome();

        p1 = profileSetupView.createPlayerProfile(scanner, 1);
        p2 = profileSetupView.createPlayerProfile(scanner, 2);

        facade.startSession(p1, p2);

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
            System.out.println("Could not launch adventure.\n");
            return;
        }

        adventureView.showState(adventure.getState());

        while (!adventure.isComplete()) {
            int currentPlayer = adventure.getState().getCurrentPlayerTurn();
            PlayerInput input = adventureView.getPlayerInput(scanner, currentPlayer);
            TurnResult result = adventure.advanceTurn(input);
            adventureView.showTurnResult(result.getMessage());
            adventureView.showState(adventure.getState());
        }

        adventureView.showWinner(adventure.getWinner());
        recordAndSave(adventureType, adventure.getWinner(), p1, p2);
    }

    private void recordAndSave(String adventureType, String winner,
                               PlayerProfile p1, PlayerProfile p2) {
        if (winner == null || winner.contains("Nobody")) {
            p1.recordLoss(adventureType);
            p2.recordLoss(adventureType);
        }
        else if (winner.contains("&")) {
            p1.recordWin(adventureType);
            p2.recordWin(adventureType);
        }
        else if (winner.equals(p1.getCharacterName())) {
            p1.recordWin(adventureType);
            p2.recordLoss(adventureType);
        }
        else {
            p2.recordWin(adventureType);
            p1.recordLoss(adventureType);
        }

        ProfileManager profileManager = new ProfileManager();
        try {
            profileManager.saveProfile(p1);
            profileManager.saveProfile(p2);
            System.out.println("Profiles saved.");
        } catch (IOException e) {
            System.out.println("Warning: could not save profiles - " + e.getMessage());
        }
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
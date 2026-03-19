package gmae.ui;

import java.util.List;

public class MainMenuView {

    public void showWelcome() {
        System.out.println("==================================");
        System.out.println(" GuildQuest Mini-Adventure Engine ");
        System.out.println("==================================");
        System.out.println();
    }

    public void showAdventureMenu(List<String> adventureTypes) {
        System.out.println("Available Adventures:");

        int i = 0;
        while (i < adventureTypes.size()) {
            System.out.println((i + 1) + ") " + adventureTypes.get(i));
            i = i + 1;
        }

        System.out.println("0) Exit");
        System.out.println();
    }

    public void showInvalidChoice() {
        System.out.println("Invalid choice. Please try again.");
        System.out.println();
    }
}
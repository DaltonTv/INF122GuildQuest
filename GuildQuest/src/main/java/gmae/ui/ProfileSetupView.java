package gmae.ui;

import java.util.Scanner;

import gmae.core.enums.CharacterClass;
import gmae.profile.PlayerProfile;

public class ProfileSetupView {

    public PlayerProfile createPlayerProfile(Scanner scanner, int playerNumber) {
        System.out.println("Set up Player " + playerNumber + ":");

        System.out.print("Enter character name: ");
        String name = scanner.nextLine().trim();

        CharacterClass chosenClass = chooseCharacterClass(scanner);

        System.out.println("Created Player " + playerNumber + ": " + name + " (" + chosenClass + ")");
        System.out.println();

        return new PlayerProfile(name, chosenClass);
    }

    private CharacterClass chooseCharacterClass(Scanner scanner) {
        CharacterClass[] classes = CharacterClass.values();

        System.out.println("Choose a class:");
        int i = 0;
        while (i < classes.length) {
            System.out.println((i + 1) + ") " + classes[i]);
            i = i + 1;
        }

        while (true) {
            System.out.print("Enter class number: ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                if (choice >= 1 && choice <= classes.length) {
                    return classes[choice - 1];
                }
            }
            catch (NumberFormatException e) {
            }

            System.out.println("Invalid class choice. Please try again.");
        }
    }
}
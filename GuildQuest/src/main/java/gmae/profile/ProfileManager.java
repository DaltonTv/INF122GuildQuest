package gmae.profile;

import gmae.core.enums.CharacterClass;

import java.io.*;

public class ProfileManager {

    private static final String SAVE_DIR = "profiles/";

    public void saveProfile(PlayerProfile profile) throws IOException {
        new File(SAVE_DIR).mkdirs();
        String filename = SAVE_DIR + profile.getCharacterName() + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(profile.getCharacterName());
            writer.println(profile.getCharacterClass());
            writer.println(profile.getTotalWins());
            writer.println(profile.getTotalLosses());
            for(String entry : profile.getQuestHistory()) {
                writer.println("HISTORY:" + entry);
            }
            for(AchievementRecord a : profile.getAchievements()) {
                writer.println("ACHIEVEMENT:" + a.getName());
            }
        }
    }

    public PlayerProfile loadProfile(String characterName) throws IOException {
        String filename = SAVE_DIR + characterName + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String name = reader.readLine();
            CharacterClass cls = CharacterClass.valueOf(reader.readLine());
            int wins = Integer.parseInt(reader.readLine());
            int loses = Integer.parseInt(reader.readLine());
            PlayerProfile profile = new PlayerProfile(name, cls);
            profile.setCharacterClass(CharacterClass.valueOf(reader.readLine()));
            profile.setTotalWins(wins);
            profile.setTotalLosses(loses);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("HISTORY:")) {
                    profile.getQuestHistory().add(line.substring(8));
                }
                else if (line.startsWith("ACHIEVEMENT:")) {
                    profile.getAchievements().add(new AchievementRecord(line.substring(12)));
                }
            }
            return profile;
        }
    }

    public boolean profileExists(String characterName) {
        return new File(SAVE_DIR + characterName + ".txt").exists();
    }
}

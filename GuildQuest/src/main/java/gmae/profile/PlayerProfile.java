package gmae.profile;

import gmae.core.entity.InventoryItem;
import gmae.core.enums.CharacterClass;

import java.util.ArrayList;
import java.util.List;

public class PlayerProfile {
    private String characterName;
    private CharacterClass characterClass;
    private List<InventoryItem> inventory;
    private List<String> questHistory;
    private List<AchievementRecord> achievements;
    private int totalWins;
    private int totalLosses;

    public PlayerProfile(String characterName, CharacterClass characterClass) {
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.inventory = new ArrayList<>();
        this.questHistory = new ArrayList<>();
        this.achievements = new ArrayList<>();
        this.totalWins = 0;
        this.totalLosses = 0;
    }

    public void recordWin(String adventureName) {
        this.totalWins++;
        questHistory.add("Won: " + adventureName);
    }
    public void recordLoss(String adventureName) {
        this.totalLosses++;
        questHistory.add("Loss: " + adventureName);
    }
    public void addAchievement(AchievementRecord record) {
        if (!achievements.contains(record)) {
            achievements.add(record);
        }
    }

    public String getCharacterName() {
        return characterName;
    }
    public CharacterClass getCharacterClass() {
        return characterClass;
    }
    public List<InventoryItem> getInventory() {
        return inventory;
    }
    public List<String> getQuestHistory() {
        return questHistory;
    }
    public List<AchievementRecord> getAchievements() {
        return achievements;
    }
    public int getTotalWins() {
        return totalWins;
    }
    public int getTotalLosses() {
        return totalLosses;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }
    public void addQuestEvent(String campaingName, String eventTitle) {
        this.questHistory.add(campaingName + ": " + eventTitle);
    }
    public void addInventoryItem(InventoryItem item) {
        this.inventory.add(item);
    }
}

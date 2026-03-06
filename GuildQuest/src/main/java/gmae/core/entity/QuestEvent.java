package gmae.core.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class QuestEvent {
    private UUID eventId;
    private String name;
    private Realm realm;
    private ArrayList<Character> participatingCharacters;
    private HashMap<Character, ArrayList<InventoryItem>> inventoryChanges;

    public QuestEvent(String name, Realm realm, ArrayList<Character> participatingCharacters,
                      HashMap<Character, ArrayList<InventoryItem>> inventoryChanges) {
        this.eventId = UUID.randomUUID();
        updateName(name);
        updateRealm(realm);
        this.participatingCharacters = new ArrayList<>();
        for(Character c : participatingCharacters) {
            addCharacter(c);
        }
        this.inventoryChanges = new HashMap<>();
        this.inventoryChanges.putAll(inventoryChanges);
    }

    public UUID getEventId() {
        return eventId;
    }
    public String getName() {
        return name;
    }
    public Realm getRealm() {
        return realm;
    }
    public ArrayList<Character> getParticipatingCharacters() {
        return participatingCharacters;
    }
    public HashMap<Character, ArrayList<InventoryItem>> getInventoryChanges() {
        return inventoryChanges;
    }

    public void updateName(String newName) {
        name = newName;
    }
    public void updateRealm(Realm newRealm) {
        realm = newRealm;
    }
    public void addCharacter(Character character) {
        participatingCharacters.add(character);
    }
    public void removeCharacter(Character character) {
        participatingCharacters.remove(character);
    }


    public void grantItem(Character character, InventoryItem item) {
        int index = participatingCharacters.indexOf(character);
        if(index >= 0) {
            participatingCharacters.get(index).addItem(item);
            if(inventoryChanges.containsKey(character)) {
                inventoryChanges.get(character).add(item);
            }
            else  {
                ArrayList<InventoryItem> items = new ArrayList<>();
                items.add(item);
                inventoryChanges.put(character, items);
            }
        }
        else {
            System.out.println("Character " + character.getName() + " doesn't exist");
        }
    }
    public void removeItem(Character character, InventoryItem item) {
        int index = participatingCharacters.indexOf(character);
        if(index >= 0) {
            participatingCharacters.get(index).removeItem(item);
            if(inventoryChanges.containsKey(character)) {
                inventoryChanges.get(character).remove(item);
            }
            else  {
                ArrayList<InventoryItem> items = new ArrayList<>();
                items.add(item);
                inventoryChanges.put(character, items);
            }
        }
        else {
            System.out.println("Character " + character.getName() + " doesn't exist");
        }
    }

}

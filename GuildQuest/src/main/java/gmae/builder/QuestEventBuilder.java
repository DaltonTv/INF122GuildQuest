package gmae.builder;

import gmae.core.entity.Character;
import gmae.core.entity.InventoryItem;
import gmae.core.entity.QuestEvent;
import gmae.core.entity.Realm;
import gmae.core.entity.WorldTime;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * QuestEventBuilder builds a QuestEvent with mandatory fields
 * Can use optional fields if needed
 * Mandatory fields: name, startTime, realm
 * Optional fields: endTime
 * Could be empty: participatingCharacters, inventoryChanges (grant/remove items)
 */
public class QuestEventBuilder {
    // ── Mandatory fields ──────
    private String name;
    private WorldTime startTime;
    private Realm realm;

    // ── Optional fields ───────────────────────────────────────────────────────
    private WorldTime endTime;
    private ArrayList<Character> participatingCharacters;
    private HashMap<Character, ArrayList<InventoryItem>> inventoryChanges;

    public QuestEventBuilder() {
        this.participatingCharacters = new ArrayList<>();
        this.inventoryChanges = new HashMap<>();
    }

    public QuestEventBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public QuestEventBuilder setStartTime(WorldTime startTime) {
        this.startTime = startTime;
        return this;
    }
    public QuestEventBuilder setEndTime(WorldTime endTime) {
        this.endTime = endTime;
        return this;
    }
    public QuestEventBuilder setRealm(Realm realm) {
        this.realm = realm;
        return this;
    }
    public QuestEventBuilder addParticipant(Character character) {
        this.participatingCharacters.add(character);
        return this;
    }
    public QuestEventBuilder setInventoryChanges(
            HashMap<Character, ArrayList<InventoryItem>> inventoryChanges) {
        this.inventoryChanges = new HashMap<>(inventoryChanges);
        return this;
    }

    public QuestEvent build() {
        // Collect any missing mandatory fields
        ArrayList<String> missing = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            missing.add("name");
        }
        if (startTime == null) {
            missing.add("startTime");
        }
        if (realm == null) {
            missing.add("realm");
        }
 
        if (!missing.isEmpty()) {
            throw new IllegalStateException(
                "QuestEvent missing required fields: " + String.join(", ", missing)
            );
        }

        QuestEvent questEvent = new QuestEvent(
            name,
            realm,
            startTime,
            participatingCharacters,
            inventoryChanges
        );

        if (endTime != null) {
            questEvent.setEndTime(endTime);
        }

        return questEvent;
    }
}

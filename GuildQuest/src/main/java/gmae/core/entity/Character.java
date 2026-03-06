package gmae.core.entity;

import gmae.core.enums.CharacterClass;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Character {
    private UUID characterId;
    private String name;
    private CharacterClass characterClass;
    private GridPosition gridPosition;
    private int level;
    private ArrayList<InventoryItem> inventory;

    public Character(String name, CharacterClass characterClass,
                     GridPosition gridPosition, ArrayList<InventoryItem> inventory)
    {
        this.characterId = UUID.randomUUID();
        this.name = name;
        this.characterClass = characterClass;
        this.level = 1;
        this.gridPosition = gridPosition;
        this.inventory = new ArrayList<>();
        for(InventoryItem item : inventory) {
            this.inventory.add(item);
        }
    }

    public UUID getCharacterId()
    {
        return characterId;
    }
    public String getName() {
        return name;
    }
    public CharacterClass getCharacterClass()
    {
        return characterClass;
    }
    public GridPosition getGridPosition()
    {
        return gridPosition;
    }
    public int getLevel()
    {
        return level;
    }
    public List<InventoryItem> getInventory()
    {
        return inventory;
    }

    public void levelUp()
    {
        level++;
    }
    public void addItem(InventoryItem item)
    {
        inventory.add(item);
    }
    public void removeItem(InventoryItem item)
    {
        inventory.remove(item);
    }
    public void updatePosition(int x, int y) {
        gridPosition.x += x;
        gridPosition.y += y;
    }

    private class GridPosition {
        private int x;
        private int y;
        public GridPosition(int x, int y) {
            this.x = x;
        }
    }
}

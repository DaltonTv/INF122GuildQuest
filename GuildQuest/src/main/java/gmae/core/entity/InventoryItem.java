package gmae.core.entity;

import gmae.core.enums.ItemType;
import java.util.UUID;

public class InventoryItem {
    private UUID itemId;
    private String name;
    private String rarity;
    private ItemType type;
    private String description;

    public InventoryItem(String name, String rarity, ItemType type, String description) {
        this.itemId = UUID.randomUUID();
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.description = description;
    }

    public UUID getItemId() {return itemId;}
    public String getName() {return name;}
    public String getRarity() {return rarity;}
    public ItemType getType() {return type;}
    public String getDescription() {return description;}

    public void setName(String name) {this.name = name;}
    public void setRarity(String rarity) {this.rarity = rarity;}
    public void setType(ItemType type) {this.type = type;}
    public void setDescription(String description) {this.description = description;}
}

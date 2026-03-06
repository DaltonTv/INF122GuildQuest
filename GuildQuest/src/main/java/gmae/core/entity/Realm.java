package gmae.core.entity;

import java.util.UUID;

public class Realm {
    private UUID realmId;
    private String name;
    private String description;
    private int timeOffset;
    private int[][] realmGrid;

    public Realm(String name, String descriptor, int timeOffset,
                 int gridHeight, int gridWidth) {
        this.realmId = UUID.randomUUID();
        this.name = name;
        this.description = descriptor;
        this.timeOffset = timeOffset;
        this.realmGrid = new int[gridHeight][gridWidth];
    }

    public UUID getRealmId() {
        return realmId;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getTimeOffset() {
        return timeOffset;
    }
    public int getGridHeight() {
        return realmGrid.length;
    }
    public int getGridWidth() {
        return realmGrid[0].length;
    }
    public int[][] getRealmGrid() {
        return realmGrid;
    }
}

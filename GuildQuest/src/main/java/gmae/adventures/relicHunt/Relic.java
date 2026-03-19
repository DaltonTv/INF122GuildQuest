package gmae.adventures.relicHunt;

import gmae.core.entity.GridPosition;

public class Relic 
{
    private String name;
    private GridPosition position;
    private boolean collected;

    public Relic(String name, int x, int y) 
    {
        this.name = name;
        this.position = new GridPosition(x, y);
        this.collected = false;
    }

    public String getName() 
    {
        return name;
    }

    public GridPosition getPosition() 
    {
        return position;
    }

    public boolean isCollected() 
    {
        return collected;
    }

    public void collect() 
    {
        this.collected = true;
    }
}
package gmae.adventures.relicHunt;

import java.util.ArrayList;
import java.util.List;

public class RelicHuntMap 
{
    private int width;
    private int height;
    private List<Relic> relics;

    public RelicHuntMap(int width, int height) 
    {
        this.width = width;
        this.height = height;
        this.relics = new ArrayList<Relic>();
    }

    public int getWidth() 
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }

    public List<Relic> getRelics() 
    {
        return relics;
    }

    public void addRelic(Relic relic) 
    {
        relics.add(relic);
    }

    public boolean isInsideMap(int x, int y) 
    {
        if (x < 0 || x >= width) {
            return false;
        }

        if (y < 0 || y >= height) {
            return false;
        }

        return true;
    }

    public Relic getRelicAt(int x, int y) 
    {
        int i = 0;
        while (i < relics.size()) 
        {
            Relic relic = relics.get(i);

            if (!relic.isCollected() && relic.getPosition().getX() == x && relic.getPosition().getY() == y) 
            {
                return relic;
            }

            i = i + 1;
        }

        return null;
    }

    public int getRemainingRelicCount() 
    {
        int count = 0;

        int i = 0;
        while (i < relics.size()) 
        {
            if (!relics.get(i).isCollected()) 
            {
                count = count + 1;
            }
            i = i + 1;
        }

        return count;
    }

    public String buildBoardDisplay(int player1X, int player1Y, int player2X, int player2Y) {
        StringBuilder board = new StringBuilder();

        int y = 0;
        while (y < height) 
        {
            int x = 0;
            while (x < width) 
            {

                if (player1X == x && player1Y == y && player2X == x && player2Y == y) 
                {
                    board.append("B ");
                }
                else if (player1X == x && player1Y == y) 
                {
                    board.append("1 ");
                }
                else if (player2X == x && player2Y == y) 
                {
                    board.append("2 ");
                }
                else if (getRelicAt(x, y) != null) 
                {
                    board.append("R ");
                }
                else 
                {
                    board.append(". ");
                }

                x = x + 1;
            }

            board.append("\n");
            y = y + 1;
        }

        return board.toString();
    }
}
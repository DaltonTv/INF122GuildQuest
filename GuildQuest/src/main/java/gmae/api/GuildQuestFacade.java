package gmae.api;

import java.util.List;

import gmae.engine.GuildQuestManager;
import gmae.engine.SessionStateStore;
import gmae.profile.PlayerProfile;

public class GuildQuestFacade 
{
    private GuildQuestManager manager;
    private SessionStateStore sessionStore;

    public GuildQuestFacade() 
    {
        this.manager = GuildQuestManager.getInstance();
        this.sessionStore = SessionStateStore.getInstance();
    }

    public void startSession(PlayerProfile player1, PlayerProfile player2) 
    {
        manager.startSession(player1, player2);
    }

    public void launchAdventure(String adventureType) 
    {
        manager.launchAdventure(adventureType);
    }

    public List<String> getAvailableAdventures() 
    {
        return manager.getAvailableAdventures();
    }

    public MiniAdventure getActiveAdventure() 
    {
        return sessionStore.getActiveAdventure();
    }

    public boolean isSessionActive() 
    {
        return manager.isSessionActive();
    }

    public void endSession() 
    {
        manager.endSession();
    }
}
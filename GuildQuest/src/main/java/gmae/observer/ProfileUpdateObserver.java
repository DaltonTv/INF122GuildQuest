package gmae.observer;

import gmae.profile.PlayerProfile;

public class ProfileUpdateObserver implements GameObserver {

    private final PlayerProfile profile;

    public ProfileUpdateObserver(PlayerProfile profile) {
        this.profile = profile;
    }

    @Override
    public void onGameEvent(String campaignName, String eventTitle) {
        profile.addQuestEvent(campaignName, eventTitle);
        System.out.println("[Profile Update] " + profile.getCharacterName()
                + " — event recorded: " + eventTitle);
    }
}
package gmae.observer;

public interface GameObserver {
    void onGameEvent(String campaignName, String eventTitle);
}
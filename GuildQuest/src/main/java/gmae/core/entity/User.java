package gmae.core.entity;

import jdk.jfr.EventSettings;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private final UUID userId;
    private String username;
    private ArrayList<Campaign> ownedCampaigns;

    public User(String username) {
        this.userId = UUID.randomUUID();
        this.username = username;
        this.ownedCampaigns = new ArrayList<>();
    }

    public UUID getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public ArrayList<Campaign> getOwnedCampaigns() {
        return ownedCampaigns;
    }

    public void addCampaign(Campaign campaign) {
        if(campaign != null && !ownedCampaigns.contains(campaign)) {
            ownedCampaigns.add(campaign);
        }
    }
    public void removeCampaign(Campaign campaign) {
        if(ownedCampaigns.contains(campaign)) {
            ownedCampaigns.remove(campaign);
        }
        else {
            System.out.println("User: " + username + " does not own campaign: " + campaign.getName());
        }
    }
    public boolean ownsCampaign(Campaign campaign) {
        return ownedCampaigns.contains(campaign);
    }
}

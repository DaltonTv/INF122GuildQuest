package gmae.core.entity;

import java.util.ArrayList;
import java.util.UUID;

public class Campaign {
    private UUID campaignId;
    private String name;
    private boolean isPublic;
    private boolean isArchived;
    private User ownerUser;
    private ArrayList<QuestEvent> questEvents;

    public Campaign(String name, boolean isPublic, User owner, ArrayList<QuestEvent> events) {
        this.campaignId = UUID.randomUUID();
        this.name = name;
        this.isPublic = isPublic;
        this.ownerUser = owner;
        this.questEvents = new ArrayList<>();
        this.questEvents.addAll(events);
    }

    public UUID getCampaignId() {
        return campaignId;
    }
    public String getName() {
        return name;
    }
    public boolean isPublic() {
        return isPublic;
    }
    public boolean isArchived() {
        return isArchived;
    }
    public User getOwnerUser() {
        return ownerUser;
    }
    public ArrayList<QuestEvent> getQuestEvents() {
        return questEvents;
    }

    public void addQuestEvent(QuestEvent questEvent) {
        this.questEvents.add(questEvent);
    }
    public void removeQuestEvent(QuestEvent questEvent) {
        UUID id = questEvent.getEventId();
        questEvents.removeIf(q -> q.getEventId().equals(id));
    }

    public void toggleVisibility() {
        isPublic = !isPublic;
    }
    public void archiveCampaign() {
        isArchived = true;
    }
}

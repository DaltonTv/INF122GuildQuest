package gmae.adventures.timedRaid;

import gmae.builder.QuestEventBuilder;
import gmae.core.entity.InventoryItem;
import gmae.core.entity.QuestEvent;
import gmae.core.entity.Realm;
import gmae.core.entity.WorldTime;

public class RaidObjective {
    private final String label;
    private final int x;
    private final int y;
    private final InventoryItem reward;
    private final QuestEvent questEvent;
    private boolean complete;

    private RaidObjective(String label, int x, int y,
                          InventoryItem reward, QuestEvent questEvent) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.reward = reward;
        this.questEvent = questEvent;
        this.complete = false;
    }

    public String getLabel() {
        return label;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public InventoryItem getReward() {
        return reward;
    }
    public QuestEvent getQuestEvent() {
        return questEvent;
    }
    public boolean isComplete() {
        return complete;
    }

    public void markComplete() {
        this.complete = true;
    }

    @Override
    public String toString() {
        String status = complete ? "[DONE]" : "[     ]";
        return status + " " + label + " @ (" + x + ", " + y + ")";
    }

    public static class Builder {
        private final String label;
        private final int x;
        private final int y;

        private Realm realm;
        private WorldTime startTime;
        private WorldTime endTime;

        private InventoryItem reward;

        public Builder(String label, int x, int y) {
            this.label = label;
            this.x = x;
            this.y = y;
        }

        public Builder realm(Realm realm) {
            this.realm = realm;
            return this;
        }

        public Builder startTime(WorldTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(WorldTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder reward(InventoryItem reward) {
            this.reward = reward;
            return this;
        }

        public RaidObjective build() {
            QuestEventBuilder questBuilder = new QuestEventBuilder()
                    .setName(label)
                    .setRealm(realm)
                    .setStartTime(startTime);

            if (endTime != null) {
                questBuilder.setEndTime(endTime);
            }

            QuestEvent questEvent = questBuilder.build();

            return new RaidObjective(label, x, y, reward, questEvent);
        }
    }
}

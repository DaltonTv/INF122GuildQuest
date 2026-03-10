package gmae.profile;

public class AchievementRecord {
    private final String name;
    private final String unlockConditions;

    public AchievementRecord(String name, String unlockConditions) {
        this.name = name;
        this.unlockConditions = unlockConditions;
    }
    public AchievementRecord(String name) {
        this(name,"");
    }

    public String getName() {
        return name;
    }
    public String getUnlockConditions() {
        return unlockConditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AchievementRecord)) return false;
        return name.equals(((AchievementRecord) o).name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

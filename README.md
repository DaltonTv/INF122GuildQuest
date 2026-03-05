# INF122GuildQuest

**Contributors:**  
Dalton Denis  
Partha Khundrakpam  
Sahir Sebastian Saldana  
Timothy Huy Tran

**Project Structure:**

```
INF122GuildQuest/
    ├──ContributorPrioCode/
    │   ├──Dalton/
    │   │   └──DaltonGQCode.txt
    │   ├──Partha/
    │   │   └──ParthaGQCode.txt
    │   ├──Sahir/
    │   │   └──SahirGQCode.txt
    │   ├──Tim/
    │   │   └──TimGQCode.txt
    │   └──combinedCode.txt
    ├──ContributorPriorUML/
    │   ├──Dalton/
    │   ├──Partha/
    │   ├──Sahir/
    │   └──Tim/
    └──GuildQuest/
        ├── .mvn/
        ├── pom.xml
        ├── README.md
        └──src/main/java/gmae/
            ├── core/                               # Shared domain entities (from Dalton & Partha)
            │   ├── entity/
            │   │   ├── Character.java
            │   │   ├── InventoryItem.java
            │   │   ├── QuestEvent.java
            │   │   ├── Realm.java
            │   │   ├── User.java
            │   │   ├── Campaign.java
            │   │   └── WorldTime.java
            │   ├── enums/
            │   │   ├── PermissionLevel.java
            │   │   ├── CharacterClass.java
            │   │   └── Theme.java
            │   └── time/
            │       ├── WorldClock.java             # Singleton (Partha)
            │       ├── TimeDisplayStrategy.java    # Strategy interface (Tim)
            │       ├── WorldOnlyStrategy.java
            │       ├── LocalOnlyStrategy.java
            │       └── BothTimeStrategy.java
            ├── engine/                             # GMAE session engine (Partha)
            │   ├── GuildQuestManager.java          # Singleton session manager
            │   ├── MiniAdventureFactory.java       # Abstract factory (Partha)
            │   ├── StandardAdventureFactory.java
            │   └── AdventureRegistry.java          # Registers available adventures
            ├── api/                                # The defined GMAE interface layer
            │   ├── MiniAdventure.java              # <<interface>> all adventures implement
            │   ├── GameState.java                  # Snapshot of current adventure state
            │   ├── PlayerInput.java                # Encapsulates a player's action
            │   ├── TurnResult.java                 # Result returned after advanceTurn()
            │   └── GuildQuestFacade.java           # Facade over engine internals (Sahir)
            ├── profile/                            # Player profiles (Dalton)
            │   ├── PlayerProfile.java              # Name, class, inventory, history
            │   ├── ProfileManager.java             # Load/save profiles
            │   └── AchievementRecord.java
            ├── ui/                                 # Presentation layer (Sahir + shared)
            │   ├── ConsoleUI.java                  # Main console loop, two-player input
            │   ├── MainMenuView.java               # Displays adventure menu
            │   ├── ProfileSetupView.java           # Profile creation on first launch
            │   └── AdventureView.java              # In-game display during play
            ├── builder/                            # Builder pattern (Tim)
            │   └── QuestEventBuilder.java
            ├── observer/                           # Observer pattern (Partha)
            │   ├── GameObserver.java               # <<interface>>
            │   └── ProfileUpdateObserver.java      # Updates profile on events
            └── adventures/                         # Mini-adventures (Sahir + Tim)
                ├── relicHunt/                      # Mini-Adventure #1 (Sahir)
                │   ├── RelicHuntAdventure.java     # implements MiniAdventure
                │   ├── RelicHuntState.java
                │   ├── RelicHuntMap.java
                │   └── Relic.java
                └── timedRaid/                      # Mini-Adventure #2 (Tim)
                    ├── TimedRaidAdventure.java     # implements MiniAdventure
                    ├── TimedRaidState.java
                    ├── RaidObjective.java
                    └── RaidTimer.java
```
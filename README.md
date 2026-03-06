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
        ├──.mvn/
        ├──pom.xml
        ├──README.md
        └──src/main/java/gmae/
            ├──adventures/                         # Mini-adventures (Sahir + Tim)
            │   ├──relicHunt/                      # Mini-Adventure #1 (Sahir)
            │   │   ├──RelicHuntAdventure.java     # implements MiniAdventure
            │   │   ├──RelicHuntState.java
            │   │   ├──RelicHuntMap.java
            │   │   └──Relic.java
            │   └──timedRaid/                      # Mini-Adventure #2 (Tim)
            │       ├──RaidObjective.java
            │       ├──RaidTimer.java
            │       ├──TimedRaidAdventure.java     # implements MiniAdventure
            │       └──TimedRaidState.java
            ├──api/                                # The defined GMAE interface layer
            │   ├──GameState.java                  # Snapshot of current adventure state
            │   ├──GuildQuestFacade.java           # Facade over engine internals (Sahir)
            │   ├──MiniAdventure.java              # <<interface>> all adventures implement
            │   ├──PlayerInput.java                # Encapsulates a player's action
            │   └──TurnResult.java                 # Result returned after advanceTurn()
            ├──builder/                            # Builder pattern (Tim)
            │   └──QuestEventBuilder.java
            ├──core/                               # Shared domain entities (from Dalton & Partha)
            │   ├──entity/
            │   │   ├──Campaign.java
            │   │   ├──Character.java
            │   │   ├──InventoryItem.java
            │   │   ├──QuestEvent.java
            │   │   ├──Realm.java
            │   │   ├──User.java
            │   │   └──WorldTime.java
            │   ├──enums/
            │   │   ├──CharacterClass.java
            │   │   ├──PermissionLevel.java
            │   │   └──Theme.java
            │   └──time/
            │       ├──BothTimeStrategy.java
            │       ├──LocalOnlyStrategy.java
            │       ├──TimeDisplayStrategy.java    # Strategy interface (Tim)
            │       ├──WorldClock.java             # Singleton (Partha)
            │       └──WorldOnlyStrategy.java
            ├──engine/                             # GMAE session engine (Partha)
            │   ├──AdventureRegistry.java          # Registers available adventures
            │   ├──GuildQuestManager.java          # Singleton session manager
            │   ├──MiniAdventureFactory.java       # Abstract factory (Partha)
            │   └──StandardAdventureFactory.java
            ├──observer/                           # Observer pattern (Partha)
            │   ├──GameObserver.java               # <<interface>>
            │   └──ProfileUpdateObserver.java      # Updates profile on events
            ├──profile/                            # Player profiles (Dalton)
            │   ├──AchievementRecord.java
            │   ├──PlayerProfile.java              # Name, class, inventory, history
            │   └──ProfileManager.java             # Load/save profiles
            └──ui/                                 # Presentation layer (Sahir + shared)
                ├──AdventureView.java              # In-game display during play
                ├──ConsoleUI.java                  # Main console loop, two-player input
                ├──MainMenuView.java               # Displays adventure menu
                └──ProfileSetupView.java           # Profile creation on first launch
```
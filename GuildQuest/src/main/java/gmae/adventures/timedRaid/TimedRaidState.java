package gmae.adventures.timedRaid;

import gmae.core.entity.GridPosition;
import gmae.profile.PlayerProfile;

import java.util.List;

public class TimedRaidState {
    //Player Positions
    private GridPosition p1Position;
    private GridPosition p2Position;

    //Objectives
    private final List<RaidObjective> objectives;
    private int objectivesCompleted;

    //Timer
    private final RaidTimer timer;

    //Turn/Completion
    private int currentPlayerTurn;  //1 or 2
    private boolean complete;
    private boolean success;

    //Status
    private String statusMessage;

    public TimedRaidState(List<RaidObjective> objectives, int timeLimitMinutes) {
        this.objectives = objectives;
        this.objectivesCompleted = 0;
        this.timer = new RaidTimer(timeLimitMinutes);
        this.p1Position = new GridPosition(0, 0);
        this.p2Position = new GridPosition(4, 4);
        this.currentPlayerTurn = 1;
        this.complete = false;
        this.success = false;
        this.statusMessage = "Timed Raid started! Complete all objectives before time runs out!";
    }

    //Player positions
    public GridPosition getP1Position() {
        return p1Position;
    }
    public GridPosition getP2Position() {
        return p2Position;
    }
    public void setP1Position(GridPosition position) {
        this.p1Position = position;
    }
    public void setP2Position(GridPosition position) {
        this.p2Position = position;
    }
    public GridPosition getCurrentPlayerPosition() {
        return currentPlayerTurn == 1 ? p1Position : p2Position;
    }

    //Objectives
    public List<RaidObjective> getObjectives() {
        return objectives;
    }
    public int getObjectivesCompleted() {
        return objectivesCompleted;
    }
    public int getTotalObjectives() {
        return objectives.size();
    }
    /**
     * Checks if incomplete objective at given position.
     * If found, mark complete, increment counter, and return
     * **/
    public boolean tryCompleteObjectiveAt(int x, int y) {
        for (RaidObjective objective : objectives) {
            if (!objective.isComplete() && objective.getX() == x && objective.getY() == y) {
                objective.markComplete();
                objectivesCompleted++;
                return true;
            }
        }
        return false;
    }
    public boolean allObjectivesComplete() {
        return objectivesCompleted >= objectives.size();
    }

    //Timer
    public RaidTimer getTimer() {
        return timer;
    }
    //Advance time
    public void tickTimer() {
        timer.tick();
    }
    public boolean isTimedOut() {
        return timer.isExpired();
    }
    public String getFormattedTimeRemaining() {
        return timer.getFormattedTimeRemaining();
    }

    //Turn/completion
    public int getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }
    public void advanceTurn() {
        currentPlayerTurn = (currentPlayerTurn == 1) ? 2 : 1;
    }
    public boolean isComplete() {
        return complete;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    //Status
    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    //Board
    /**
     * Renders a 5x5 grid as ASCII string for AdventureView/GameState
     * Legend:
     * 1 = p1   2 = p2  B = both players
     * O = incomplete objective     . = empty tile
     * X = completed objective (shown in status, not on board)
     */
    public String buildBoardDisplay(PlayerProfile p1, PlayerProfile p2) {
        int width = 5;
        int height = 5;
        StringBuilder board = new StringBuilder();

        //Header
        board.append("Time remaining: ").append(getFormattedTimeRemaining()).append("\n");
        board.append("Objectives: ").append(objectivesCompleted)
                .append("/").append(objectives.size()).append("\n\n");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean isP1 = p1Position.getX() == x && p1Position.getY() == y;
                boolean isP2 = p2Position.getX() == x && p2Position.getY() == y;
                boolean isObjective = hasIncompleteObjectiveAt(x, y);

                if (isP1 && isP2) {
                    board.append("B ");
                }
                else if (isP1) {
                    board.append("1 ");
                }
                else if (isP2) {
                    board.append("2 ");
                }
                else if (isObjective) {
                    board.append("O ");
                }
                else {
                    board.append(". ");
                }
            }
            board.append("\n");
        }

        board.append("\n")
                .append(p1.getCharacterName()).append("(P1)   |   ")
                .append(p2.getCharacterName()).append("(P2)\n");

        return board.toString();
    }

    private boolean hasIncompleteObjectiveAt(int x, int y) {
        for (RaidObjective objective : objectives) {
            if (!objective.isComplete() && objective.getX() == x && objective.getY() == y) {
                return true;
            }
        }
        return false;
    }
}

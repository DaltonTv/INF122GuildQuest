package gmae.api;

public class PlayerInput {
    public enum Action {MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT, INTERACT, SKIP}

    private final int playerId;
    private final Action action;

    public PlayerInput(int playerId, Action action){
        this.playerId = playerId;
        this.action = action;
    }

    public int getPlayerId() {
        return playerId;
    }
    public Action getAction() {
        return action;
    }

    public static PlayerInput fronString(int playerId, String raw){
        return switch(raw.trim().toUpperCase()){
            case "W" -> new PlayerInput(playerId, Action.MOVE_UP);
            case "S" -> new PlayerInput(playerId, Action.MOVE_DOWN);
            case "A" -> new PlayerInput(playerId, Action.MOVE_LEFT);
            case "D" -> new PlayerInput(playerId, Action.MOVE_RIGHT);
            case "E" -> new PlayerInput(playerId, Action.INTERACT);
            default -> new PlayerInput(playerId, Action.SKIP);
        };
    }
}

package models;

public enum GameType {

    ARCADE("Arcade"),
    RPG("Role Playing Game"),
    FPS("First Person Shooter");

    private String type;

    GameType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

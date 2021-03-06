package com.example.petermartinez.abcrabble.Event;

/**
 * Created by petermartinez on 5/10/16.
 */
public class Event {

    public enum Type {CHAT, SCORE, MOVE, JUDGE, CLOCK, TILE};

    private int game;
    private long time;
    private long gameTime;
    private Type type;
    private int gameOrder;
    private int typeOrder;
    private String content;
    private int player;

    public Event(int game, long time, long gameTime, Type type, int gameOrder, int typeOrder, String content, int player) {
        this.game = game;
        this.time = time;
        this.gameTime = gameTime;
        this.type = type;
        this.gameOrder = gameOrder;
        this.typeOrder = typeOrder;
        this.content = content;
        this.player = player;
    }

    public static Event createZerothEvent(int game, long time){
        Event event = new Event(game, time, 0l, Type.JUDGE, 0, 0, "Commence Game", 0);
        return event;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getGameTime() {
        return gameTime;
    }

    public void setGameTime(long gameTime) {
        this.gameTime = gameTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getGameOrder() {
        return gameOrder;
    }

    public void setGameOrder(int gameOrder) {
        this.gameOrder = gameOrder;
    }

    public int getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(int typeOrder) {
        this.typeOrder = typeOrder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}

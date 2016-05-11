package com.example.petermartinez.abcrabble.Event;

/**
 * Created by petermartinez on 5/10/16.
 */
public class Event {

    public enum Type {CHAT, SCORE, MOVE, JUDGE, CLOCK, TILE};

    private int game;
    private long timeStamp;
    private Type type;
    private int order;
    private String content;
    private int player;

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

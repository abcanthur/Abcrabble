package com.example.petermartinez.abcrabble.Things;

import com.example.petermartinez.abcrabble.Event.Event;

/**
 * Created by petermartinez on 5/10/16.
 */
public class Game {
    public enum State {COMPLETED, QUIT, INACTIVE, PAUSED, ACTIVE}

    private int game;
    private long timeCreated;
    private String name;
    private int[] playerId;
    private String[] playerName;
    private int[] playerScore;
    private long[] playerTime;
    private char[] playerTiles;
    private int[] playerChallenge;
    private Event recentEvent;
    private int currPlayer;
    private State state;
    private boolean[] hasType; //CHAT, SCORE, MOVES, JUDGE, CLOCK, TILE
    private long clock;
    private int clockSettings;
    private char[] tilesLeft;
    private char[] tilesPlayed;
    private int dictionary; //switch to enum sometime

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int[] playerId) {
        this.playerId = playerId;
    }

    public String[] getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String[] playerName) {
        this.playerName = playerName;
    }

    public int[] getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int[] playerScore) {
        this.playerScore = playerScore;
    }

    public long[] getPlayerTime() {
        return playerTime;
    }

    public void setPlayerTime(long[] playerTime) {
        this.playerTime = playerTime;
    }

    public char[] getPlayerTiles() {
        return playerTiles;
    }

    public void setPlayerTiles(char[] playerTiles) {
        this.playerTiles = playerTiles;
    }

    public int[] getPlayerChallenge() {
        return playerChallenge;
    }

    public void setPlayerChallenge(int[] playerChallenge) {
        this.playerChallenge = playerChallenge;
    }

    public Event getRecentEvent() {
        return recentEvent;
    }

    public void setRecentEvent(Event recentEvent) {
        this.recentEvent = recentEvent;
    }

    public int getCurrPlayer() {
        return currPlayer;
    }

    public void setCurrPlayer(int currPlayer) {
        this.currPlayer = currPlayer;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean[] getHasType() {
        return hasType;
    }

    public void setHasType(boolean[] hasType) {
        this.hasType = hasType;
    }

    public long getClock() {
        return clock;
    }

    public void setClock(long clock) {
        this.clock = clock;
    }

    public int getClockSettings() {
        return clockSettings;
    }

    public void setClockSettings(int clockSettings) {
        this.clockSettings = clockSettings;
    }

    public char[] getTilesLeft() {
        return tilesLeft;
    }

    public void setTilesLeft(char[] tilesLeft) {
        this.tilesLeft = tilesLeft;
    }

    public char[] getTilesPlayed() {
        return tilesPlayed;
    }

    public void setTilesPlayed(char[] tilesPlayed) {
        this.tilesPlayed = tilesPlayed;
    }

    public int getDictionary() {
        return dictionary;
    }

    public void setDictionary(int dictionary) {
        this.dictionary = dictionary;
    }
}

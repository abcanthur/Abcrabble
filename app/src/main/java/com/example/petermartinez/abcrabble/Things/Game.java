package com.example.petermartinez.abcrabble.Things;

import com.example.petermartinez.abcrabble.Event.Event;

/**
 * Created by petermartinez on 5/10/16.
 */
public class Game {
    public enum State {COMPLETED, QUIT, INACTIVE, PAUSED, ACTIVE}

    private long game;
    private long timeCreated;
    private String name;
    private long[] playerId;
    private String[] playerName;
    private int[] playerScore;
    private long[] playerTime;
    private String[] playerTiles;
    private int[] playerChallenge;
    private Event recentEvent;
    private int currPlayer;
    private State state;
    private boolean[] hasType; //CHAT, SCORE, MOVES, JUDGE, CLOCK, TILE
    private long clock;
    private int[] clockSettings;
    private int moveOrder;
    private String tilesLeft;
    private String tilesPlayed;
    private int dictionary; //switch to enum sometime 1-TWL06

    public Game(long game, long timeCreated, String name, long[] playerId, String[] playerName, int[] playerScore, long[] playerTime, String[] playerTiles, int[] playerChallenge, Event recentEvent, int currPlayer, State state, boolean[] hasType, long clock, int[] clockSettings, int moveOrder, String tilesLeft, String tilesPlayed, int dictionary) {
        this.game = game;
        this.timeCreated = timeCreated;
        this.name = name;
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.playerTime = playerTime;
        this.playerTiles = playerTiles;
        this.playerChallenge = playerChallenge;
        this.recentEvent = recentEvent;
        this.currPlayer = currPlayer;
        this.state = state;
        this.hasType = hasType;
        this.clock = clock;
        this.clockSettings = clockSettings;
        this.moveOrder = moveOrder;
        this.tilesLeft = tilesLeft;
        this.tilesPlayed = tilesPlayed;
        this.dictionary = dictionary;
    }

    public Game() {
    }

    public static String gameSettingsToString(int[] settings){
        String dbSettings = String.valueOf(settings[0]);
        for(int i = 1; i < settings.length; i++){
            dbSettings = dbSettings + " " + String.valueOf(settings[i]);
        }
        return dbSettings;
    }

    public static int[] gameSettingsFromString(String settings){
        String[] stringSet = settings.split(" ");
        int[] sets = new int[stringSet.length];
        for(int i = 0; i < stringSet.length; i++){
            sets[i] = Integer.getInteger(stringSet[i]);
        }
        return sets;
    }

    public long getGame() {
        return game;
    }

    public void setGame(long game) {
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

    public long[] getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long[] playerId) {
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

    public String[] getPlayerTiles() {
        return playerTiles;
    }

    public void setPlayerTiles(String[] playerTiles) {
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

    public int[] getClockSettings() {
        return clockSettings;
    }

    public void setClockSettings(int[] clockSettings) {
        this.clockSettings = clockSettings;
    }

    public int getMoveOrder() {
        return moveOrder;
    }

    public void setMoveOrder(int moveOrder) {
        this.moveOrder = moveOrder;
    }

    public String getTilesLeft() {
        return tilesLeft;
    }

    public void setTilesLeft(String tilesLeft) {
        this.tilesLeft = tilesLeft;
    }

    public String getTilesPlayed() {
        return tilesPlayed;
    }

    public void setTilesPlayed(String tilesPlayed) {
        this.tilesPlayed = tilesPlayed;
    }

    public int getDictionary() {
        return dictionary;
    }

    public void setDictionary(int dictionary) {
        this.dictionary = dictionary;
    }
}

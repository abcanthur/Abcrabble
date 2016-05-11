package com.example.petermartinez.abcrabble.Things;

import com.example.petermartinez.abcrabble.Event.Event;

/**
 * Created by petermartinez on 5/10/16.
 */
public class Game {
    public enum State {COMPLETED, QUIT, INACTIVE, PAUSED, ACTIVE};

    private int game;
    private String[] playerNames;
    private int[] playerScores;
    private Event recentEvent;
    private int currPlayer;
    private State state;
    private boolean[] hasType; //CHAT, SCORE, MOVES, JUDGE, CLOCK, TILE
    private int dictionary; //switch to enum sometime

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public int[] getPlayerScores() {
        return playerScores;
    }

    public void setPlayerScores(int[] playerScores) {
        this.playerScores = playerScores;
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
}

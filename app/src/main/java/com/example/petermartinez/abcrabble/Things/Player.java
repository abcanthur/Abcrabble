package com.example.petermartinez.abcrabble.Things;

/**
 * Created by petermartinez on 5/10/16.
 */
public class Player {
    private String name; //player handle for this game
    private int id; //permanent id to tie player histories despite changing names
    private int game; //game id to tie this player to a specific game
    private int timeRemaining; //in seconds
    private int timeUsed; //in seconds
    private int score;
    private int challenges; //to see if they will play their next turn (can accumulate beyond 1)



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public int getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(int timeUsed) {
        this.timeUsed = timeUsed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getChallenges() {
        return challenges;
    }

    public void setChallenges(int challenges) {
        this.challenges = challenges;
    }
}

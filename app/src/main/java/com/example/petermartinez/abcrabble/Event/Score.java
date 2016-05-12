package com.example.petermartinez.abcrabble.Event;

/**
 * Created by petermartinez on 5/10/16.
 */
public class Score extends Event {
    public Score(int game, long time, long gameTime, Type type, int gameOrder, int typeOrder, String content, int player) {
        super(game, time, gameTime, type, gameOrder, typeOrder, content, player);
    }
}

package com.example.petermartinez.abcrabble.Event;

/**
 * Created by petermartinez on 5/10/16.
 */
public class Chat extends Event {
    public Chat(int game, long time, long gameTime, Type type, int gameOrder, int typeOrder, String content, int player) {
        super(game, time, gameTime, type, gameOrder, typeOrder, content, player);
    }
}

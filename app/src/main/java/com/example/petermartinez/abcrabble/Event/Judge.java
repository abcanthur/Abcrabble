package com.example.petermartinez.abcrabble.Event;

/**
 * Created by petermartinez on 5/10/16.
 */
public class Judge extends Event {
    public Judge(int game, long time, long gameTime, int gameOrder, int typeOrder, String content, int player) {
        super(game, time, gameTime, Type.JUDGE, gameOrder, typeOrder, content, player);
    }

    public static Judge createZerothJudge(int game, long time){
        Judge judge = new Judge(game, time, 0l, 0, 0, "Commence Game", 0);
        return judge;
    }
}

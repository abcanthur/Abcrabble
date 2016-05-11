package com.example.petermartinez.abcrabble.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petermartinez.abcrabble.Player.Player;
import com.example.petermartinez.abcrabble.R;

/**
 * Created by petermartinez on 5/5/16.
 */
public class ClockFrag extends Fragment {
    public static final String TIMEZERO = "currTimeStampZero";
    public static final String CLOCKACTIVITY = "isClockActivelyTiming";


    private static ImageView playerTurnsBargraph;
    private static TextView currTurnTime;
    private static TextView gameTotalTime;
    private static TextView primaryTime;
    private static TextView player0;
    private static TextView player1;
    private static TextView player2;
    private static TextView player3;
    private static String[] playerNames;
    private static int numberOfPlayers;
    private static int currPlayer = 0; //0-3, the index of the player whose turn it is
    private SharedPreferences clockPrefs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.clock_frag, container, false);

        Context context = getActivity();
        clockPrefs = context.getSharedPreferences(getString(R.string.clock_prefs_key), Context.MODE_PRIVATE);

        playerNames = new String[]{"Peter", "Vik", "DA", "Paul Julius"};
        numberOfPlayers = playerNames.length;

        setViews(v);

        primaryTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotatePlayers();
            }
        });

        return v;

    }

    private void setViews(View v){
        playerTurnsBargraph = (ImageView) v.findViewById(R.id.player_turns_bargraph);
        currTurnTime = (TextView) v.findViewById(R.id.curr_turn_time);
        gameTotalTime = (TextView) v.findViewById(R.id.game_total_time);
        primaryTime = (TextView) v.findViewById(R.id.primary_time);
        player0 = (TextView) v.findViewById(R.id.player_0_time);
        player1 = (TextView) v.findViewById(R.id.player_1_time);
        player2 = (TextView) v.findViewById(R.id.player_2_time);
        player3 = (TextView) v.findViewById(R.id.player_3_time);
        if(numberOfPlayers > 2) {
            player2.setVisibility(View.VISIBLE);
            if (numberOfPlayers == 4) {
                player3.setVisibility(View.VISIBLE);
            }
        }
        setCurrPlayer();
    }

    private void setCurrPlayer(){
        for(int i = 0; i < numberOfPlayers; i++){
            TextView thisPlayerView = getPlayerTextView(i);
//            Player thisPlayer = getPlayer();
            if(i != currPlayer){
                thisPlayerView.setText(playerNames[i] + "\n12:34");
                thisPlayerView.setPadding(2, 8, 2, 2);
                thisPlayerView.setTextAppearance(android.R.style.TextAppearance_DeviceDefault);
            } else {
                thisPlayerView.setText(playerNames[i]+ "\n");
                thisPlayerView.setPadding(2,2,2,2);
                thisPlayerView.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
            }
        }
    }

    private TextView getPlayerTextView(int i) {
        switch (i) {
            case 0:
                return player0;
            case 1:
                return player1;
            case 2:
                return player2;
            default:
                return player3;
        }
    }

//    private Player getPlayer(int i){  //must figure how to deal with players, I think instantiate in Record activity
//        switch (i) {
//            case 0:
//                return player0;
//            case 1:
//                return player1;
//            case 2:
//                return player2;
//            default:
//                return player3;
//        }
//    }

    private void setTimeZero(){
        SharedPreferences.Editor editor = clockPrefs.edit();
        editor.putLong(TIMEZERO, System.currentTimeMillis());
        editor.apply();
    }

    private long getTimeZero(){
        return clockPrefs.getLong(TIMEZERO, System.currentTimeMillis());
    }

    private void setClockActivity(int clockActive){ //0 inactive, 1 paused, 2 active
        SharedPreferences.Editor editor = clockPrefs.edit();
        editor.putInt(CLOCKACTIVITY, clockActive);
        editor.apply();
    }

    private long getClockActivity(){ //0 inactive, 1 paused, 2 active
        return clockPrefs.getInt(CLOCKACTIVITY, 0);
    }

    private void rotatePlayers(){
        currPlayer = (currPlayer + 1) % numberOfPlayers;
        setCurrPlayer();
    }
}

package com.example.petermartinez.abcrabble.Fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petermartinez.abcrabble.Activity.ActiveGameActivity;
import com.example.petermartinez.abcrabble.R;
import com.example.petermartinez.abcrabble.StopwatchService;

/**
 * Created by petermartinez on 5/5/16.
 */
public class ClockFrag extends Fragment {
    private static String TAG = "ClockFrag";
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
    private static TextView thisPlayerView;
    private static String[] playerNames;
    private static int numberOfPlayers;
    private static int currPlayer = 0; //0-3, the index of the player whose turn it is
    private SharedPreferences clockPrefs;
    private static long thisTime;

    // Timer to update the elapsedTime display
    private final long mFrequency = 100;    // milliseconds
    private final int TICK_WHAT = 2;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message m) {
            updateElapsedTime();
            sendMessageDelayed(Message.obtain(this, TICK_WHAT), mFrequency);
        }
    };

    // Connection to the background StopwatchService
    private StopwatchService m_stopwatchService;
    private ServiceConnection m_stopwatchServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            m_stopwatchService = ((StopwatchService.LocalBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            m_stopwatchService = null;
        }
    };

//    startService(new Intent(getActivity(),myPlayService.class));
//
//    with
//
//    getActivity().startService(new Intent(getActivity(),myPlayService.class));

    private void bindStopwatchService() {
        getActivity().bindService(new Intent(getActivity(), StopwatchService.class),
                m_stopwatchServiceConn, Context.BIND_AUTO_CREATE);
    }

    private void unbindStopwatchService() {
        if ( m_stopwatchService != null ) {
            getActivity().unbindService(m_stopwatchServiceConn);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.clock_frag, container, false);
        playerNames = new String[]{"Peter", "Vik", "DA", "Paul Julius"};
        numberOfPlayers = playerNames.length;
        setViews(v);

        Context context = getActivity();
        clockPrefs = context.getSharedPreferences(getString(R.string.clock_prefs_key), Context.MODE_PRIVATE);

        getActivity().startService(new Intent(getActivity(), StopwatchService.class));
        bindStopwatchService();
        mHandler.sendMessageDelayed(Message.obtain(mHandler, TICK_WHAT), mFrequency);



        primaryTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetClicked(v);
                rotatePlayers();
                onStartClicked(v);
            }
        });

        primaryTime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onPauseClicked(v);
                return false;
            }
        });

        return v;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindStopwatchService();
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
            thisPlayerView = getPlayerTextView(i);
             ActiveGameActivity.currPlayerTimeValue = getPlayerTime(i);
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

    private long getPlayerTime(int i){  //must figure how to deal with players, I think instantiate in Record activity
        switch (i) {
            case 0:
                return ActiveGameActivity.player0TimeValue;
            case 1:
                return ActiveGameActivity.player1TimeValue;
            case 2:
                return ActiveGameActivity.player2TimeValue;
            default:
                return ActiveGameActivity.player3TimeValue;
        }
    }

    private void setPlayerTime(int i, long time){  //must figure how to deal with players, I think instantiate in Record activity
        switch (i) {
            case 0:
                ActiveGameActivity.player0TimeValue = time;
                break;
            case 1:
                ActiveGameActivity.player1TimeValue = time;
                break;
            case 2:
                ActiveGameActivity.player2TimeValue = time;
                break;
            default:
                ActiveGameActivity.player3TimeValue = time;
                break;
        }
    }

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

    public void onStartClicked(View v) {
        Log.d(TAG, "start button clicked");
        m_stopwatchService.start();
    }

    public void onPauseClicked(View v) {
        Log.d(TAG, "pause button clicked");
        m_stopwatchService.pause();
    }

    public void onResetClicked(View v) {
        Log.d(TAG, "reset button clicked");
        m_stopwatchService.reset();
        ActiveGameActivity.gameTotalTimeValue += m_stopwatchService.getElapsedTime();
        setPlayerTime(currPlayer, ActiveGameActivity.currPlayerTimeValue - thisTime);
    }


    public void updateElapsedTime() {
        if ( m_stopwatchService != null )
            thisTime = m_stopwatchService.getElapsedTime();
        currTurnTime.setText(m_stopwatchService.getFormattedElapsedTime());
        gameTotalTime.setText(m_stopwatchService.formatElapsedTime(ActiveGameActivity.gameTotalTimeValue + thisTime));
        primaryTime.setText(m_stopwatchService.formatElapsedTime(ActiveGameActivity.currPlayerTimeValue - thisTime));
    }
}

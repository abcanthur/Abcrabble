package com.example.petermartinez.abcrabble.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.petermartinez.abcrabble.Fragments.ClockFrag;
import com.example.petermartinez.abcrabble.R;

/**
 * Created by petermartinez on 5/10/16.
 */
public class ActiveGameActivity extends AppCompatActivity {
    private FrameLayout fragContainerTimer;

    private android.support.v4.app.FragmentManager fragmentManager;
    private ClockFrag clockFrag;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;

//    public static long currTurnTimeValue = 0;
    public static long currPlayerTimeValue;
    public static long gameTotalTimeValue = 0;
    public static long player0TimeValue = 25 * 60 * 1000;
    public static long player1TimeValue = 25 * 60 * 1000;
    public static long player2TimeValue = 25 * 60 * 1000;
    public static long player3TimeValue = 25 * 60 * 1000;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_game);


        setViews();
        setFragment();




    }

    private void setViews(){
        fragContainerTimer = (FrameLayout) findViewById(R.id.frag_container_timer);
        fragmentManager = getSupportFragmentManager();
        clockFrag = new ClockFrag();
    }

    private void setFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag_container_timer, clockFrag);
        fragmentTransaction.commit();
    }

}

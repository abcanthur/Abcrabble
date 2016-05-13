package com.example.petermartinez.abcrabble.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petermartinez.abcrabble.Dictionary.Dictionary;
import com.example.petermartinez.abcrabble.Event.Judge;
import com.example.petermartinez.abcrabble.R;
import com.example.petermartinez.abcrabble.Things.Game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by petermartinez on 5/10/16.
 */
public class GameSetupActivity extends AppCompatActivity {

    private static EditText gameEnterName;
    private static EditText player0EnterName;
    private static EditText player1EnterName;
    private static EditText player2EnterName;
    private static EditText player3EnterName;
    private static Spinner spinnerTimingMode;
    private static Spinner spinnerStartTime;
    private static Spinner spinnerIncrement;
    private static TextView spinnerIncrementText;
    private static RelativeLayout minutesAndPenalties;
    private static Spinner spinnerPenaltyPerMin;
    private static TextView spinnerPenaltyPerMinText;
    private static Spinner spinnerDictionary;
    private static Button startGameFromSetup;

    private static int timingMode = 0;
    private static int timeForEachPlayer = 25;
    private static int timeIncrement = 10;
    private static int timePenalty = 10;

    private static ArrayList<String> timingModes;
    private static ArrayAdapter<String> timingModesAdapter;
    private static ArrayList<Integer> startTimes;
    private static ArrayAdapter<Integer> startTimesAdapter;
    private static ArrayList<Integer> increments;
    private static ArrayAdapter<Integer> incrementsAdapter;
    private static ArrayList<Integer> penalties;
    private static ArrayAdapter<Integer> penaltiesAdapter;
    private static ArrayList<String> dictionaries;
    private static ArrayAdapter<String> dictionariesAdapter;

    public static final SimpleDateFormat gameNameSdf = new SimpleDateFormat("E MMM d h:m a yyyy", Locale.US);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        setViews();
        setListeners();


}

    public void setViews(){
        gameEnterName = (EditText) findViewById(R.id.game_enter_name);

        player0EnterName = (EditText) findViewById(R.id.player_0_enter_name);
        player1EnterName = (EditText) findViewById(R.id.player_1_enter_name);
        player2EnterName = (EditText) findViewById(R.id.player_2_enter_name);
        player3EnterName = (EditText) findViewById(R.id.player_3_enter_name);

        timingModes = new ArrayList<String>();
        timingModes.add("CountUp");
        timingModes.add("CountDown");
        timingModes.add("Bronstein");
        timingModes.add("Fischer");
        timingModes.add("Hourglass");
        timingModesAdapter = new ArrayAdapter<String>(GameSetupActivity.this, R.layout.centered_spinner, timingModes) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position % 2 == 1) { // we're on an even row
                    view.setBackgroundColor(getColor(R.color.TWred));
                } else {
                    view.setBackgroundColor(getColor(R.color.DWpink));
                }
                return view;
            }
        };

        timingModesAdapter.setDropDownViewResource(R.layout.centered_spinner_dropdown);
        spinnerTimingMode = (Spinner) findViewById(R.id.spinner_timing_mode);
        spinnerTimingMode.setAdapter(timingModesAdapter);


        spinnerStartTime = (Spinner) findViewById(R.id.spinner_start_time);
        spinnerIncrement = (Spinner) findViewById(R.id.spinner_increment);
        spinnerIncrementText = (TextView) findViewById(R.id.spinner_increment_text);

        minutesAndPenalties = (RelativeLayout) findViewById(R.id.minutes_and_penalties);
        spinnerPenaltyPerMin = (Spinner) findViewById(R.id.spinner_penalty_per_min);
        spinnerPenaltyPerMinText = (TextView) findViewById(R.id.spinner_penalty_per_min_text);
        spinnerDictionary = (Spinner) findViewById(R.id.spinner_dictionary);

        startTimes = new ArrayList<Integer>();
        for(int i = 2; i < 90; i++){
            startTimes.add(i);
        }
        startTimesAdapter = new ArrayAdapter<Integer>(GameSetupActivity.this, R.layout.centered_spinner, startTimes){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position % 2 == 1) { // we're on an even row
                    view.setBackgroundColor(getColor(R.color.TLblue));
                } else {
                    view.setBackgroundColor(getColor(R.color.DLskyblue));
                }
                return view;
            }
        };
        startTimesAdapter.setDropDownViewResource(R.layout.centered_spinner_dropdown);
        spinnerStartTime = (Spinner) findViewById(R.id.spinner_start_time);
        spinnerStartTime.setAdapter(startTimesAdapter);
        spinnerStartTime.setSelection(23);


        increments = new ArrayList<Integer>();
        increments.add(5);
        increments.add(10);
        increments.add(15);
        increments.add(20);
        increments.add(30);
        increments.add(45);
        increments.add(60);
        increments.add(90);
        incrementsAdapter = new ArrayAdapter<Integer>(GameSetupActivity.this, R.layout.centered_spinner, increments){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position % 2 == 1) { // we're on an even row
                    view.setBackgroundColor(getColor(R.color.TLblue));
                } else {
                    view.setBackgroundColor(getColor(R.color.DLskyblue));
                }
                return view;
            }
        };
        incrementsAdapter.setDropDownViewResource(R.layout.centered_spinner_dropdown);
        spinnerIncrement = (Spinner) findViewById(R.id.spinner_increment);
        spinnerIncrement.setAdapter(incrementsAdapter);
        spinnerIncrement.setSelection(1);

        penalties = new ArrayList<Integer>();
        penalties.add(0);
        penalties.add(1);
        penalties.add(2);
        penalties.add(3);
        penalties.add(5);
        penalties.add(10);
        penalties.add(15);
        penalties.add(20);
        penalties.add(25);
        penaltiesAdapter = new ArrayAdapter<Integer>(GameSetupActivity.this, R.layout.centered_spinner, penalties){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position % 2 == 0) { // we're on an even row
                    view.setBackgroundColor(getColor(R.color.TWred));
                } else {
                    view.setBackgroundColor(getColor(R.color.DWpink));
                }
                return view;
            }
        };
        penaltiesAdapter.setDropDownViewResource(R.layout.centered_spinner_dropdown);
        spinnerPenaltyPerMin = (Spinner) findViewById(R.id.spinner_penalty_per_min);
        spinnerPenaltyPerMin.setAdapter(penaltiesAdapter);
        spinnerPenaltyPerMin.setSelection(5);

        dictionaries = new ArrayList<String>();
        dictionaries.add("TWL06");
        dictionaries.add("CSW");
        dictionaries.add("SOWPODS");
        dictionariesAdapter = new ArrayAdapter<String>(GameSetupActivity.this, R.layout.centered_spinner, dictionaries){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position % 2 == 1) { // we're on an even row
                    view.setBackgroundColor(getColor(R.color.TWred));
                } else {
                    view.setBackgroundColor(getColor(R.color.DWpink));
                }
                return view;
            }
        };
        dictionariesAdapter.setDropDownViewResource(R.layout.centered_spinner_dropdown);
        spinnerDictionary = (Spinner) findViewById(R.id.spinner_dictionary);
        spinnerDictionary.setAdapter(dictionariesAdapter);

        startGameFromSetup = (Button) findViewById(R.id.start_game_from_setup);
    }

    public void setListeners(){
        spinnerTimingMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timingMode = position;
                if(position > 0){
                    minutesAndPenalties.setVisibility(View.VISIBLE);
                    spinnerPenaltyPerMin.setVisibility(View.VISIBLE);
                    spinnerPenaltyPerMinText.setVisibility(View.VISIBLE);
                }
                if(position == 2 || position == 3){
                    spinnerIncrement.setVisibility(View.VISIBLE);
                    spinnerIncrementText.setVisibility(View.VISIBLE);
                } else {
                    spinnerIncrement.setVisibility(View.INVISIBLE);
                    spinnerIncrementText.setVisibility(View.INVISIBLE);
                }
                if(position == 4){
                    spinnerPenaltyPerMin.setVisibility(View.INVISIBLE);
                    spinnerPenaltyPerMinText.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                timingMode = 0;
            }
        });
        spinnerStartTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeForEachPlayer = startTimes.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerIncrement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeIncrement = increments.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                timeIncrement = 10;
            }
        });
        spinnerPenaltyPerMin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timePenalty = penalties.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                timePenalty = 10;
            }
        });

        startGameFromSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorCheckForm();
                createGameObject();
                startActiveGameActivity();
            }
        });
    }

    public void errorCheckForm(){
        if(player2EnterName.getText().toString().equals("") && player3EnterName.getText().toString().length() > 0){
            player2EnterName.setText(player3EnterName.getText().toString());
            player3EnterName.setText("");
            Toast.makeText(GameSetupActivity.this, "Player 4 swapped into Player 3", Toast.LENGTH_SHORT).show();
        }
    }

    private void createGameObject(){

        int gameId = -1;

        long time = System.currentTimeMillis();

        String gameName = gameEnterName.getText().toString();
        if(gameEnterName.getText().toString().equals("")) {gameName = gameNameSdf.format(new Date(time));}

        int numPlayers;
        if(player2EnterName.getText().toString().length() > 0) {
            if (player3EnterName.getText().toString().length() > 0) {
                numPlayers = 4;
            } else {
                numPlayers = 3;
            }
        } else {
            numPlayers = 2;
        }





        if(timingMode == 0){
            timeForEachPlayer = 0;
        }

        int[] playerId= new int[] {-1,-1};
        String[] playerName = new String[2];
        int[] playerScore = new int[] {0,0};
        long[] playerTime = new long[] {timeForEachPlayer, timeForEachPlayer};
        String[] playerTiles = new String[] {"",""};
        int[] playerChallenge = new int[] {0,0};
        if(numPlayers == 4) {
            playerId = new int[] {-1,-1,-1,-1};
            playerName = new String[4];
            playerScore = new int[] {0,0,0,0};
            playerTime = new long[] {timeForEachPlayer, timeForEachPlayer, timeForEachPlayer, timeForEachPlayer};
            playerTiles = new String[] {"","","",""};
            playerChallenge = new int[] {0,0,0,0};

            playerName[3] = player3EnterName.getText().toString();
            playerName[2] = player2EnterName.getText().toString();
            playerName[1] = player1EnterName.getText().toString();
            playerName[0] = player0EnterName.getText().toString();
        }

        if(numPlayers == 3){
            playerId = new int[] {-1,-1,-1};
            playerName = new String[3];
            playerScore = new int[] {0,0,0};
            playerTime = new long[] {timeForEachPlayer, timeForEachPlayer, timeForEachPlayer};
            playerTiles = new String[] {"","",""};
            playerChallenge = new int[] {0,0,0};

            playerName[2] = player2EnterName.getText().toString();
            playerName[1] = player1EnterName.getText().toString();
            playerName[0] = player0EnterName.getText().toString();
        }

        if(numPlayers < 3){
            playerId = new int[] {-1,-1};
            playerName = new String[2];
            playerScore = new int[] {0,0};
            playerTiles = new String[] {"",""};
            playerChallenge = new int[] {0,0};

            playerName[1] = player1EnterName.getText().toString();
            playerName[0] = player0EnterName.getText().toString();
        }

        int recentEvent = -1;

        int currPlayer = 0;

        Game.State state = Game.State.PAUSED;

        boolean[] hasType = new boolean[] {false, false, false, false, false, false};

        long clock = 0;

        int[] clockSettings = new int[] {timingMode, timeForEachPlayer, timeIncrement, timePenalty};

        String tilesLeft = Dictionary.tileAlphabetScrabble;

        String tilesPlayed = "";

        int dictionary = 1;

        Game game = new Game(gameId, time, gameName, playerId, playerName, playerScore, playerTime, playerTiles, playerChallenge,
                Judge.createZerothJudge(gameId, time), currPlayer, state, hasType, clock, clockSettings, tilesLeft, tilesPlayed, dictionary);
    }

    public int parsePickerString(String pick){
        String[] split = pick.split(" ");
        return Integer.getInteger(split[0]);
    }


        private void startActiveGameActivity(){ //calls active game activity
            Intent intent = new Intent(GameSetupActivity.this, ActiveGameActivity.class);
            Bundle bundle = new Bundle();
//                    bundle.putInt("theItemPosition", 0);
            intent.putExtras(bundle);
            startActivity(intent);
        }


}

package com.example.petermartinez.abcrabble.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
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
    private static Spinner spinnerPenaltyPerMin;
    private static Spinner spinnerDictionary;
    private static Button startGameFromSetup;

    private static int timingMode = 0;
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

    public static final SimpleDateFormat gameNameSdf = new SimpleDateFormat("E mmm d h:m a yyyy", Locale.US);



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
        timingModesAdapter = new ArrayAdapter<String>(GameSetupActivity.this, android.R.layout.simple_spinner_item, timingModes);
        timingModesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimingMode = (Spinner) findViewById(R.id.spinner_timing_mode);
        spinnerTimingMode.setAdapter(timingModesAdapter);


        spinnerStartTime = (Spinner) findViewById(R.id.spinner_start_time);
        spinnerIncrement = (Spinner) findViewById(R.id.spinner_increment);
        spinnerPenaltyPerMin = (Spinner) findViewById(R.id.spinner_penalty_per_min);
        spinnerDictionary = (Spinner) findViewById(R.id.spinner_dictionary);

        startTimes = new ArrayList<Integer>();
        for(int i = 2; i < 90; i++){
            startTimes.add(i);
        }
        startTimesAdapter = new ArrayAdapter<Integer>(GameSetupActivity.this, android.R.layout.simple_spinner_item, startTimes);
        startTimesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStartTime = (Spinner) findViewById(R.id.spinner_start_time);
        spinnerStartTime.setAdapter(startTimesAdapter);


        increments = new ArrayList<Integer>();
        increments.add(5);
        increments.add(10);
        increments.add(15);
        increments.add(20);
        increments.add(30);
        increments.add(45);
        increments.add(60);
        increments.add(90);
        incrementsAdapter = new ArrayAdapter<Integer>(GameSetupActivity.this, android.R.layout.simple_spinner_item, increments);
        incrementsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIncrement = (Spinner) findViewById(R.id.spinner_increment);
        spinnerIncrement.setAdapter(incrementsAdapter);

        penalties = new ArrayList<Integer>();
        penalties.add(1);
        penalties.add(2);
        penalties.add(3);
        penalties.add(5);
        penalties.add(10);
        penalties.add(15);
        penalties.add(20);
        penalties.add(25);
        penaltiesAdapter = new ArrayAdapter<Integer>(GameSetupActivity.this, android.R.layout.simple_spinner_item, penalties);
        penaltiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPenaltyPerMin = (Spinner) findViewById(R.id.spinner_penalty_per_min);
        spinnerPenaltyPerMin.setAdapter(penaltiesAdapter);

        dictionaries = new ArrayList<String>();
        dictionaries.add("TWL06");
        dictionaries.add("CSW");
        dictionaries.add("SOWPODS");
        dictionariesAdapter = new ArrayAdapter<String>(GameSetupActivity.this, android.R.layout.simple_spinner_dropdown_item, dictionaries);
        dictionariesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDictionary = (Spinner) findViewById(R.id.spinner_dictionary);
        spinnerDictionary.setAdapter(dictionariesAdapter);

        startGameFromSetup = (Button) findViewById(R.id.start_game_from_setup);
    }

    public void setListeners(){
        spinnerTimingMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timingMode = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                timingMode = 0;
            }
        });

        startworkingrighthere

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

    public void createGameObject(){

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

        long timeForEachPlayer = 0;
        if(timingMode > 0){
            timeForEachPlayer = Long.valueOf(pickerStartTime.getValue());
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

//        int[] clockSettings = new int[] {timingMode, parsePickerString(pickerStartTime.getValue()), parsePickerString(pickerIncrement.getValue()), parsePickerString(pickerPenaltyPerMin.getValue())};

        int[] clockSettings = new int[] {timingMode, pickerStartTime.getValue(), pickerIncrement.getValue(), pickerPenaltyPerMin.getValue()};

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

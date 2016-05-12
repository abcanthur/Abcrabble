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
    private static NumberPicker pickerStartTime;
    private static NumberPicker pickerIncrement;
    private static NumberPicker pickerPenaltyPerMin;
    private static NumberPicker pickerDictionary;
    private static Button startGameFromSetup;

    private static int timingMode = 0;
    private static ArrayList<String> timingModes;
    private static ArrayAdapter<String> timingModesAdapter;

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
        timingModesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timingModes);
        timingModesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimingMode.setAdapter(timingModesAdapter);
        spinnerTimingMode = (Spinner) findViewById(R.id.spinner_timing_mode);

        pickerStartTime = (NumberPicker) findViewById(R.id.picker_start_time);
        pickerIncrement = (NumberPicker) findViewById(R.id.picker_increment);
        pickerPenaltyPerMin = (NumberPicker) findViewById(R.id.picker_penalty_per_min);
        pickerDictionary = (NumberPicker) findViewById(R.id.picker_dictionary);

        pickerStartTime.setMinValue(1);
        pickerStartTime.setValue(25);

        String[] increments = new String[] {"5 seconds" ,"10 seconds","15 seconds","20 seconds","30 seconds","40 seconds","60 seconds","90 seconds"};
        pickerIncrement.setDisplayedValues(increments);
        pickerIncrement.setWrapSelectorWheel(true);

        String[] penalties = new String[] {"1 point", "2 points", "3 points", "5 points", "10 points", "15 points", "20 points", "25 points"};
        pickerPenaltyPerMin.setDisplayedValues(penalties);
        pickerPenaltyPerMin.setWrapSelectorWheel(true);

        String[] dictionaries = new String[] {"CSW", "TWL06", "SOWPODS"};
        pickerDictionary.setDisplayedValues(dictionaries);
        pickerDictionary.setWrapSelectorWheel(true);

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

        pickerStartTime.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

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

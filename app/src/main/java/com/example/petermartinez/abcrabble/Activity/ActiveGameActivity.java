package com.example.petermartinez.abcrabble.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.petermartinez.abcrabble.Fragments.ClockFrag;
import com.example.petermartinez.abcrabble.R;
import com.example.petermartinez.abcrabble.SQLiteDB.GameDB;
import com.example.petermartinez.abcrabble.Things.Game;

import java.util.List;

/**
 * Created by petermartinez on 5/10/16.
 */
public class ActiveGameActivity extends AppCompatActivity {
    private FrameLayout fragContainerTimer;

    private android.support.v4.app.FragmentManager fragmentManager;
    private ClockFrag clockFrag;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;

//    public static long currTurnTimeValue = 0;
    private static long activeGameId;
    private static boolean isFreshGame;
    private static GameDB gameDB;
//    private static PlayerDB playerDB;
//    private static EventDB eventdb;

    private static Game activeGame;
    public static long currPlayerTimeValue;
    public static long gameTotalTimeValue;
    public static long player0TimeValue;
    public static long player1TimeValue;
    public static long player2TimeValue;
    public static long player3TimeValue;

    private static String[] spokenText;
    public static final int SPEECH_REQUEST_CODE = 0;


    private static LinearLayout rowScore;
    private static LinearLayout rowWords;
    private static LinearLayout rowTiles;
    private static Button buttonScore;
    private static Button buttonWords;
    private static Button buttonTiles;
    private static Switch switchScore;
    private static Switch switchWords;
    private static Switch switchTiles;
    private static EditText editScore;
    private static EditText editWords;
    private static EditText editTiles;
    private static ImageButton speakScore;
    private static ImageButton speakWords;
    private static ImageButton speakTiles;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_game);
        spokenText = new String[3];
        gameDB = new GameDB(this);
//        Bundle extras = getIntent().getExtras();
//        gameIDprelim = extras.getLong(GameSQLiteHelper.COL_TIME_CREATED);
//        isFreshGame = extras.getBoolean("isFreshGame");
        SharedPreferences sharedPreferences = ActiveGameActivity.this.getPreferences(Context.MODE_PRIVATE);
        activeGameId = sharedPreferences.getLong("ActiveGameID", 0);
        isFreshGame = sharedPreferences.getBoolean("IsFreshGame", true);


        new GetGameObject().execute();



        setViews();
        setDataFromGame();
        setListeners();
        setFragment();




    }



    private void setViews(){
        rowScore = (LinearLayout) findViewById(R.id.row_score);
        rowWords = (LinearLayout) findViewById(R.id.row_words);
        rowTiles = (LinearLayout) findViewById(R.id.row_tiles);
        buttonScore = (Button) findViewById(R.id.button_score);
        buttonWords = (Button) findViewById(R.id.button_words);
        buttonTiles = (Button) findViewById(R.id.button_tiles);
        switchScore = (Switch) findViewById(R.id.switch_score);
        switchWords = (Switch) findViewById(R.id.switch_words);
        switchTiles = (Switch) findViewById(R.id.switch_tiles);
        editScore = (EditText) findViewById(R.id.edit_score);
        editWords = (EditText) findViewById(R.id.edit_words);
        editTiles = (EditText) findViewById(R.id.edit_tiles);
        speakScore = (ImageButton) findViewById(R.id.speak_score);
        speakWords = (ImageButton) findViewById(R.id.speak_words);
        speakTiles = (ImageButton) findViewById(R.id.speak_tiles);
        fragContainerTimer = (FrameLayout) findViewById(R.id.frag_container_timer);
        fragmentManager = getSupportFragmentManager();
        clockFrag = new ClockFrag();
    }

    private void setDataFromGame(){

    }

    private void setListeners(){
        switchScore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    rowScore.setVisibility(View.VISIBLE);
                    buttonScore.setClickable(true);
                } else {
                    rowScore.setVisibility(View.GONE);
                    buttonScore.setClickable(false);
                }
            }
        });

        switchWords.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    rowWords.setVisibility(View.VISIBLE);
                    buttonWords.setClickable(true);
                } else {
                    rowWords.setVisibility(View.GONE);
                    buttonWords.setClickable(false);
                }
            }
        });

        switchTiles.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    rowTiles.setVisibility(View.VISIBLE);
                    buttonTiles.setClickable(true);
                } else {
                    rowTiles.setVisibility(View.GONE);
                    buttonTiles.setClickable(false);
                }
            }
        });

        speakScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechButton(0);
            }
        });
        speakWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechButton(1);
            }
        });
        speakTiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechButton(2);
            }
        });

        buttonScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechButton(0);
            }
        });
        buttonWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechButton(1);
            }
        });
        buttonTiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechButton(2);
            }
        });
    }

    private class GetGameObject extends AsyncTask<Object, Object, Cursor> {
        GameDB gameDB = new GameDB(ActiveGameActivity.this);
        @Override
        protected Cursor doInBackground(Object... params) {
            // Pass the Row ID into GetOneNote function in
            // DatabaseConnector.java class
            gameDB.open();
            String[] query = new String[]{String.valueOf(activeGameId)};
            return gameDB.getGameById(query, isFreshGame);
        }
        @Override
        protected void onPostExecute(Cursor result) {
            super.onPostExecute(result);
            activeGame = gameDB.dumpCursorToGameObject(result);
            result.close();
            gameDB.close();
        }
    }

    private void setFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag_container_timer, clockFrag);
        fragmentTransaction.commit();
    }



    public void speechButton(int i){
        displaySpeechRecognizer();
        switch (i){
            case 0:
                editScore.setText(spokenText[0]);
                break;
            case 1:
                editWords.setText(spokenText[1]);
                break;
            case 2:
                editTiles.setText(spokenText[2]);
                break;

        }
        editScore.setText(spokenText[0]);
        editWords.setText(spokenText[1]);
        editTiles.setText(spokenText[2]);
    }

    public static boolean isRecognitionAvailable (Context context){

        return false;
    }



    // Create an intent that can start the Speech Recognizer activity
    public void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            spokenText[0] = results.get(0);
            spokenText[1] = results.get(1);
            spokenText[2] = results.get(2);
            Log.i("Speech: ", "onActivityResult: 0 " + spokenText[0]);
            Log.i("Speech: ", "onActivityResult: 1 " + spokenText[1]);
            Log.i("Speech: ", "onActivityResult: 2 " + spokenText[2]);
            // Do something with spokenText
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}

package com.example.petermartinez.abcrabble;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petermartinez.abcrabble.Activity.ActiveGameActivity;
import com.example.petermartinez.abcrabble.Activity.GameSetupActivity;
import com.example.petermartinez.abcrabble.Dictionary.Dictionary;
import com.example.petermartinez.abcrabble.Fragments.ClockFrag;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    implements GestureDetector.OnGestureListener

    private static EditText wordSearch;
    private static Button yesNo;
    private static ImageView yesNoV;
    private static TextView mainMessage;
    private FrameLayout fragContainerTimer;

    private android.support.v4.app.FragmentManager fragmentManager;
    private ClockFrag clockFrag;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;

    private static Firebase mFirebaseCurrentTextRef;

    private GestureDetectorCompat mDetector;

    private static boolean isFirstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isFirstRun){firstRun();}

        initFirebase();
        initFirebaseEventListener();


        setViews();
        setListeners();
//        mDetector = new GestureDetectorCompat(this,this);








    }

    private void setViews(){
        wordSearch = (EditText) findViewById(R.id.word_search);
        yesNo = (Button) findViewById(R.id.yes_no);
        yesNoV = (ImageView) findViewById(R.id.yes_noV);
        yesNoV.setImageDrawable(getDrawable(R.drawable.yes_no_rough));
        mainMessage = (TextView) findViewById(R.id.main_message);

    }

    private void setListeners(){
        yesNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordSearchCheckDict(wordSearch.getText().toString());


                        mFirebaseCurrentTextRef.setValue(wordSearch.getText().toString());


            }
        });

        yesNo.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                return false;
            }
        });

        yesNoV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameSetupActivity();
            }
        });

//        yesNo.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//
//                    return true;
//            }
//        });
    }

    private void startActiveGameActivity(){ //calls active game activity
        Intent intent = new Intent(MainActivity.this, ActiveGameActivity.class);
        Bundle bundle = new Bundle();
//                    bundle.putInt("theItemPosition", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void startGameSetupActivity(){ //calls game setup activity
        Intent intent = new Intent(MainActivity.this, GameSetupActivity.class);
        Bundle bundle = new Bundle();
//                    bundle.putInt("theItemPosition", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void initFirebase(){
        Firebase mFirebaseRef = new Firebase("https://jumbly.firebaseio.com/");
        mFirebaseCurrentTextRef = mFirebaseRef.child("currentText");

    }

    private void initFirebaseEventListener(){
        mFirebaseCurrentTextRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mainMessage.setText(text);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void wordSearchCheckDict(String word){
        boolean isWord = Dictionary.checkWordDict(word);
        if(isWord){
            mainMessage.setText("Yes, " + word.toUpperCase() + " is a word!");
        } else {
            mainMessage.setText("No, " + word.toUpperCase() + " is not a word.");
        }
        mainMessage.setVisibility(View.VISIBLE);
    }

    private void firstRun(){
        Dictionary.setList();
    }
}

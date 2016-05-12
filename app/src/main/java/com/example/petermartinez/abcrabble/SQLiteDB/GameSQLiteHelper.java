package com.example.petermartinez.abcrabble.SQLiteDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.petermartinez.abcrabble.Event.Event;

/**
 * Created by petermartinez on 5/11/16.
 */
public class GameSQLiteHelper extends SQLiteOpenHelper implements BaseColumns {
    private static final String TAG = "GAMESQLiteHelper";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ABCrab_DB";
    public static final String GAMES_TABLE_NAME = "GAMES";
    public static final String PLAYERS_TABLE_NAME = "PLAYERS";
    public static final String EVENTS_TABLE_NAME = "EVENTS";

    public static final String COL_GAME_ID = "_id";
    public static final String COL_TIME_CREATED = "TIME_CREATED";
    public static final String COL_GAME_NAME = "GAME_NAME";
    public static final String COL_PLAYER_0_ID = "PLAYER_0_ID";
    public static final String COL_PLAYER_1_ID = "PLAYER_1_ID";
    public static final String COL_PLAYER_2_ID = "PLAYER_2_ID";
    public static final String COL_PLAYER_3_ID = "PLAYER_3_ID";
    public static final String COL_RECENT_EVENT = "RECENT_EVENT";
    public static final String COL_CURR_PLAYER = "CURR_PLAYER";
    public static final String COL_STATE = "STATE";
    public static final String COL_HAS_TYPE = "HAS_TYPE";
    public static final String COL_CLOCK = "CLOCK";
    public static final String COL_CLOCK_SETTINGS = "CLOCK_SETTINGS";
    public static final String COL_TILES_LEFT = "TILES_LEFT";
    public static final String COL_TILES_PLAYED = "TILES_PLAYED";
    public static final String COL_DICTIONARY = "DICTIONARY";

    public static final String[] GAMES_COLUMNS = {COL_GAME_ID, COL_TIME_CREATED, COL_GAME_NAME, COL_PLAYER_0_ID, COL_PLAYER_1_ID, COL_PLAYER_2_ID, COL_PLAYER_3_ID,
            COL_RECENT_EVENT, COL_CURR_PLAYER,
            COL_STATE, COL_HAS_TYPE, COL_CLOCK, COL_CLOCK_SETTINGS, COL_TILES_LEFT , COL_TILES_PLAYED, COL_DICTIONARY};

    public static final String COL_ID = "_id";
    public static final String COL_PLAYER_NAME = "PLAYER_NAME";
    public static final String COL_PLAYER_GAME = "PLAYER_GAME";
    public static final String COL_PLAYER_CLOCK = "PLAYER_CLOCK";
    public static final String COL_SCORE = "SCORE";
    public static final String COL_TILES = "TILES";
    public static final String COL_CHALLENGE = "CHALLENGE";

    public static final String[] PLAYERS_COLUMNS = {COL_ID, COL_PLAYER_NAME, COL_PLAYER_GAME, COL_PLAYER_CLOCK, COL_SCORE, COL_TILES, COL_CHALLENGE};

    public static final String COL_GAME = "GAME";
    public static final String COL_TIME = "TIME";
    public static final String COL_GAME_CLOCK = "GAME_CLOCK";
    public static final String COL_TYPE = "TYPE";
    public static final String COL_GAME_ORDER = "GAME_ORDER";
    public static final String COL_TYPE_ORDER = "TYPE_ORDER";
    public static final String COL_CONTENT = "CONTENT";
    public static final String COL_PLAYER = "PLAYER";

    public static final String[] EVENTS_COLUMNS = {COL_GAME, COL_TIME, COL_GAME_CLOCK, COL_TYPE, COL_GAME_ORDER, COL_TYPE_ORDER, COL_CONTENT, COL_PLAYER};

    private static GameSQLiteHelper instance;

    private static final String CREATE_GAMES_TABLE =
            "CREATE TABLE " + GAMES_TABLE_NAME +
                    "(" +
                    COL_GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TIME_CREATED + " LONG, " +
                    COL_GAME_NAME + " TEXT, " +
                    COL_PLAYER_0_ID + " INTEGER, " +
                    COL_PLAYER_1_ID + " INTEGER, " +
                    COL_PLAYER_2_ID + " INTEGER, " +
                    COL_PLAYER_3_ID + " INTEGER, " +
                    COL_RECENT_EVENT + " INTEGER, " +
                    COL_CURR_PLAYER + " INTEGER, " +
                    COL_STATE + " TEXT, " +
                    COL_HAS_TYPE + " INTEGER, " +
                    COL_CLOCK + " LONG, " +
                    COL_CLOCK_SETTINGS + " INTEGER, " +
                    COL_TILES_LEFT + " TEXT, " +
                    COL_TILES_PLAYED + " TEXT, " +
                    COL_DICTIONARY + " TEXT )";


    private static final String CREATE_PLAYERS_TABLE =
            "CREATE TABLE " + PLAYERS_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_PLAYER_NAME + " TEXT, " +
                    COL_PLAYER_GAME + " INTEGER, " +
                    COL_PLAYER_CLOCK + " LONG, " +
                    COL_SCORE + " INTEGER, " +
                    COL_TILES + " TEXT, " +
                    COL_CHALLENGE + " INTEGER )";

    private static final String CREATE_EVENTS_TABLE =
            "CREATE TABLE " + EVENTS_TABLE_NAME +
                    "(" +
                    COL_GAME + " INTEGER, " +
                    COL_TIME + " LONG, " +
                    COL_GAME_CLOCK + " LONG, " +
                    COL_TYPE + " TEXT, " +
                    COL_GAME_ORDER + " INTEGER, " +
                    COL_TYPE_ORDER + " INTEGER, " +
                    COL_CONTENT + " TEXT, " +
                    COL_PLAYER + " INTEGER )";

    public static GameSQLiteHelper getInstance(Context context){
        if(instance == null){
            instance = new GameSQLiteHelper(context);
        }
        return instance;
    }

    public GameSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GAMES_TABLE);
        db.execSQL(CREATE_PLAYERS_TABLE);
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GAMES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PLAYERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);
        this.onCreate(db);
    }
}

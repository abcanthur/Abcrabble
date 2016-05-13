package com.example.petermartinez.abcrabble.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.SQLException;

import com.example.petermartinez.abcrabble.Event.Event;
import com.example.petermartinez.abcrabble.Things.Game;

/**
 * Created by petermartinez on 5/11/16.
 */
public class GameDB {

    private SQLiteDatabase DB;
    private GameSQLiteHelper gameSQLiteHelper;

    public GameDB(Context context) {
        gameSQLiteHelper = new GameSQLiteHelper(context);
    }



    public Cursor getAllGames() {
        Cursor cursor = DB.rawQuery("select * from " + GameSQLiteHelper.GAMES_TABLE_NAME + " ORDER BY " + GameSQLiteHelper.COL_TIME_CREATED + " DESC", null);

        return cursor;
    }

    public Cursor getGameById(String[] query){
        Cursor cursor = DB.query(GameSQLiteHelper.GAMES_TABLE_NAME, // a. table
                GameSQLiteHelper.GAMES_COLUMNS, // b. column names
                GameSQLiteHelper.COL_ID + " = ?", // c. selections
                query, // d. selections args
                null, // e. group by
                null, // f. having
                GameSQLiteHelper.COL_TIME_CREATED, // g. order by
                null); // h. limit
        return cursor;
    }

    public Cursor getPlayersByGame(String[] query) {
        Cursor cursor = DB.query(GameSQLiteHelper.PLAYERS_TABLE_NAME, // a. table
                GameSQLiteHelper.PLAYERS_COLUMNS, // b. column names
                GameSQLiteHelper.COL_PLAYER_GAME + " = ?", // c. selections
                query, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    public Cursor getEventsByGame(String[] query) {
//        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = DB.query(GameSQLiteHelper.EVENTS_TABLE_NAME, // a. table
                GameSQLiteHelper.EVENTS_COLUMNS, // b. column names
                GameSQLiteHelper.COL_GAME + " = ?", // c. selections
                query, // d. selections args
                null, // e. group by
                null, // f. having
                GameSQLiteHelper.COL_GAME_ORDER, // g. order by
                null); // h. limit
        return cursor;
    }

    // Open Database function
    public void open() throws SQLException {
        // Allow DB to be in writable mode
        DB = gameSQLiteHelper.getWritableDatabase();
    }

    // Close Database function
    public void close() {
        if (DB != null)
            DB.close();
    }

//    private int game;
//    private long timeCreated;
    private String name;
    private int[] playerId;
    private String[] playerName;
    private int[] playerScore;
    private long[] playerTime;
    private String[] playerTiles;
    private int[] playerChallenge;
    private Event recentEvent;
    private int currPlayer;
    private Game.State state;
    private boolean[] hasType; //CHAT, SCORE, MOVES, JUDGE, CLOCK, TILE
    private long clock;
    private int[] clockSettings;
    private int moveOrder;
    private String tilesLeft;
    private String tilesPlayed;
    private int dictionary; //switch to enum sometime 1-TWL06

    COL_GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
    COL_TIME_CREATED + " LONG, " +
    COL_GAME_NAME + " TEXT, " +
    COL_PLAYER_0_ID + " STRING, " +
    COL_PLAYER_1_ID + " STRING, " +
    COL_PLAYER_2_ID + " STRING, " +
    COL_PLAYER_3_ID + " STRING, " +

    COL_RECENT_EVENT + " INTEGER, " +
    COL_CURR_PLAYER + " INTEGER, " +
    COL_STATE + " TEXT, " +
    COL_HAS_TYPE + " INTEGER, " +
    COL_CLOCK + " LONG, " +
    COL_CLOCK_SETTINGS + " STRING, " +
    COL_MOVE_ORDER + " INTEGER, " +
    COL_TILES_LEFT + " TEXT, " +
    COL_TILES_PLAYED + " TEXT, " +
    COL_DICTIONARY + " TEXT )";


    GAMES_COLUMNS = {COL_GAME_ID, COL_TIME_CREATED, COL_GAME_NAME,
            COL_PLAYER_0_ID, COL_PLAYER_1_ID, COL_PLAYER_2_ID, COL_PLAYER_3_ID,
            COL_RECENT_EVENT, COL_CURR_PLAYER, COL_STATE, COL_HAS_TYPE,
            COL_CLOCK, COL_CLOCK_SETTINGS,
            COL_MOVE_ORDER, COL_TILES_LEFT , COL_TILES_PLAYED, COL_DICTIONARY};

        // Create Database function
        public void InsertGame(Game game) {
            ContentValues values = new ContentValues();
        values.put(GameSQLiteHelper.COL_TIME_CREATED, game.getTimeCreated());
            values.put(GameSQLiteHelper.COL_GAME_NAME, game.getName());
        values.put(GameSQLiteHelper.COL_PLAYER_0_ID, (game.getPlayerId()[0]));
        values.put(GameSQLiteHelper.COL_PLAYER_1_ID, (game.getPlayerId()[0]));
            values.put(GameSQLiteHelper.COL_PLAYER_0_NAME, (game.getPlayerName()[0]));
            values.put(GameSQLiteHelper.COL_PLAYER_1_NAME, (game.getPlayerName()[1]));
        if(game.getPlayerId().length > 2) {
            values.put(GameSQLiteHelper.COL_PLAYER_2_ID, (game.getPlayerId()[2]));
            values.put(GameSQLiteHelper.COL_PLAYER_2_NAME, (game.getPlayerName()[2]));
            if (game.getPlayerId().length == 4) {
                values.put(GameSQLiteHelper.COL_PLAYER_3_ID, (game.getPlayerId()[3]));
                values.put(GameSQLiteHelper.COL_PLAYER_3_NAME, (game.getPlayerName()[3]));
            }
        }
        values.put(GameSQLiteHelper.COL_RECENT_EVENT, game.getRecentEvent().getGameOrder()) ;
        values.put(GameSQLiteHelper.COL_CURR_PLAYER, game.getCurrPlayer()) ;
        values.put(GameSQLiteHelper.COL_STATE, game.getState().toString());
        values.put(GameSQLiteHelper.COL_HAS_TYPE, 0); //must implement a full hastype conversion, not necesssary now
        values.put(GameSQLiteHelper.COL_CLOCK, game.getClock());
        values.put(GameSQLiteHelper.COL_CLOCK_SETTINGS, Game.gameSettingsToString(game.getClockSettings()));
        values.put(GameSQLiteHelper.COL_MOVE_ORDER, ;
        values.put(GameSQLiteHelper.COL_TILES_LEFT ;
        values.put(GameSQLiteHelper.COL_TILES_PLAYED, ;
        values.put(GameSQLiteHelper.COL_DICTIONARY ;


                open();
        DB.insert(TABLE_NAME, null, newCon);
        close();
    }

    // emulate these functions once you need them, must insert events from record, players from setup

//
//    // Update Database function
//    public void UpdateNote(long id, String title, String note) {
//        ContentValues editCon = new ContentValues();
//        editCon.put(TITLE, title);
//        editCon.put(NOTE, note);
//
//        open();
//        DB.update(TABLE_NAME, editCon, ID + "=" + id, null);
//        close();
//    }
//
//    // Delete Database function
//    public void DeleteNote(long id) {
//        open();
//        DB.delete(TABLE_NAME, ID + "=" + id, null);
//        close();
//    }
//
//    // List all data function
//    public Cursor ListAllNotes() {
//        return DB.query(TABLE_NAME, new String[] { ID, TITLE }, null,
//                null, null, null, TITLE);
//    }
//
//    // Capture single data by ID
//    public Cursor GetOneNote(long id) {
//        return DB.query(TABLE_NAME, null, ID + "=" + id, null, null,
//                null, null);
//    }
}

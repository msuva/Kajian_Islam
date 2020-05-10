package com.android.example.wordlistsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordListOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION= 1;
    private static final String WORLD_LIST_TABLE = "word_entries";
    private static final String DATABASE_NAME = "wordlist";

    private static final String KEY_ID ="_id";
    private static final String KEY_WORD="word";

    private static final String[] COLUMS = {KEY_ID, KEY_WORD};
    private static final String WORD_LIST_TABLE_CREATE =
            "CREATE TABLE " + WORLD_LIST_TABLE + " (" +
            KEY_ID +" INTEGER PRIMARY KEY, " +
            KEY_WORD + " TEXT );";

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    public WordListOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WORD_LIST_TABLE_CREATE);
        fillDatabaseWithData(db);
    }

    private void fillDatabaseWithData(SQLiteDatabase db) {
        String[] words = {"Android", "Adapter", "ListView", "Async",
                        "Android Studio", "SQL", "Open", "data",
                        "viewholder", "performance", "OnClick"};

        ContentValues values = new ContentValues();
        for (int i = 0 ; i < words.length ; i++){
            values.put(KEY_WORD, words[i]);
            db.insert(WORLD_LIST_TABLE, null, values);
        }
    }

    public long insert(String word){
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, word);
        try{
            if(mWritableDB == null){
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(WORLD_LIST_TABLE, null, values);

        }catch (Exception e){

        }

        return newId;
    }

    public  int update(int id, String word){
        int mNumberOfRowsUpdate = -1;
        try {
            if(mWritableDB == null){
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_WORD, word);
            mNumberOfRowsUpdate = mWritableDB.update(WORLD_LIST_TABLE,values,
                    KEY_ID + " = ?",
                    new String[]{String.valueOf(id)});
        }catch (Exception e){

        }
        return mNumberOfRowsUpdate;
    }

    public WordItem query(int position){
        String query = "SELECT * FROM " + WORLD_LIST_TABLE + " ORDER BY "+
                KEY_WORD + " ASC " + " LIMIT " + position + ", 1";
        Cursor cursor = null;
        WordItem entry = new WordItem();

        try {
            if(mReadableDB == null){
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setmWord(cursor.getString(cursor.getColumnIndex(KEY_WORD)));
        }catch (Exception e){

        }finally {
            cursor.close();
            return entry;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + WORLD_LIST_TABLE);
        onCreate(db);
    }

    public long count() {
        if(mReadableDB == null){
            mReadableDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDB, WORLD_LIST_TABLE);
    }
}

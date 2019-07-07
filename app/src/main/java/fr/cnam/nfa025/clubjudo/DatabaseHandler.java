package fr.cnam.nfa025.clubjudo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;

import static fr.cnam.nfa025.clubjudo.Util.APP_TAG;

public class DatabaseHandler extends SQLiteOpenHelper implements Serializable {

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.i(APP_TAG, "DatabaseHandler: constructeur !");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(APP_TAG, "DatabaseHandler: onCreate !");
        db.execSQL(Util.TABLE_DROP);
        db.execSQL(Util.TABLE_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(APP_TAG, "DatabaseHandler: onUpgrade !");

        db.execSQL(Util.TABLE_DROP);
        onCreate(db);
    }

    public void setSQLCreate(String sqlCreateTable_p) {

        //this.JUDO_TABLE_DROP = sqlCreateTable_p;

    }


}


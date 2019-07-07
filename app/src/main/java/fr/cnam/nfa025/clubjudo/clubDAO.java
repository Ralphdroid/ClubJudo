package fr.cnam.nfa025.clubjudo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;


public class clubDAO {

    Context context;
    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;
    protected final static int VERSION = 1;

    clubDAO() {}

    clubDAO(Context context_p) {

        context = context_p;

        this.mHandler = new DatabaseHandler(context_p, Util.FILENAME, null, VERSION);
        SQLiteDatabase checkDB;
        //this.mHandler.onCreate(this.mHandler.getWritableDatabase());

        try {
            Log.i(Util.APP_TAG, "DAOBase: Test existence de la table (apres)");

            checkDB = SQLiteDatabase.openDatabase(context_p.getDatabasePath(Util.FILENAME).getPath(),
                    null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();


        } catch (SQLiteException e) {
            // base de données n'existe pas.
            Log.i(Util.APP_TAG, "DAOBase: Test existence de la table (apres): KO elle n'existe pas ! => je la crée !");
            this.mHandler.onCreate(this.mHandler.getWritableDatabase());
        }

        //this.mHandler.onCreate(this.mHandler.getWritableDatabase()); //pour vider la table

    }



    public boolean ajouter(String nom_p, String ville_p, String sexe_p) {


        //TODO Ajouter le sexe
        // CODE
        SQLiteDatabase mDb = this.mHandler.getWritableDatabase();;

        try {

            ContentValues values = new ContentValues();
            values.put("nom", nom_p);
            values.put("ville", ville_p);
            mDb.insert(Util.TABLE_NAME, null, values);


            Cursor c = (Cursor) mDb.rawQuery("select * from " + Util.TABLE_NAME + ";", null);

            Log.i(Util.APP_TAG, "clubDAO: Ajout => nb = " + c.getCount());
            return true;


        } catch (Exception e) {

            Log.i(Util.APP_TAG, "clubDAO: l'Ajout s'est mal passé !");
            return false;
        }

    }


    public boolean modifier (String nom_p, String ville_p, Integer id_p) {

        Log.i(Util.APP_TAG, "clubDAO: la Modification....!!!!!");
        // CODE
        SQLiteDatabase mDb = this.mHandler.getWritableDatabase();;

        try {

            String strSQL = "UPDATE " + Util.TABLE_NAME

                    + " SET " + Util.NOM + " = '" + nom_p + "',"
                    + Util.VILLE + " = '" + ville_p + "' WHERE " + Util.KEY + " = " + id_p + ";";

            mDb.execSQL(strSQL);


            return true;


        } catch (Exception e) {

            Log.i(Util.APP_TAG, "clubDAO: la Modification s'est mal passé !");
            return false;
        }


    }


    public boolean supprimer(Integer id_p) {


        Log.i(Util.APP_TAG, "clubDAO: la Suppression....!!!!!");
        // CODE
        SQLiteDatabase mDb = this.mHandler.getWritableDatabase();;

        try {

            Log.i(Util.APP_TAG, "clubDAO: test 1");
            String strSQL = "DELETE FROM " + Util.TABLE_NAME

                    + " WHERE " + Util.KEY + " = " + id_p + ";";

            Log.i(Util.APP_TAG, "clubDAO: test 2");
            mDb.execSQL(strSQL);
            Log.i(Util.APP_TAG, "clubDAO: test 3 ");

            return true;


        } catch (Exception e) {

            Log.i(Util.APP_TAG, "clubDAO: la Suppression s'est mal passée !");
            return false;
        }

    }


    /**
     * @param m le métier modifié
     */
    public void modifier(clubJudo m) {
        // CODE
    }

    /**
     * @param id l'identifiant du métier à récupérer
     */

    /**
    public clubJudo selectionner(long id) {
        // CODE

        return Null;
    }*/


}

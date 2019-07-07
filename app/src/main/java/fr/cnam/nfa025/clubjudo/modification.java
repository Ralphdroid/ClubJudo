package fr.cnam.nfa025.clubjudo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class modification extends AppCompatActivity  {

    clubDAO club_db;
    ArrayAdapter<String> adapter;
    ArrayList<String> listeClub;
    Map<Integer, Integer> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modification_layout);

        club_db = new clubDAO(getBaseContext());
        listeClub = new ArrayList<String>();
        data = new HashMap<Integer, Integer>() ;

        afficherNbLignes();
        afficherListeParOrdreAlphab();

    }


    @Override
    public void onBackPressed() {
        Log.i(Util.APP_TAG, "CycleDeVie.onBackPressed(): début !");
        this.finish();
    }


    @Override
    public void onStop(){
        Log.i(Util.APP_TAG, "CycleDeVie.onStop(): début !");
        super.onStop();
    }
    @Override
    public void onDestroy(){
        Log.i(Util.APP_TAG, "CycleDeVie.onDestroy(): début !");
        super.onDestroy();
    }



    public void afficherListeParOrdreAlphab() {

        adapter = new ArrayAdapter<String>(getBaseContext(),
                                            android.R.layout.simple_list_item_1,
                                            listeClub);

        ListView mListView = (ListView) findViewById(R.id.LV_listeClub);
        mListView.setAdapter(adapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent modificationFormulaire = new Intent(modification.this , modification_formulaire.class);
                modificationFormulaire.putExtra("id_mod", data.get(position));
                startActivityForResult(modificationFormulaire, 1);

                }
        });

        for(char alphabet = 'a'; alphabet <='z'; alphabet ++ )
        {
            afficherListe(alphabet);

        }
    }

    public void afficherListe(char alphabet_p) {

        SQLiteDatabase mDb = club_db.mHandler.getWritableDatabase();


        try {

            Cursor cursor = (Cursor) mDb.rawQuery("select * from " + Util.TABLE_NAME + " where NOM LIKE '" + alphabet_p + "%';", null);

            if (cursor.getCount()> 0) listeClub.add(alphabet_p+"");

            if (cursor != null) {
                // move cursor to first row
                if (cursor.moveToFirst()) {
                    do {

                        // Get version from Cursor
                        listeClub.add(cursor.getString(1) + " -- " + cursor.getString(2));
                        data.put(listeClub.size()-1, cursor.getInt(0));

                        // move to next row
                    } while (cursor.moveToNext());
                }
            }

            Log.i(Util.APP_TAG, "Modification: la donnée n'a pas réussi à être lue !");

        } catch (Exception e) {

            Log.i(Util.APP_TAG, "Modification: La table est vide ou pb !"  );
        }



        adapter.notifyDataSetChanged();

    }

    public void retour_accueil(View v) {

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        afficherNbLignes();

        listeClub.clear();
        adapter.notifyDataSetChanged();

        ListView mListView = (ListView) findViewById(R.id.LV_listeClub);
        mListView.invalidateViews();
        mListView.refreshDrawableState();

        afficherListeParOrdreAlphab();


    }

    public void afficherNbLignes() {
        //SQLiteDatabase mDb = club_db.mHandler.getWritableDatabase();
        //Cursor cursor = (Cursor) mDb.rawQuery("SELECT COUNT(*) FROM " + Util.TABLE_NAME + ";", null);

        SQLiteDatabase mDb = club_db.mHandler.getWritableDatabase(); //db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(mDb, Util.TABLE_NAME);
        mDb.close();
        Log.i(Util.APP_TAG, "  ===> Modification: Nb de lignes de la table = " +  count);

    }

}

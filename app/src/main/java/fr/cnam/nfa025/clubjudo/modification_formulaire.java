package fr.cnam.nfa025.clubjudo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class modification_formulaire extends Activity {

    clubDAO club_db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modification_formulaire_layout);
        remplirChampsAModifier(getIntent().getExtras().getInt("id_mod"));
    }

    public void remplirChampsAModifier(Integer id_p) {


        club_db = new clubDAO(getBaseContext());
        SQLiteDatabase mDb = club_db.mHandler.getWritableDatabase();

        try {

            Cursor cursor = (Cursor) mDb.rawQuery("select " + Util.NOM + ", " + Util.VILLE + " from " + Util.TABLE_NAME + " where " + Util.KEY +" = " + id_p +";", null);

            if (cursor != null) {
                // move cursor to first row
                if (cursor.moveToFirst()) {
                    do {
                        EditText ed_nom = this.findViewById(R.id.modification_nom);
                        ed_nom.setText(cursor.getString(0), TextView.BufferType.EDITABLE);


                        EditText ed_ville = this.findViewById(R.id.modification_ville);
                        ed_ville.setText(cursor.getString(1), TextView.BufferType.EDITABLE);
                        // move to next row
                    } while (cursor.moveToNext());
                }
            }

            Log.i(Util.APP_TAG, "Modification_formulaire: la donnée n'a pas réussi à être lue !");

        } catch (Exception e) {

            Log.i(Util.APP_TAG, "Modification_formulaire: La table est vide ou pb !"  );
        }


    }


    public void supprimer(View v) {

        club_db.supprimer(getIntent().getExtras().getInt("id_mod"));
        retour_liste(findViewById(android.R.id.content));

        retour_liste(findViewById(android.R.id.content));

    }



    public void modifier(View v) {

        String nom = ((EditText)findViewById(R.id.modification_nom)).getText().toString();
        String ville = ((EditText)findViewById(R.id.modification_ville)).getText().toString();

        if (club_db.modifier(nom, ville, getIntent().getExtras().getInt("id_mod"))) {

            //Ajout ok => on vide les champs
            EditText et_nom = (EditText)findViewById(R.id.modification_nom);
            et_nom.setText("");

            EditText et_ville = (EditText)findViewById(R.id.modification_ville);
            et_ville.setText("");

        }

        retour_liste(findViewById(android.R.id.content));


    }

    public void retour_liste(View v) {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();

    }


}

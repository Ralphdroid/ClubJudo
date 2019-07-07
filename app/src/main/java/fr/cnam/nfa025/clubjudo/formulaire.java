package fr.cnam.nfa025.clubjudo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class formulaire extends AppCompatActivity {

    clubDAO club_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulaire_layout);

        club_db = new clubDAO(getBaseContext());

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


    public void addListenerOnButton() {

        RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        RadioButton rb_m = (RadioButton)(findViewById(R.id.sexe_m));
        RadioButton rb_f = (RadioButton)(findViewById(R.id.sexe_f));


        switch (selectedId) {
            case R.id.sexe_f : {

                if (rb_m.isSelected()) rb_m.setChecked(false);
                break;
        }
            case R.id.sexe_m : {

                if (rb_f.isSelected()) rb_f.setChecked(false);
                break;

            }
        }


    }

    public void ajouter(View v) {

        String nom = ((EditText)findViewById(R.id.nom)).getText().toString();
        String ville = ((EditText)findViewById(R.id.ville)).getText().toString();

        RadioButton rb_m = (RadioButton)(findViewById(R.id.sexe_m));
        RadioButton rb_f = (RadioButton)(findViewById(R.id.sexe_f));

        String sexe = (rb_m.isSelected())? "H" : "F";

            if (club_db.ajouter(nom, ville, sexe)) {

                //Ajout ok => on vide les champs
                EditText et_nom = (EditText)findViewById(R.id.nom);
                et_nom.setText("");

                EditText et_ville = (EditText)findViewById(R.id.ville);
                et_ville.setText("");

        }


    }

    public void retour_accueil(View v) {

        finish();

    }


}

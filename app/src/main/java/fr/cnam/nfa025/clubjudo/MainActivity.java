package fr.cnam.nfa025.clubjudo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity  {


    clubDAO club_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        club_db = new clubDAO(getBaseContext());

    }


    public void menuItemSelected(View v) {

    switch(v.getId()) {

        case R.id.ajout: {

            Intent formulaire = new Intent(MainActivity.this, formulaire.class);
            startActivity(formulaire);
            break;
        }


        case R.id.modifier: {

            Intent modification = new Intent(MainActivity.this, modification.class);
            modification.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //Bundle bundle = new Bundle();
            //bundle.putSerializable("club_DAO",this.club_db);
            //modification.putExtras(bundle);
            //formulaire.putExtra("DBHandler", (Serializable)this.mHandler);
            //formulaire.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(modification);
            break;
        }

        default:break;


    }


    }




}

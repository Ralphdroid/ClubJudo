package fr.cnam.nfa025.clubjudo;

public class Util {

    public static final String NOM = "nom";

    public static final String TABLE_NAME = "clubjudo";
    public static final String KEY = "id";
    public static final String VILLE = "ville";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOM + " VARCHAR, " + VILLE + " VARCHAR);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public static final String APP_TAG = "Judo_TAG";
    public static final String FILENAME = "database.db";


}

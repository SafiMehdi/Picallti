package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import data.Offre;

import java.text.ParseException;

public class OffreDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_OFFRE = "Offre_table";
    public static final String ID = "id";
    public static final String IMAGE_ID = "image_id";
    public static final String TITRE = "titre";
    public static final String DESCRIPTION = "description";
    public static final String LOCALISATION = "localisation";
    public static final String PRIX = "prix";
    public static final String TIME = "time";
    private static final String OPERATION = "operation";
    public static final String USER = "user";
    public static final String VEHICULE = "vehicule";

    public static final String CREATE_OFFRE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_OFFRE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            IMAGE_ID + " INTEGER," +
            TITRE + " TEXT," +
            LOCALISATION + " TEXT," +
            TIME + " TEXT," +
            PRIX + " DOUBLE," +
            OPERATION + " TEXT," +
            DESCRIPTION + " TEXT," +
            USER + " INT," +
            VEHICULE + " INT," +
            "FOREIGN KEY("+VEHICULE+") REFERENCES "+VehiculeDbHelper.TABLE_VEHICULE+"("+ID+")," +
            "FOREIGN KEY("+USER+") REFERENCES "+UserDbHelper.TABLE_USER+"("+ID+"))";

    public OffreDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        System.out.println(CREATE_OFFRE_TABLE);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_OFFRE_TABLE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("hhhhhhhhhhhhhh");
        db.execSQL(CREATE_OFFRE_TABLE);
        System.out.println("Offre table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertOffre(Offre offre) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE_ID, offre.getImageId());
        values.put(TITRE, offre.getTitre());
        values.put(DESCRIPTION, offre.getDescription());
        values.put(LOCALISATION, offre.getLocalisation());
        values.put(PRIX, offre.getPrix());
        values.put(OPERATION, offre.getOperation());
        values.put(USER, offre.getUser().getId());
        db.insert(TABLE_OFFRE, null, values);
        System.out.println("offer inserted");
    }
}

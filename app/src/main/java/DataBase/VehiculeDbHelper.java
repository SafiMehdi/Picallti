package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;

import data.User;
import data.Vehicule;

public class VehiculeDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_VEHICULE = "Vehicule_table";
    public static final String ID = "id";
    public static final String NOM = "nom";
    public static final String MARQUE = "marque";
    public static final String DESCRIPTION = "description";
    public static final String TYPE = "Vehicule_type";
    static final String CREATE_VEHICULE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_VEHICULE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            NOM + " TEXT," +
            MARQUE + " TEXT," +
            TYPE + " INT," +
            DESCRIPTION + " TEXT," +
            "FOREIGN KEY("+TYPE+") REFERENCES "+VehiculeTypeDbHelper.TABLE_VEHICULE_TYPE+"("+ID+"))";

    public VehiculeDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_VEHICULE_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertVehicule(Vehicule vehicule) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOM, vehicule.getNom());
        values.put(MARQUE, vehicule.getMarque());
        values.put(DESCRIPTION, vehicule.getDescription());
        values.put(TYPE, vehicule.getVehiculeType().getId());
        db.insert(TABLE_VEHICULE, null, values);
        System.out.println("vehicule inserted");
    }
}

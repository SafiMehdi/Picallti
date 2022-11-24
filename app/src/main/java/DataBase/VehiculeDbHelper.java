package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VehiculeDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_VEHICULE = "Vehicule_table";
    public static final String ID = "id";
    public static final String NOM = "nom";
    public static final String MARQUE = "marque";
    public static final String DESCRIPTION = "description";
    static final String CREATE_VEHICULE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_VEHICULE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            NOM + " TEXT," +
            MARQUE + " TEXT," +
            DESCRIPTION + " TEXT )";

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
}

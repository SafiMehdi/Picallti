package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VehiculeTypeDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_VEHICULE_TYPE = "Vehicule_type_table";
    public static final String ID = "id";
    public static final String NOM = "nom";
    static final String CREATE_VEHICULE_TYPE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_VEHICULE_TYPE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            NOM + " TEXT)";

    public VehiculeTypeDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_VEHICULE_TYPE_TABLE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

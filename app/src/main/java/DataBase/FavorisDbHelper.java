package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FavorisDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_FAVORIS = "Favoris_table";
    public static final String ID = "id";
    public static final String USER = "user";
    public static final String OFFRE = "offre";
    public static final String CREATE_TABLE_FAVORIS = "CREATE TABLE IF NOT EXISTS " + TABLE_FAVORIS + " (" +
            ID + " INTEGER PRIMARY KEY," +
            OFFRE + " INT," +
            USER + " INT," +
            "FOREIGN KEY("+USER+") REFERENCES "+USER+"("+ID+")," +
            "FOREIGN KEY("+OFFRE+") REFERENCES "+OFFRE+"("+ID+")" +
            ")";

    public FavorisDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //System.out.println(CREATE_TABLE_FAVORIS);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_TABLE_FAVORIS);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

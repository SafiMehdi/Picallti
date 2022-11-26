package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;

import data.Commentaire;
import data.Favoris;

public class FavorisDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_FAVORIS = "Favoris_table";
    public static final String ID = "id";
    public static final String USER = "user";
    public static final String OFFRE = "offre";
    public static final String LocalDateTime = "date_time";

    public static final String CREATE_TABLE_FAVORIS = "CREATE TABLE IF NOT EXISTS " + TABLE_FAVORIS + " (" +
            ID + " INTEGER PRIMARY KEY," +
            OFFRE + " INT," +
            LocalDateTime + " TEXT," +
            USER + " INT," +
            "FOREIGN KEY("+USER+") REFERENCES "+UserDbHelper.TABLE_USER+"("+ID+")," +
            "FOREIGN KEY("+OFFRE+") REFERENCES "+OffreDbHelper.TABLE_OFFRE+"("+ID+")" +
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

    public void insertFavoris(Favoris favoris) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LocalDateTime, favoris.getLocalDateTime().toString());
        values.put(USER, favoris.getUser().getId());
        values.put(OFFRE, favoris.getOffre().getId());
        db.insert(TABLE_FAVORIS, null, values);
        System.out.println("favoris inserted");
    }

}

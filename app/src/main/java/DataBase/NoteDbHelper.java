package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NOTE = "Note_table";
    public static final String ID = "id";
    public static final String USER = "user";
    public static final String OFFRE = "offre";
    public static final String NOTE = "note";
    public static final String CREATE_TABLE_NOTE = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            NOTE + " TEXT," +
            OFFRE + " INT," +
            USER + " INT," +
            "FOREIGN KEY("+USER+") REFERENCES "+USER+"("+ID+")," +
            "FOREIGN KEY("+OFFRE+") REFERENCES "+OFFRE+"("+ID+")" +
            ")";

    public NoteDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

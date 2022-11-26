package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;

import data.Favoris;
import data.Note;

public class NoteDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NOTE = "Note_table";
    public static final String ID = "id";
    public static final String USER = "user";
    public static final String OFFRE = "offre";
    public static final String NOTE = "note";
    public static final String LocalDateTime = "date_time";

    public static final String CREATE_TABLE_NOTE = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            NOTE + " TEXT," +
            OFFRE + " INT," +
            LocalDateTime + " TEXT," +
            USER + " INT," +
            "FOREIGN KEY("+USER+") REFERENCES "+UserDbHelper.TABLE_USER+"("+ID+")," +
            "FOREIGN KEY("+OFFRE+") REFERENCES "+OffreDbHelper.TABLE_OFFRE+"("+ID+")" +
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

    public void insertNote(Note note) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LocalDateTime, note.getLocalDateTime().toString());
        values.put(USER, note.getUser().getId());
        values.put(NOTE, note.getNote());
        values.put(OFFRE, note.getOffre().getId());
        db.insert(TABLE_NOTE, null, values);
        System.out.println("note inserted");
    }
}

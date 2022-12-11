package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.ArrayList;

import data.Favoris;
import data.Note;

public class NoteDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NOTE = "Note_table";
    public static final String ID = "id";
    public static final String USER = "user";
    public static final String OFFRE = "offre";
    public static final String NOTE = "note";
    public Context context;

    public static final String CREATE_TABLE_NOTE = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            NOTE + " INT," +
            OFFRE + " INT," +
            USER + " INT," +
            "FOREIGN KEY("+USER+") REFERENCES "+UserDbHelper.TABLE_USER+"("+ID+")," +
            "FOREIGN KEY("+OFFRE+") REFERENCES "+OffreDbHelper.TABLE_OFFRE+"("+ID+")" +
            ")";
    public UserDbHelper userDbHelper;
    public OffreDbHelper offreDbHelper;

    public NoteDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version,PicalltiDbHelper picalltiDbHelper) {
        super(context, name, factory, version);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_TABLE_NOTE);
        this.context = context;
        this.offreDbHelper = picalltiDbHelper.offreDbHelper;
        this.userDbHelper = picalltiDbHelper.userDbHelper;
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
//        values.put(LocalDateTime, note.getLocalDateTime().toString());
        values.put(USER, note.getUser().getId());
        values.put(NOTE, note.getNote());
        values.put(OFFRE, note.getOffre().getId());
        db.insert(TABLE_NOTE, null, values);
    }

    public ArrayList<Note> readNotes() throws ParseException {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NOTE,
                null,
                null,
                null,
                null,
                null,
                null
        );
        //ArrayList<String> itemTitles = new ArrayList<String>();
        ArrayList<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            int user = cursor.getInt(cursor.getColumnIndexOrThrow(USER));
            int offre = cursor.getInt(cursor.getColumnIndexOrThrow(OFFRE));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            int note = cursor.getInt(cursor.getColumnIndexOrThrow(NOTE));

            Note noteObj = new Note(id,note,userDbHelper.selectUserById(user),offreDbHelper.selectOfferById(offre));
            //Commentaire comment = new Commentaire(id,commentaire,userDbHelper.selectUserById(user),offreDbHelper.selectOfferById(offre), LocalDate.parse(date), LocalTime.parse(time));
            notes.add(noteObj);
        }
        cursor.close();
        //String items[] = itemTitles.toArray(new String[itemTitles.size()]);
        return notes;

    }

    public Note selectNoteById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NOTE+" WHERE id="+id+"", null);

        if (cursor.moveToFirst()){
            int user = cursor.getInt(cursor.getColumnIndexOrThrow(USER));
            int offre = cursor.getInt(cursor.getColumnIndexOrThrow(OFFRE));
            int note = cursor.getInt(cursor.getColumnIndexOrThrow(NOTE));

            return  new Note(id,note,userDbHelper.selectUserById(user),offreDbHelper.selectOfferById(offre));
        }cursor.close();
        return  null;
    }
    public void deleteAll(){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_NOTE, null, null);
    }
}

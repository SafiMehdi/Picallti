package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import data.Commentaire;
import data.Offre;
import data.User;

public class CommentaireDbHelper extends SQLiteOpenHelper {
    public static final String COMMENTAIRE_TABLE = "Commentaire";
    public static final String ID = "id";
    public static final String COMMENTAIRE = "commentaire";
    public static final String OFFRE = "offre";
    public static final String USER = "user";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public Context context;
    public static final String CREATE_COMMENTAIRE_TABLE = "CREATE TABLE IF NOT EXISTS " + COMMENTAIRE_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            COMMENTAIRE + " TEXT," +
            OFFRE + " INT," +
            USER + " INT," +
            DATE + " TEXT,"+
            TIME + " TEXT,"+
            "FOREIGN KEY("+USER+") REFERENCES "+ UserDbHelper.TABLE_USER +"("+ID+")," +
            "FOREIGN KEY("+OFFRE+") REFERENCES "+OffreDbHelper.TABLE_OFFRE+"("+ID+")" +
            ")";
   // public PicalltiDbHelper picalltiDbHelper;
    public UserDbHelper userDbHelper;
    public OffreDbHelper offreDbHelper;

    public CommentaireDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version,PicalltiDbHelper picalltiDbHelper) {
        super(context, name, factory, version);
        //System.out.println(CREATE_COMMENTAIRE_TABLE);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_COMMENTAIRE_TABLE);
        this.context = context;
        this.userDbHelper = picalltiDbHelper.userDbHelper;
        this.offreDbHelper = picalltiDbHelper.offreDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertCommentaire(Commentaire commentaire) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMMENTAIRE, commentaire.getCommentaire());
        values.put(DATE, commentaire.getLocalDateTime().toString());
        values.put(TIME,commentaire.getTime().toString());
        values.put(USER, commentaire.getUser().getId());
        values.put(OFFRE, commentaire.getOffre().getId());
        db.insert(COMMENTAIRE_TABLE, null, values);
    }


    public ArrayList<Commentaire> readComments() throws ParseException {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                COMMENTAIRE_TABLE,
                null,
                null,
                null,
                null,
                null,
                null
        );
        //ArrayList<String> itemTitles = new ArrayList<String>();
        ArrayList<Commentaire> commentaires = new ArrayList<>();
        while (cursor.moveToNext()) {
            String commentaire = cursor.getString(
                    cursor.getColumnIndexOrThrow(COMMENTAIRE)
            );
            int user = cursor.getInt(cursor.getColumnIndexOrThrow(USER));
            int offre = cursor.getInt(cursor.getColumnIndexOrThrow(OFFRE));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(TIME));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DATE));

            Commentaire comment = new Commentaire(id,commentaire,userDbHelper.selectUserById(user),offreDbHelper.selectOfferById(offre),"LocalDate.parse(date)","LocalTime.parse(time)");
            commentaires.add(comment);
        }
        cursor.close();
        //String items[] = itemTitles.toArray(new String[itemTitles.size()]);
        return commentaires;

    }

    public Commentaire selectCommentById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+COMMENTAIRE_TABLE+" WHERE id="+id+"", null);

        if (cursor.moveToFirst()){
            String commentaire = cursor.getString(
                    cursor.getColumnIndexOrThrow(COMMENTAIRE)
            );
            int user = cursor.getInt(cursor.getColumnIndexOrThrow(USER));
            int offre = cursor.getInt(cursor.getColumnIndexOrThrow(OFFRE));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(TIME));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DATE));

            return new Commentaire(id,commentaire,userDbHelper.selectUserById(user),offreDbHelper.selectOfferById(offre),"LocalDate.parse(date)","LocalTime.parse(time)");
        }cursor.close();
        return  null;

    }

    public void deleteAll(){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(COMMENTAIRE_TABLE, null, null);
    }
}

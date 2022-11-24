package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import data.Offre;
import data.User;

public class CommentaireDbHelper extends SQLiteOpenHelper {
    public static final String COMMENTAIRE_TABLE = "Commentaire";
    public static final String ID = "id";
    public static final String COMMENTAIRE = "commentaire";
    public static final String OFFRE = "offre";
    public static final String USER = "user";
    public static final String CREATE_COMMENTAIRE_TABLE = "CREATE TABLE IF NOT EXISTS " + COMMENTAIRE_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            COMMENTAIRE + " TEXT," +
            OFFRE + " INT," +
            USER + " INT," +
            "FOREIGN KEY("+USER+") REFERENCES "+USER+"("+ID+")," +
            "FOREIGN KEY("+OFFRE+") REFERENCES "+OFFRE+"("+ID+")" +
            ")";

    public CommentaireDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //System.out.println(CREATE_COMMENTAIRE_TABLE);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_COMMENTAIRE_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

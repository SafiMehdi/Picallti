package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.picallti.Offre;

import java.text.ParseException;

import data.User;

public class UserDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_USER = "User_table";
    public static final String ID = "id";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String GENRE = "genre";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String PASSWORD = "password";
    public static final String PHOTO = "photo";
    public static final String BIO = "bio";
    public static final String ROLE = "role";

    static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" +
            ID + " INTEGER PRIMARY KEY," +
            NOM + " TEXT," +
            PRENOM + " TEXT," +
            GENRE + " TEXT," +
            EMAIL + " TEXT," +
            PHONE + " INT," +
            PASSWORD + " TEXT," +
            PHOTO + " INT," +
            BIO + " TEXT," +
            ROLE + " TEXT)";

    public UserDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_USER_TABLE);

        System.out.println(CREATE_USER_TABLE);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        System.out.println("User table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertUser(User user) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOM, user.getNom());
        values.put(PRENOM, user.getPrenom());
        values.put(GENRE, user.getGenre());
        values.put(EMAIL, user.getEmail());
        values.put(PHONE, user.getPhone());
        values.put(PASSWORD, user.getPassword());
        values.put(PHOTO, user.getPhoto());
        values.put(BIO, user.getBio());
        values.put(ROLE, user.getRole());
        db.insert(TABLE_USER, null, values);
        System.out.println("inserted");
    }
}

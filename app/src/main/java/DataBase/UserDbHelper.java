package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.SurfaceControl;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.ArrayList;

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
        //System.out.println(CREATE_USER_TABLE);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
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
    }

    public ArrayList<User> readUsers() throws ParseException {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USER,
                null,
                null,
                null,
                null,
                null,
                null
        );

        //ArrayList<String> itemTitles = new ArrayList<String>();
        ArrayList<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            String nom = cursor.getString(
                    cursor.getColumnIndexOrThrow(NOM)
            );
            String prenom = cursor.getString(cursor.getColumnIndexOrThrow(PRENOM));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow(PHOTO));
            String genre = cursor.getString(cursor.getColumnIndexOrThrow(GENRE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL));
            int phone = cursor.getInt(cursor.getColumnIndexOrThrow(PHONE));
            String bio = cursor.getString(cursor.getColumnIndexOrThrow(BIO));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD));
            String role = cursor.getString(cursor.getColumnIndexOrThrow(ROLE));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            User user = new User(id,nom,prenom,genre,email,phone,password,image,bio,role);
            users.add(user);
        }
        cursor.close();
        //String items[] = itemTitles.toArray(new String[itemTitles.size()]);
        return users;

    }

    public User selectUserById(int id){
        SQLiteDatabase db = getReadableDatabase();
        /*Cursor cursor = db.query(
                TABLE_USER,
                null,
                ID  + "=?",
                new String[]{Integer.toString(id)},
                null,
                null,
                null
        );*/
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE id="+id+"", null);


        if (cursor.moveToFirst()){
            String nom = cursor.getString(
                    cursor.getColumnIndexOrThrow(NOM)
            );
            String prenom = cursor.getString(cursor.getColumnIndexOrThrow(PRENOM));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow(PHOTO));
            String genre = cursor.getString(cursor.getColumnIndexOrThrow(GENRE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL));
            int phone = cursor.getInt(cursor.getColumnIndexOrThrow(PHONE));
            String bio = cursor.getString(cursor.getColumnIndexOrThrow(BIO));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD));
            String role = cursor.getString(cursor.getColumnIndexOrThrow(ROLE));
            //int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            return new User(id,nom,prenom,genre,email,phone,password,image,bio,role);
        }cursor.close();
        return  null;
    }

    public void deleteAll(){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_USER, null, null);
    }

}

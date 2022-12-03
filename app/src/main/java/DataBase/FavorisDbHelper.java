package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import data.Commentaire;
import data.Favoris;

public class FavorisDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_FAVORIS = "Favoris_table";
    public static final String ID = "id";
    public static final String USER = "user";
    public static final String OFFRE = "offre";

    public Context context;

    public static final String CREATE_TABLE_FAVORIS = "CREATE TABLE IF NOT EXISTS " + TABLE_FAVORIS + " (" +
            ID + " INTEGER PRIMARY KEY," +
            OFFRE + " INT," +
            USER + " INT," +
            "FOREIGN KEY("+USER+") REFERENCES "+UserDbHelper.TABLE_USER+"("+ID+")," +
            "FOREIGN KEY("+OFFRE+") REFERENCES "+OffreDbHelper.TABLE_OFFRE+"("+ID+")" +
            ")";
    public UserDbHelper userDbHelper;
    public OffreDbHelper offreDbHelper;

    public FavorisDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version,PicalltiDbHelper picalltiDbHelper) {
        super(context, name, factory, version);
        //System.out.println(CREATE_TABLE_FAVORIS);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_TABLE_FAVORIS);
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

    public void insertFavoris(Favoris favoris) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER, favoris.getUser().getId());
        values.put(OFFRE, favoris.getOffre().getId());
        db.insert(TABLE_FAVORIS, null, values);
        System.out.println();
    }

    public ArrayList<Favoris> readFavoris() throws ParseException {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_FAVORIS,
                null,
                null,
                null,
                null,
                null,
                null
        );
        //ArrayList<String> itemTitles = new ArrayList<String>();
        ArrayList<Favoris> favoris = new ArrayList<>();
        while (cursor.moveToNext()) {
            int user = cursor.getInt(cursor.getColumnIndexOrThrow(USER));
            int offre = cursor.getInt(cursor.getColumnIndexOrThrow(OFFRE));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));

            //Commentaire comment = new Commentaire(id,commentaire,userDbHelper.selectUserById(user),offreDbHelper.selectOfferById(offre), LocalDate.parse(date), LocalTime.parse(time));
            Favoris favori = new Favoris(id,userDbHelper.selectUserById(user),offreDbHelper.selectOfferById(offre));
            favoris.add(favori);
        }
        cursor.close();
        //String items[] = itemTitles.toArray(new String[itemTitles.size()]);
        return favoris;

    }

    public Favoris selectFavorisById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_FAVORIS+" WHERE id="+id+"", null);

        if (cursor.moveToFirst()){
            int user = cursor.getInt(cursor.getColumnIndexOrThrow(USER));
            int offre = cursor.getInt(cursor.getColumnIndexOrThrow(OFFRE));
            return new Favoris(id,userDbHelper.selectUserById(user),offreDbHelper.selectOfferById(offre));
        }cursor.close();
        return  null;
    }
    public void deleteAll(){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_FAVORIS, null, null);
    }

}

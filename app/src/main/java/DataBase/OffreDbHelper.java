package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import data.Offre;
import data.User;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class OffreDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_OFFRE = "Offre_table";
    public static final String ID = "id";
    public static final String IMAGE_ID = "image_id";
    public static final String TITRE = "titre";
    public static final String DESCRIPTION = "description";
    public static final String LOCALISATION = "localisation";
    public static final String PRIX = "prix";
    public static final String TIME = "time";
    private static final String OPERATION = "operation";
    private static final String DATE = "date";
    public static final String USER = "user";
    public static final String VEHICULE = "vehicule";
    public Context context;

    public static final String CREATE_OFFRE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_OFFRE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            IMAGE_ID + " INTEGER," +
            TITRE + " TEXT," +
            LOCALISATION + " TEXT," +
            TIME + " TEXT," +
            PRIX + " DOUBLE," +
            OPERATION + " TEXT," +
            DESCRIPTION + " TEXT," +
            USER + " INT," +
            DATE + " TEXT," +
            VEHICULE + " INT," +
            "FOREIGN KEY("+VEHICULE+") REFERENCES "+VehiculeDbHelper.TABLE_VEHICULE+"("+ID+")," +
            "FOREIGN KEY("+USER+") REFERENCES "+UserDbHelper.TABLE_USER+"("+ID+"))";

    public UserDbHelper userDbHelper;
    public VehiculeDbHelper vehiculeDbHelper;

    public OffreDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version,PicalltiDbHelper picalltiDbHelper) {
        super(context, name, factory, version);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_OFFRE_TABLE);
        this.context = context;
        this.userDbHelper = picalltiDbHelper.userDbHelper;
        this.vehiculeDbHelper = picalltiDbHelper.vehiculeDbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_OFFRE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertOffre(Offre offre) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE_ID, offre.getImageId());
        values.put(TITRE, offre.getTitre());
        values.put(DESCRIPTION, offre.getDescription());
        values.put(LOCALISATION, offre.getLocalisation());
        values.put(PRIX, offre.getPrix());
        values.put(TIME, offre.getTime().toString());
        values.put(DATE, offre.getLocalDateTime().toString());
        values.put(OPERATION, offre.getOperation());
        values.put(USER, offre.getUser().getId());
        db.insert(TABLE_OFFRE, null, values);
    }



    public ArrayList<Offre> readOffres() throws ParseException {
        SQLiteDatabase db = getReadableDatabase();
       // VehiculeDbHelper vehiculeDbHelper = new VehiculeDbHelper(context,PicalltiDbHelper.DATABASE_NAME,null,1);
        Cursor cursor = db.query(
                TABLE_OFFRE,
                null,
                null,
                null,
                null,
                null,
                null
        );
        //ArrayList<String> itemTitles = new ArrayList<String>();
        ArrayList<Offre> offres = new ArrayList<>();
        while (cursor.moveToNext()) {
            String titre = cursor.getString(
                    cursor.getColumnIndexOrThrow(TITRE)
            );
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow(IMAGE_ID));
            String operation = cursor.getString(cursor.getColumnIndexOrThrow(OPERATION));
            String localisation = cursor.getString(cursor.getColumnIndexOrThrow(LOCALISATION));
            float prix = cursor.getFloat(cursor.getColumnIndexOrThrow(PRIX));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(TIME));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DATE));
            int user = cursor.getInt(cursor.getColumnIndexOrThrow(USER));
            int vehicule = cursor.getInt(cursor.getColumnIndexOrThrow(VEHICULE));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
            Offre offre = new Offre(id,image,titre,description,localisation,prix,"LocalTime.parse(time)",operation,userDbHelper.selectUserById(user),vehiculeDbHelper.selectVehiculeById(vehicule),"LocalDate.parse(date)","kenitra");
            offres.add(offre);
        }
        cursor.close();
        //String items[] = itemTitles.toArray(new String[itemTitles.size()]);
        return offres;

    }

    public Offre selectOfferById(int id){
        SQLiteDatabase db = getReadableDatabase();
        //VehiculeDbHelper vehiculeDbHelper = new VehiculeDbHelper(context,PicalltiDbHelper.DATABASE_NAME,null,1);
        /*Cursor cursor = db.query(
                TABLE_OFFRE,
                null,
                ID + "="+id,
                null,
                null,
                null,
                null
        );*/
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_OFFRE+" WHERE id="+id+"", null);

        if (cursor.moveToFirst()){
                String titre = cursor.getString(
                        cursor.getColumnIndexOrThrow(TITRE)
                );
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
                int image = cursor.getInt(cursor.getColumnIndexOrThrow(IMAGE_ID));
                String operation = cursor.getString(cursor.getColumnIndexOrThrow(OPERATION));
                String localisation = cursor.getString(cursor.getColumnIndexOrThrow(LOCALISATION));
                float prix = cursor.getFloat(cursor.getColumnIndexOrThrow(PRIX));
                String time = cursor.getString(cursor.getColumnIndexOrThrow(TIME));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DATE));
                int user = cursor.getInt(cursor.getColumnIndexOrThrow(USER));
                int vehicule = cursor.getInt(cursor.getColumnIndexOrThrow(VEHICULE));
                //int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
                //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
                return new Offre(id,image,titre,description,localisation,prix, "LocalTime.parse(time)",operation,userDbHelper.selectUserById(user),vehiculeDbHelper.selectVehiculeById(vehicule),"LocalDate.parse(date)","kenitra");

        }
        cursor.close();
        return  null;
    }
    public void deleteAll(){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_OFFRE, null, null);
    }
}

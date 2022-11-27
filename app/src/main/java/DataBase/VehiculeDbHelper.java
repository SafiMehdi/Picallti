package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.ArrayList;

import data.User;
import data.Vehicule;
import data.VehiculeType;

public class VehiculeDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_VEHICULE = "Vehicule_table";
    public static final String ID = "id";
    public static final String NOM = "nom";
    public static final String MARQUE = "marque";
    public static final String DESCRIPTION = "description";
    public static final String TYPE = "Vehicule_type";
    public Context context;
    static final String CREATE_VEHICULE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_VEHICULE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            NOM + " TEXT," +
            MARQUE + " TEXT," +
            TYPE + " INT," +
            DESCRIPTION + " TEXT," +
            "FOREIGN KEY("+TYPE+") REFERENCES "+VehiculeTypeDbHelper.TABLE_VEHICULE_TYPE+"("+ID+"))";

    public VehiculeDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_VEHICULE_TABLE);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertVehicule(Vehicule vehicule) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOM, vehicule.getNom());
        values.put(MARQUE, vehicule.getMarque());
        values.put(DESCRIPTION, vehicule.getDescription());
        values.put(TYPE, vehicule.getVehiculeType().getId());
        db.insert(TABLE_VEHICULE, null, values);
        System.out.println("vehicule inserted");
    }

    public ArrayList<Vehicule> readVehicules() throws ParseException {
        Log.d("ensak", "invoke read vehicules");
        VehiculeTypeDbHelper vehiculeTypeDbHelper = new VehiculeTypeDbHelper(null,PicalltiDbHelper.DATABASE_NAME,null,1);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_VEHICULE,
                null,
                null,
                null,
                null,
                null,
                null
        );
        //ArrayList<String> itemTitles = new ArrayList<String>();
        ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();
        while (cursor.moveToNext()) {
            String nom = cursor.getString(
                    cursor.getColumnIndexOrThrow(NOM)
            );
            String marque = cursor.getString(cursor.getColumnIndexOrThrow(MARQUE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
            int type = cursor.getInt(cursor.getColumnIndexOrThrow(TYPE));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Vehicule v = new Vehicule(id,nom,marque,description,vehiculeTypeDbHelper.selectVehiculeTypeById(type));
            vehicules.add(v);
        }
        cursor.close();
        //String items[] = itemTitles.toArray(new String[itemTitles.size()]);
        return vehicules;

    }

    public Vehicule selectVehiculeById(int id){
        SQLiteDatabase db = getReadableDatabase();
        VehiculeTypeDbHelper vehiculeTypeDbHelper = new VehiculeTypeDbHelper(context,PicalltiDbHelper.DATABASE_NAME,null,1);
        Cursor cursor = db.query(
                TABLE_VEHICULE,
                null,
                ID + "="+id,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()){
            String nom = cursor.getString(
                    cursor.getColumnIndexOrThrow(NOM)
            );
            String marque = cursor.getString(cursor.getColumnIndexOrThrow(MARQUE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
            int type = cursor.getInt(cursor.getColumnIndexOrThrow(TYPE));

            return new Vehicule(id,nom,marque,description,vehiculeTypeDbHelper.selectVehiculeTypeById(type));
        }cursor.close();
        return  null;

    }
}

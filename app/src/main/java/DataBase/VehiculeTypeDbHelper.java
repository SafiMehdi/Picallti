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

public class VehiculeTypeDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_VEHICULE_TYPE = "Vehicule_type_table";
    public static final String ID = "id";
    public static final String NOM = "nom";
    static final String CREATE_VEHICULE_TYPE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_VEHICULE_TYPE + " (" +
            ID + " INTEGER PRIMARY KEY," +
            NOM + " TEXT)";

    public VehiculeTypeDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_VEHICULE_TYPE_TABLE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertVehiculeType(VehiculeType vehiculeType) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOM, vehiculeType.getNom());
        db.insert(TABLE_VEHICULE_TYPE, null, values);
        System.out.println("vehicule type inserted");
    }

    public ArrayList<VehiculeType> readVehiculeTypes() throws ParseException {
        Log.d("ensak", "invoke read user");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_VEHICULE_TYPE,
                null,
                null,
                null,
                null,
                null,
                null
        );
        //ArrayList<String> itemTitles = new ArrayList<String>();
        ArrayList<VehiculeType> types = new ArrayList<>();
        while (cursor.moveToNext()) {
            String nom = cursor.getString(
                    cursor.getColumnIndexOrThrow(NOM)
            );
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            types.add(new VehiculeType(id,nom));
        }
        cursor.close();
        //String items[] = itemTitles.toArray(new String[itemTitles.size()]);
        return types;

    }

    public VehiculeType selectVehiculeTypeById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_VEHICULE_TYPE+" WHERE id="+id+"", null);

        if (cursor.moveToFirst()){
            String nom = cursor.getString(
                    cursor.getColumnIndexOrThrow(NOM)
            );
            return new VehiculeType(id,nom);

        }cursor.close();
        return  null;
    }
    public void deleteAll(){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_VEHICULE_TYPE, null, null);
    }
}

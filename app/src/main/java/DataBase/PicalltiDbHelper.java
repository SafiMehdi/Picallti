package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class PicalltiDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "picallti21";
    //public Context context;
    public UserDbHelper userDbHelper ;
    public OffreDbHelper offreDbHelper;
    public CommentaireDbHelper commentaireDbHelper;
    public FavorisDbHelper favorisDbHelper;
    public NoteDbHelper noteDbHelper;
    public VehiculeTypeDbHelper vehiculeTypeDbHelper;
    public VehiculeDbHelper vehiculeDbHelper;
    public NotificationDbHelper notificationDbHelper;


    public PicalltiDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.context=context;
        this.userDbHelper = new UserDbHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.vehiculeTypeDbHelper = new VehiculeTypeDbHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.vehiculeDbHelper = new VehiculeDbHelper(context,DATABASE_NAME,null,DATABASE_VERSION,this);
        this.offreDbHelper = new OffreDbHelper(context,DATABASE_NAME,null,DATABASE_VERSION,this);
        this.commentaireDbHelper = new CommentaireDbHelper(context,DATABASE_NAME,null,DATABASE_VERSION,this);
        this.favorisDbHelper = new FavorisDbHelper(context,DATABASE_NAME,null,DATABASE_VERSION,this);
        this.noteDbHelper = new NoteDbHelper(context,DATABASE_NAME,null,DATABASE_VERSION,this);
        this.notificationDbHelper = new NotificationDbHelper(context,DATABASE_NAME,null,DATABASE_VERSION,this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Log.d("cons", "consssss");
        //
        db.execSQL(UserDbHelper.CREATE_USER_TABLE);
        System.out.println("---------------------------");
        System.out.println(OffreDbHelper.CREATE_OFFRE_TABLE);
        System.out.println("---------------------------");

        try {
            db.execSQL(OffreDbHelper.CREATE_OFFRE_TABLE);
        }catch (Exception e){
            System.out.println("offre table not created");
            System.out.println(e.getMessage());
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

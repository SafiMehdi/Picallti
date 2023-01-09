package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import data.Notification;
import data.Offre;
import data.User;

public class NotificationDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NOTIFICATION = "Notification_table";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String ICON = "icon";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String USER = "user";
    public Context context;
    public static final String CREATE_NOTIFICATION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTIFICATION + " (" +
            ID + " INTEGER PRIMARY KEY," +
            ICON + " INTEGER," +
            TITLE + " TEXT," +
            TEXT + " TEXT," +
            USER + " INT," +
            DATE + " TEXT," +
            TIME + " TEXT," +
            "FOREIGN KEY("+USER+") REFERENCES "+UserDbHelper.TABLE_USER+"("+ID+"))";
    public UserDbHelper userDbHelper;

    public NotificationDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version,PicalltiDbHelper picalltiDbHelper) {
        super(context, name, factory, version);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(CREATE_NOTIFICATION_TABLE);
        this.context = context;
        this.userDbHelper = picalltiDbHelper.userDbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertNotification(Notification notification) throws ParseException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ICON, notification.getIcon());
        values.put(TITLE, notification.getTitle());
        values.put(TEXT, notification.getText());
        values.put(TIME, notification.getTime().toString());
        values.put(DATE, notification.getLocalDate().toString());
        values.put(USER, notification.getUser().getId());
        db.insert(TABLE_NOTIFICATION, null, values);
    }

    public ArrayList<Notification> readNotificationsByUser(User user) throws ParseException {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NOTIFICATION+" WHERE user="+user.getId()+"", null);

        //ArrayList<String> itemTitles = new ArrayList<String>();
        ArrayList<Notification> notifications = new ArrayList<>();
        while (cursor.moveToNext()) {
            String titre = cursor.getString(
                    cursor.getColumnIndexOrThrow(TITLE)
            );
            String text = cursor.getString(cursor.getColumnIndexOrThrow(TEXT));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow(ICON));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(TIME));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DATE));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            notifications.add(new Notification(id,titre,text,image,user,"LocalTime.parse(time)","LocalDate.parse(date)"));
        }
        cursor.close();
        //String items[] = itemTitles.toArray(new String[itemTitles.size()]);
        return notifications;
    }
    public void deleteAll(){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_NOTIFICATION, null, null);
    }

}

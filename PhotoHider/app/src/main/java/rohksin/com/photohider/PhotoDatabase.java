package rohksin.com.photohider;

/**
 * Created by Illuminati on 2/12/2018.
 */


import android.database.sqlite.SQLiteOpenHelper;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Illuminati on 12/16/2017.
 */

public class PhotoDatabase extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "LinkDatabase.db";          //<--- ir gives compile time erroe it it is not static
    public final static int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    //************************************************************************************************
    //                         History Table
    //************************************************************************************************
    public final String TABLE_NAME = "LINKTABLE";
    public final String COLUMN_ID = "ID";
    //public final String LINK_TEXT = "LINK_TEXT";
    public final String LINK = "LINK";
    public  String[] colums = new String[]{COLUMN_ID, LINK};

    //************************************************************************************************
    //                          Raw Queries
    //************************************************************************************************
    private  final String CREATE_TABLE = "create table "+TABLE_NAME +" ( " + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," + LINK +" VARCHAR );";
    private final String DROP_TABLE = "drop table if exists "+ TABLE_NAME;



    public PhotoDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

    //*********************************************************************
    //  CRUD OPERATIONS
    //*********************************************************************


    public void addHistory(String name)
    {
        db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LINK,name);
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public void deleteHistory(String name)
    {
        String whereClause = LINK+"=?";
        String[] whereArgs = new String[] { name };
        db.delete(TABLE_NAME, whereClause, whereArgs);
    }


    public List<PhotoData> getHistoryList()
    {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,colums,null,null,null,null,null);

        int length = cursor.getCount();
        List<PhotoData> savedLinks = new ArrayList<PhotoData>();

        for(int i =0;i<length;i++)
        {
            cursor.moveToNext();
            cursor.getString(1);
            String history =  cursor.getString(cursor.getColumnIndex(LINK));

            PhotoData data = new PhotoData();
            data.setPath(history);

            savedLinks.add(data);
        }

        return savedLinks;
    }
}


package rohksin.com.sqlitedatabasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 12/5/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper{


    public final static String DATABASE_NAME = "Mydatabase.db";          //<--- ir gives compile time erroe it it is not static
    public final static int DATABASE_VERSION = 1;

    public final String TABLE_NAME = "PEOPLE";
    public final String COLUMN_ID = "ID";
    public final String FIRST_NAME = "FIRST_NAME";
    public final String LAST_NAME = "LAST_NAME";


    private SQLiteDatabase db;

    private  final String CREATE_TABLE = "create table "+TABLE_NAME +" ( " + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," + FIRST_NAME +" VARCHAR," + LAST_NAME + " VARCHAR );";

    //String cr = "create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + FIRST_NAME + " VARCHAR, " + LAST_NAME + " VARCHAR);";

    private final String DROP_TABLE = "drop table if exists "+ TABLE_NAME;


    public MyDatabaseHelper(Context context) {
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


    public void addPeople(People people)
    {
        db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME,people.getLname());
        contentValues.put(LAST_NAME,people.getFname());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();

    }

    public List<People> getPeopleList()
    {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

        int length = cursor.getCount();
        Log.d("CURSOR LENGTH",length+"");

        List<People> people = new ArrayList<People>();

        for(int i =0;i<length;i++)
        {
            People person = new People();
            person.setFname(cursor.getColumnName(1));
            person.setLname(cursor.getColumnName(2));
            people.add(person);
        }

        return people;
    }

}

package rohksin.com.sqlitedatabasedemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Illuminati on 12/6/2017.
 */

public class MyContentProvider extends ContentProvider {


    private MyDatabaseHelper databaseHelper;

    public static final String PROVIDER_NAME = "rohksin.com.sqlitedatabasedemo.NameProvider";
    public static final String URL = "content://"+PROVIDER_NAME+"/names";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"names",1);
        uriMatcher.addURI(PROVIDER_NAME,"names/#",2);
    }


    @Override
    public boolean onCreate() {
        Context context= getContext();
        databaseHelper = new MyDatabaseHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        switch (uriMatcher.match(uri))
        {
            case 1:
            {
                Cursor cursor = databaseHelper.getPeopleList("PEOPLE");
                return cursor ;
            }
            default:
            {
                return null;
            }

        }


    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

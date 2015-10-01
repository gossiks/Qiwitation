package org.kazin.qiwitation.backend;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by Alexey on 30.09.2015.
 */
public class UserContentProvider extends ContentProvider{

    static final String DB_NAME = "qiwitation";
    static final int DB_VERSION = 1;

    static final String db_name = "qiwitation";
    static final int db_version = 1;

    static final String table_users = "users";
    static final String ID_USER = "ID_USER";
    static final String NAME_USER = "NAME_USER";

    static final String db_create = "create table "+table_users+" (" + "id integer primary key autoincrement,"
            + ID_USER +" integer, "+ NAME_USER +" text"+");";

    static final String AUTHORITY = "org.kazin.qiwitation.backend.usercontentprovider";
    static final String USERS_PATH = "users";

    //General Uri
    public static final  Uri USER_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"
        +USERS_PATH);

    //data types
    static final String USER_CONTENT_TYPE = "vnd.android.cursor.item/vnd."+AUTHORITY+"."+USERS_PATH;
    static final String USER_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+AUTHORITY+"."+USERS_PATH;

    //for uri matcher
    static final int URI_USERS = 1;
    static final int URI_USERS_ID = 2;

    DBHelper dbHelper;
    SQLiteDatabase db;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, USERS_PATH, URI_USERS);
        uriMatcher.addURI(AUTHORITY, USERS_PATH+"/#", URI_USERS_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)){
            case URI_USERS:
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = NAME_USER +" ASC";
                }
                break;
            case URI_USERS_ID:
                String id = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection)){
                    selection = ID_USER +" = "+id;
                } else {
                    selection = selection + " AND "+ ID_USER +" = "+id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: "+uri);
        }

        db = dbHelper.getWritableDatabase();
        Cursor cur = db.query(table_users, projection, selection, selectionArgs, null, null, sortOrder);
        cur.setNotificationUri(getContext().getContentResolver(), USER_CONTENT_URI);

        return cur;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case URI_USERS:
                return USER_CONTENT_TYPE;
            case URI_USERS_ID:
                return USER_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if(uriMatcher.match(uri) != URI_USERS){
            throw  new IllegalArgumentException("Wrong URI: "+uri);
        }
        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(table_users, null, values);
        Uri resultUri = ContentUris.withAppendedId(USER_CONTENT_URI, rowID);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case URI_USERS:
                //eventually blank
                break;
            case URI_USERS_ID:
                String id = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection)){
                    selection = ID_USER +" = " + id;
                } else {
                    selection = selection + " AND " + ID_USER + " = "+id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: "+uri);
        }
        db = dbHelper.getWritableDatabase();
        int row = db.delete(table_users, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return row;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case URI_USERS:
                //eventualy blank
                break;
            case URI_USERS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = ID_USER + " = " + id;
                } else {
                    selection = selection + " AND " + ID_USER + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int row = db.update(table_users, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return row;
    }

    //misc classes
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(db_create);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //eventually blank
        }
    }
}

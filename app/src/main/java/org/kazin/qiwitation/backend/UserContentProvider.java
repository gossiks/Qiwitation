package org.kazin.qiwitation.backend;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Alexey on 30.09.2015.
 */
public class UserContentProvider extends ContentProvider{

    static final String db_name = "qiwitation";
    static final int db_version = 1;

    static final String table_users = "users";
    static final String id_user = "id_user";
    static final String name_user = "name_user";

    static final String db_create = "create table "+table_users+"("
            +id_user+"integer primary key, "+name_user+" text,"+");";

    static final String AUTHORITY = "org.kazin.qiwitation.backend.usercontentprovider";
    static final String USERS_PATH = "users";


    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

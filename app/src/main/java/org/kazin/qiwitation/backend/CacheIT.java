package org.kazin.qiwitation.backend;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import org.kazin.qiwitation.object.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 30.09.2015.
 */
public class CacheIT {

    private Activity mActivity;
    private final Uri USERS_URI = Uri.parse("content://"+UserContentProvider.AUTHORITY+"/"+UserContentProvider.USERS_PATH);

    public CacheIT(Activity activity) {
        mActivity = activity;
    }

    public boolean isUsersCached(){
        return 0 < new Select().from(UserAA.class).count();
    }

    public void cacheUsers(List<User> users){
        dropTableIfNeeded();

        ActiveAndroid.beginTransaction();
        try{
            for(User user:users){
                //UserAA userAA = new UserAA(user.getName(), user.getId());
                UserAA userAA = new UserAA();
                userAA.name = user.getName();
                userAA.UserId = user.getId();
                userAA.save();

                ContentValues cv = new ContentValues();
                cv.put(UserContentProvider.NAME_USER, user.getName());
                cv.put(UserContentProvider.ID_USER, user.getId());

                mActivity.getContentResolver().insert(USERS_URI, cv);
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }

    public void dropTableIfNeeded(){
        if(isUsersCached()){
            new Delete().from(UserAA.class).execute();
            mActivity.getContentResolver().delete(USERS_URI, null,null);
        }
    }

    public List<User> getCachedUsers(){
        List<UserAA> usersAA = new Select().from(UserAA.class).execute();
        List<User> users = new ArrayList<>(usersAA.size());

        for(UserAA userAA:usersAA){
            users.add(new User(userAA.UserId, userAA.name));
        }

        return users;
    }

}

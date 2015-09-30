package org.kazin.qiwitation.backend;

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

    public boolean isUsersCached(){
        return 0 < new Select().from(UserAA.class).count();
    }

    public void cacheUsers(List<User> users){
        dropTableIfNeeded();

        ActiveAndroid.beginTransaction();
        try{
            for(User user:users){
                UserAA userAA = new UserAA(user.getName(), user.getId());
                userAA.save();
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

    //sql ActiveAndroid POJO

    @Table(name = "Users")
    public class UserAA extends Model {

        @Column (name = "Name")
        public String name;

        @Column (name = "UserId")
        public int UserId;

        public UserAA() {
            super();
        }

        public UserAA(String name, int id) {
            super();
            this.name = name;
            UserId = id;
        }
    }
}

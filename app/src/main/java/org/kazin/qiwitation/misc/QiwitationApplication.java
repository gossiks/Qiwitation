package org.kazin.qiwitation.misc;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by Alexey on 30.09.2015.
 */
public class QiwitationApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName("qiwi.db").create();
        ActiveAndroid.initialize(dbConfiguration);
    }
}

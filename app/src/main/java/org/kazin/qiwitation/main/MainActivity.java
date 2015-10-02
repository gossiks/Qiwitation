package org.kazin.qiwitation.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.main.fragment.UserDetailFragment;
import org.kazin.qiwitation.main.mvp.Viewer;

public class MainActivity extends AppCompatActivity {

    public boolean mIsDualPane;
    public Viewer mViewer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.user_detail_container) != null){
            mIsDualPane = true;
        }

        mViewer = Viewer.getInstance(this);
        mViewer.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

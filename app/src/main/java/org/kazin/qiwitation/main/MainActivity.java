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
        //mViewer.onResumeUserFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package org.kazin.qiwitation.main.mvp;

import android.app.Fragment;
import android.util.Log;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.main.MainActivity;
import org.kazin.qiwitation.main.fragment.UsersFragment;
import org.kazin.qiwitation.main.fragment.UsersFragmentAdapter;
import org.kazin.qiwitation.main.fragment.misc.DividerItemDecoration;
import org.kazin.qiwitation.object.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 25.09.2015.
 */
public class ViewerSinglePane extends Viewer{

    private UsersFragment mFragment;

    public ViewerSinglePane(MainActivity activity) {
        super(activity);
    }

    @Override
    public void onCreate() {
        mFragment = new UsersFragment();
        mActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.users_container, mFragment).commit();

        super.onCreate();
    }


    @Override
    public void setUsers(List<User> users) {
        mFragment.mRecyclerView.setAdapter(new UsersFragmentAdapter(users));
        super.setUsers(users);
    }
}

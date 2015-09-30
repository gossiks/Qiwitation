package org.kazin.qiwitation.main.mvp;

import android.view.View;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.main.MainActivity;
import org.kazin.qiwitation.main.fragment.UserDetailFragment;
import org.kazin.qiwitation.main.fragment.UserDetailFragmentAdapter;
import org.kazin.qiwitation.main.fragment.UsersFragment;
import org.kazin.qiwitation.main.fragment.UsersFragmentAdapter;
import org.kazin.qiwitation.main.fragment.misc.DividerItemDecoration;
import org.kazin.qiwitation.object.Balance;
import org.kazin.qiwitation.object.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 28.09.2015.
 */
public class ViewerDualPane extends Viewer {

    private UsersFragment mUsersFragment;
    private UserDetailFragment mUserDetailFragment;

    public ViewerDualPane(MainActivity activity) {
        super(activity);
    }

    @Override
    public void onCreate() {
        mUsersFragment = new UsersFragment();
        mUserDetailFragment = new UserDetailFragment();

        mActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.users_container, mUsersFragment).commit();

        mActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.user_detail_container, mUserDetailFragment).commit();


        super.onCreate();
    }



    //set methods

    @Override
    public void setUsers(List<User> users) {
        mUsersFragment.mRecyclerView.setAdapter(new UsersFragmentAdapter(users, getUserOnClickListener()));
        super.setUsers(users);
    }

    @Override
    public void setBalances(List<Balance> balances) {
        mUserDetailFragment.mRecyclerView.setAdapter(new UserDetailFragmentAdapter(balances));
        super.setBalances(balances);
    }

    @Override
    public User getUserByAdapterView(View view) {
        int id = mUsersFragment.mRecyclerView.getChildAdapterPosition(view);
        return ((UsersFragmentAdapter)mUsersFragment.mRecyclerView.getAdapter()).getUserByPosition(id);
    }
}

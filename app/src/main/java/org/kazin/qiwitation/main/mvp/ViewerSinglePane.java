package org.kazin.qiwitation.main.mvp;

import android.app.Fragment;
import android.util.Log;
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
 * Created by Alexey on 25.09.2015.
 */
public class ViewerSinglePane extends Viewer{

    public static final String USER_FRAGMENT_TAG = "userFragment";

    private UsersFragment mFragment;
    private UserDetailFragment mUserDetailFragment;

    public ViewerSinglePane(MainActivity activity) {
        super(activity);
    }

    @Override
    public void onCreate() {

        mFragment = (UsersFragment) mActivity.getSupportFragmentManager().findFragmentByTag(USER_FRAGMENT_TAG);

        if(mFragment == null){
            mFragment = new UsersFragment();
            mFragment.setViewer(this);
            mActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.users_container, mFragment, USER_FRAGMENT_TAG).commit();
        }
        super.onCreate();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    //set methods

    @Override
    public void setUsers(List<User> users) {
        mFragment.mRecyclerView.setAdapter(new UsersFragmentAdapter(users, getUserOnClickListener()));
        super.setUsers(users);
    }

    @Override
    public void setBalances(List<Balance> balances) {
        mUserDetailFragment.mRecyclerView.setAdapter(new UserDetailFragmentAdapter(balances));
        super.setBalances(balances);
    }

    //on methods

    @Override
    public void onUserSelect(User user) {
        mUserDetailFragment = new UserDetailFragment();
        mActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.users_container, mUserDetailFragment).addToBackStack("singlePane").commit();
        super.onUserSelect(user);
    }


    //show methods

    @Override
    public void showUserLoadingProgress() {
        /*try{
            mFragment.mProgressBar.setVisibility(View.VISIBLE);
        } catch (Exception e){
            e.printStackTrace();
        }*/

        super.showUserLoadingProgress();
    }

    @Override
    public void unshowUserLoadingProgress() {
        mFragment.mProgressBar.setVisibility(View.INVISIBLE);
        super.unshowUserLoadingProgress();
    }

    @Override
    public void showBalancesLoadingProgress() {
        mUserDetailFragment.mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void unshowBalancesLoadingProgress() {
        mUserDetailFragment.mProgressBar.setVisibility(View.INVISIBLE);
    }

    //misc


    @Override
    public User getUserByAdapterView(View view) {
        int id = mFragment.mRecyclerView.getChildAdapterPosition(view);
        return ((UsersFragmentAdapter)mFragment.mRecyclerView.getAdapter()).getUserByPosition(id);
    }
}

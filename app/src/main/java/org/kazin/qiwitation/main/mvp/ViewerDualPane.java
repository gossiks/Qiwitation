package org.kazin.qiwitation.main.mvp;

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
        mUsersFragment.setViewer(this);
        mUserDetailFragment = new UserDetailFragment();
        mUserDetailFragment.setViewer(this);

        mActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.users_container, mUsersFragment).commit();

        mActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.user_detail_container, mUserDetailFragment).commit();


        super.onCreate();
    }

    //on methods

    @Override
    public void onUserSelect(User user) {
        mViewer.clearBalances();

        super.onUserSelect(user);
    }

    @Override
    public void showUserRetrieveError(String message) {
        mUsersFragment.mError.setText(message);
        mUsersFragment.mError.setVisibility(View.VISIBLE);

        mUsersFragment.mRepeatButton.setOnClickListener(mViewer.getOnClickRepeatUsersListener());
        mUsersFragment.mRepeatButton.setVisibility(View.VISIBLE);

        super.showUserRetrieveError(message);
    }

    @Override
    public void unshowUserRetrieveError() {
        mUsersFragment.mError.setVisibility(View.INVISIBLE);
        mUsersFragment.mRepeatButton.setVisibility(View.INVISIBLE);
        super.unshowUserRetrieveError();
    }

    @Override
    public void showBalancesRetrieveError(String message) {
        mUserDetailFragment.mError.setText(message);
        mUserDetailFragment.mError.setVisibility(View.VISIBLE);

        mUserDetailFragment.mRepeatButton.setOnClickListener(mViewer.getOnclickRepeatDetailsListener());
        mUserDetailFragment.mRepeatButton.setVisibility(View.VISIBLE);
        super.showBalancesRetrieveError(message);
    }

    @Override
    public void unshowBalancesRetrieveError() {
        mUserDetailFragment.mError.setVisibility(View.INVISIBLE);
        mUserDetailFragment.mRepeatButton.setVisibility(View.INVISIBLE);
        super.unshowBalancesRetrieveError();
    }

    @Override
    public void showUserLoadingProgress() {
        mUserDetailFragment.mProgressBar.setVisibility(View.VISIBLE);
        super.showUserLoadingProgress();
    }

    @Override
    public void unshowUserLoadingProgress() {
        mUserDetailFragment.mProgressBar.setVisibility(View.INVISIBLE);
        super.unshowUserLoadingProgress();
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
    public void clearBalances() {
        mViewer.unshowBalancesRetrieveError();
        try{((UserDetailFragmentAdapter)mUserDetailFragment.mRecyclerView.getAdapter()).clearData();
        }
        catch (Exception e){
            Log.d("apkapk", "No UserDetailAdapter.");
        }
        super.clearBalances();
    }

    @Override
    public User getUserByAdapterView(View view) {
        int id = mUsersFragment.mRecyclerView.getChildAdapterPosition(view);
        return ((UsersFragmentAdapter)mUsersFragment.mRecyclerView.getAdapter()).getUserByPosition(id);
    }

    //show methods


    @Override
    public void showBalancesLoadingProgress() {
        mUserDetailFragment.mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void unshowBalancesLoadingProgress() {
        mUserDetailFragment.mProgressBar.setVisibility(View.INVISIBLE);
    }
}

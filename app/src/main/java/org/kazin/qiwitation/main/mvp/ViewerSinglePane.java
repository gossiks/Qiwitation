package org.kazin.qiwitation.main.mvp;

import android.view.View;

import org.kazin.qiwitation.R;
import org.kazin.qiwitation.main.MainActivity;
import org.kazin.qiwitation.main.fragment.UserDetailFragment;
import org.kazin.qiwitation.main.fragment.UserDetailFragmentAdapter;
import org.kazin.qiwitation.main.fragment.UsersFragment;
import org.kazin.qiwitation.main.fragment.UsersFragmentAdapter;
import org.kazin.qiwitation.object.Balance;
import org.kazin.qiwitation.object.User;

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
    public void onResumeUserFragment() {
        super.onResumeUserFragment();
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
        mUserDetailFragment.mRepeatButton.setVisibility(View.INVISIBLE);
        mUserDetailFragment.mError.setVisibility(View.INVISIBLE);
        super.setBalances(balances);
    }

    //on methods

    @Override
    public void onUserSelect(User user) {
        mUserDetailFragment = new UserDetailFragment();
        mUserDetailFragment.setViewer(this);
        mActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.users_container, mUserDetailFragment).addToBackStack("singlePane").commit();
        super.onUserSelect(user);
    }


    //show methods

    @Override
    public void showUserLoadingProgress() {
        mFragment.mProgressBar.setVisibility(View.VISIBLE);

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

    @Override
    public void showUserRetrieveError(String message) {
        mFragment.mError.setText(message);
        mFragment.mError.setVisibility(View.VISIBLE);

        mFragment.mRepeatButton.setOnClickListener(mViewer.getOnClickRepeatUsersListener());
        mFragment.mRepeatButton.setVisibility(View.VISIBLE);

        super.showUserRetrieveError(message);
    }

    @Override
    public void unshowUserRetrieveError() {
        mFragment.mError.setVisibility(View.INVISIBLE);
        mFragment.mRepeatButton.setVisibility(View.INVISIBLE);
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

    //misc


    @Override
    public User getUserByAdapterView(View view) {
        int id = mFragment.mRecyclerView.getChildAdapterPosition(view);
        return ((UsersFragmentAdapter)mFragment.mRecyclerView.getAdapter()).getUserByPosition(id);
    }
}

package org.kazin.qiwitation.main.mvp;

import android.app.Activity;

import org.kazin.qiwitation.object.Balance;
import org.kazin.qiwitation.object.User;

import java.util.List;

/**
 * Created by Alexey on 25.09.2015.
 */
public class Presenter {

    private static Presenter mPresenter;

    private Model mModel;
    private Viewer mViewer;

    public Presenter(Viewer viewer) {
        mViewer = viewer;
    }

    public static Presenter getInstance(Viewer viewer) {
        if(mPresenter ==null){
            mPresenter = new Presenter(viewer);
            mPresenter.mModel =  Model.getInstance(mPresenter);
        }

        if(mPresenter.mViewer !=viewer){
            mPresenter.mViewer = viewer;
        }

        return mPresenter;
    }

    //lifecycle methods

    public void onCreate() {
        mModel.onCreate();
    }

    public void onResumeUserFragment() {
        mModel.onResumeUserFragment();
    }

    public void onResumeUserDetailFragment() {
        mModel.onResumeUserDetailFragment();
    }

    //set methods

    public void setUsers(List<User> users){
        mViewer.setUsers(users);
    }

    public void setBalances(List<Balance> balances){
        mViewer.setBalances(balances);
    }

    //onMethods

    public void onUserSelect(User user){
        mModel.onUserSelect(user);
    }


    //show methods

    public void showSetUsersError(Integer resultCode) {
        mViewer.showSetUsersError(resultCode);
    }

    public void showUserLoadingProgress() {
        mViewer.showUserLoadingProgress();
    }

    public void unshowUserLoadingProgress() {
        mViewer.unshowUserLoadingProgress();
    }

    public void showToast(String s) {
        mViewer.showToast(s);
    }

    public void showBalancesLoadingProgress() {
        mViewer.showBalancesLoadingProgress();
    }

    public void unshowBalancesLoadingProgress() {
        mViewer.unshowBalancesLoadingProgress();
    }

    public void showUserRetrieveError(String s) {
        mViewer.showUserRetrieveError(s);
    }

    public void showBalancesRetrieveError(String s) {
        mViewer.showBalancesRetrieveError(s);
    }
    //misc
    public Activity getActivity() {
        return mViewer.mActivity;
    }


    public void onClickUsersRefresh() {
        mModel.onClickUsersRefresh();
    }


}

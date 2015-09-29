package org.kazin.qiwitation.main.mvp;

import android.widget.Toast;

import org.kazin.qiwitation.main.MainActivity;
import org.kazin.qiwitation.object.Balance;
import org.kazin.qiwitation.object.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 28.09.2015.
 */
public class Viewer  {

    protected static Viewer mViewer;

    protected MainActivity mActivity;
    protected Presenter mPresenter;



    public Viewer(MainActivity activity) {
        mActivity = activity;
    }

    public static Viewer getInstance(MainActivity acti) {

        if(mViewer ==null){
            if(acti.mIsDualPane){
                mViewer = new ViewerDualPane(acti);
            } else {
                mViewer = new ViewerSinglePane(acti);
            }

            mViewer.mPresenter =  Presenter.getInstance(mViewer);
        }

        if(mViewer.mActivity !=acti){
            mViewer.mActivity = acti;
        }

        return mViewer;
    }

    //lifecycle methods

    public void onCreate() {
       mPresenter.onCreate();
    }

    //set methods
    public void setUsers(List<User> users){    }

    public void setBalances(List<Balance> balances){

    }

    //on methods

    public void onUserSelect(User user){
        mPresenter.onUserSelect(user);
    }

    //show methods

    public void showSetUsersError(Integer resultCode) {
        showToast("Can't load users list. Error code: "+resultCode);
    }

    //misc

    private void showToast(String string){
        Toast.makeText(mActivity.getApplicationContext(), string, Toast.LENGTH_SHORT);
    }
}

package org.kazin.qiwitation.main.mvp;

import org.kazin.qiwitation.object.Balance;
import org.kazin.qiwitation.object.User;

import java.util.ArrayList;
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

    //set methods

    public void setUsers(List<User> users){
        mViewer.setUsers(users);
    }

    public void setBalances(ArrayList<Balance> balances){
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
}

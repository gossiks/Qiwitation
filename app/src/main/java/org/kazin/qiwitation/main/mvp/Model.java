package org.kazin.qiwitation.main.mvp;

import android.util.Log;

import org.kazin.qiwitation.backend.RetrofitHelper;
import org.kazin.qiwitation.object.User;
import org.kazin.qiwitation.object.UsersResponse;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Alexey on 25.09.2015.
 */
public class Model {

    private static Model mModel;

    private Presenter mPresenter;


    public Model(Presenter presenter) {
        mPresenter = presenter;
    }

    public static Model getInstance(Presenter mPresenter) {
        if(mModel==null){
            mModel = new Model(mPresenter);
        }
        return mModel;
    }

    //lifecycle methods

    public void onCreate() {
        //TODO
        RetrofitHelper.getIUserRestAPI().getUsers().subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.d("apkapk", "doOnSusscribe fired!");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .subscribe(new Subscriber<UsersResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d("apkapk", "onCompleted fired!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("apkapk", "onError fired!");
                    }

                    @Override
                    public void onNext(UsersResponse usersResponse) {
                        Log.d("apkapk", "onNext fired! "+"Result code: "+usersResponse.getResultCode()+ "Users:"+usersResponse.getUsers().toString());
                        if(usersResponse.getResultCode()==0){
                            mPresenter.setUsers(usersResponse.getUsers());
                        } else {
                            mPresenter.showSetUsersError(usersResponse.getResultCode());
                        }

                        onCompleted();
                    }
                });
    }



    //on methods

    public void onUserSelect(User user) {

    }
}

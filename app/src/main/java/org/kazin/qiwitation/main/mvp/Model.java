package org.kazin.qiwitation.main.mvp;

import org.kazin.qiwitation.backend.CacheIT;
import org.kazin.qiwitation.backend.RetrofitHelper;
import org.kazin.qiwitation.object.User;
import org.kazin.qiwitation.object.UsersResponse;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by Alexey on 25.09.2015.
 */
public class Model {

    private static Model mModel;

    private Presenter mPresenter;

    //rxjava
    private Subscriber<UsersResponse> mUsersSubscriber;
    private Observable<UsersResponse> mUsersObservable;

    private Subscriber<List<User>> mUsersCachedSubscriber;
    private Observable<List<User>> mUsersCachedObservable;

    private boolean loadUsersInProgress = false;

    private CacheIT mCacheIT;


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

        if(getCacheIT().isUsersCached()){
            loadUsersFromCache();
        } else {
            loadUsersFromWeb();
        }

    }


    //on methods

    public void onUserSelect(User user) {
        //TODO
    }



    //
    private void loadUsersFromWeb(){
        if(!loadUsersInProgress){
            getUsersObservable().subscribe(getUserSubscriber());
        } else {
            mPresenter.showToast("Refresh task already in progress");
        }
    }

    private void loadUsersFromCache(){



    }

    //init
    private CacheIT getCacheIT(){
        if(mCacheIT == null){
            mCacheIT = new CacheIT();
        }
        return mCacheIT;
    }

    //rxjava

    private Subscriber<UsersResponse> getUserSubscriber(){
        if(mUsersSubscriber == null){
            mUsersSubscriber = new Subscriber<UsersResponse>() {
                @Override
                public void onCompleted() {
                    unsubscribe();
                    loadUsersInProgress = false;
                    mPresenter.unshowUserLoadingProgress();
                }

                @Override
                public void onError(Throwable e) {
                    loadUsersInProgress = false;
                    mPresenter.unshowUserLoadingProgress();
                }

                @Override
                public void onNext(UsersResponse usersResponse) {

                    if(usersResponse.getResultCode()==0){
                        mPresenter.setUsers(usersResponse.getUsers());
                    } else {
                        mPresenter.showSetUsersError(usersResponse.getResultCode());
                    }
                    onCompleted();
                }
            };
        }

        return mUsersSubscriber;
    }

    private Observable<UsersResponse> getUsersObservable(){
        if(mUsersObservable == null){
           mUsersObservable = RetrofitHelper.getIUserRestAPI()
                   .getUsers()
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .doOnSubscribe(new Action0() {
                       @Override
                       public void call() {
                           loadUsersInProgress = true;
                           mPresenter.showUserLoadingProgress();
                       }
                    })
                   .retry(5);
        }

        return mUsersObservable;
    }

    private Subscriber<List<User>> getUsersCachedSubscriber(){
        if(mUsersCachedSubscriber == null){
            mUsersCachedSubscriber = new Subscriber<List<User>>() {
                @Override
                public void onCompleted() {
                    unsubscribe();
                    loadUsersInProgress = false;
                    mPresenter.unshowUserLoadingProgress();
                }

                @Override
                public void onError(Throwable e) {
                    loadUsersInProgress = false;
                    mPresenter.unshowUserLoadingProgress();
                }

                @Override
                public void onNext(List<User> users) {
                    mPresenter.setUsers(users);
                }
            };
        }
        return mUsersCachedSubscriber;
    }

    private Observable<List<User>> getUsersCachedObservable(){
        if(mUsersCachedObservable == null){
            mUsersCachedObservable = Observable.create(new Observable.OnSubscribe<List<User>>() {
                @Override
                public void call(Subscriber<? super List<User>> subscriber) {
                    loadUsersInProgress = true;
                    try{
                        List<User> users = getCacheIT ().getCachedUsers();
                        subscriber.onNext(users);
                        subscriber.onCompleted();
                    } catch (Exception e){
                        subscriber.onError(e);
                    }

                }
            }).doOnSubscribe(new Action0() {
                @Override
                public void call() {
                    mPresenter.showUserLoadingProgress();
                }
            });
        }

        return mUsersCachedObservable;
    }


}

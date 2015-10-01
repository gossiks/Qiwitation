package org.kazin.qiwitation.main.mvp;

import org.kazin.qiwitation.backend.CacheIT;
import org.kazin.qiwitation.backend.RetrofitHelper;
import org.kazin.qiwitation.object.Balance;
import org.kazin.qiwitation.object.User;
import org.kazin.qiwitation.object.UserDetailResponse;
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

    private Subscriber<UserDetailResponse> mUserDetailSubscriber;
    private Observable<UserDetailResponse> mUserDetailObservable;

    private boolean loadUsersInProgress = false;
    private boolean loadBalancesInProgress = false;

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

    }

    public void onResume(){
        if(getCacheIT().isUsersCached()){
            loadUsersFromCache();
        } else {
            loadUsersFromWeb();
        }
    }


    //on methods

    public void onUserSelect(User user) {
        loadBalancesFromWeb(user.getId());
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
        if(!loadUsersInProgress){
            getUsersCachedObservable().subscribe(getUsersCachedSubscriber());
        } else {
            mPresenter.showToast("Refresh task already in progress");
        }
    }

    private void loadBalancesFromWeb(int user_id){
        if(!loadUsersInProgress){
            getUserBalancesObservable(user_id).subscribe(getUserBalancesSubscriber());
        } else {
            mPresenter.showToast("Refresh task already in progress");
        }
    }

    //init
    private CacheIT getCacheIT(){
        if(mCacheIT == null){
            mCacheIT = new CacheIT(mPresenter.getActivity());
        }
        return mCacheIT;
    }

    //rxjava

    private Subscriber<UsersResponse> getUserSubscriber(){
            mUsersSubscriber = new Subscriber<UsersResponse>() {
                @Override
                public void onCompleted() {
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
                        mCacheIT.cacheUsers(usersResponse.getUsers());
                        mPresenter.setUsers(usersResponse.getUsers());
                    } else {
                        mPresenter.showSetUsersError(usersResponse.getResultCode());
                    }
                }
            };

        return mUsersSubscriber;
    }

    private Observable<UsersResponse> getUsersObservable(){

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

        return mUsersObservable;
    }



    private Subscriber<List<User>> getUsersCachedSubscriber(){

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
                    mPresenter.showToast("Error with users cache load: "+e);
                }

                @Override
                public void onNext(List<User> users) {
                    mPresenter.setUsers(users);
                }
            };

        return mUsersCachedSubscriber;
    }

    private Observable<List<User>> getUsersCachedObservable(){

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


        return mUsersCachedObservable;
    }


    private Subscriber<UserDetailResponse> getUserBalancesSubscriber(){

            mUserDetailSubscriber = new Subscriber<UserDetailResponse>() {
                @Override
                public void onCompleted() {
                    unsubscribe();
                    loadBalancesInProgress = false;
                    mPresenter.unshowBalancesLoadingProgress();
                }

                @Override
                public void onError(Throwable e) {
                    loadBalancesInProgress = false;
                    mPresenter.unshowBalancesLoadingProgress();
                }

                @Override
                public void onNext(UserDetailResponse balances) {
                    if(balances.getResult_code()==0){
                        mPresenter.setBalances(balances.getBalances());
                    } else {
                        mPresenter.showSetUsersError(balances.getResult_code());
                    }
                }
            };

        return mUserDetailSubscriber;
    }

    private Observable<UserDetailResponse> getUserBalancesObservable(int userId){

            mUserDetailObservable = RetrofitHelper.getIUserDetailRestAPI()
                    .getUserDetail(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            loadBalancesInProgress = true;
                            mPresenter.showUserLoadingProgress();
                        }
                    })
                    .retry(5);


        return mUserDetailObservable;
    }

    public void onClickUsersRefresh() {
        loadUsersFromWeb();
    }
}

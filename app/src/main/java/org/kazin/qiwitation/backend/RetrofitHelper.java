package org.kazin.qiwitation.backend;

import org.kazin.qiwitation.object.UserDetailResponse;
import org.kazin.qiwitation.object.UsersResponse;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Alexey on 29.09.2015.
 */
public class RetrofitHelper {

    public static Retrofit mRetrofit;

    private static Retrofit getRetrofit(){
        if(mRetrofit ==null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://w.qiwi.com").addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }
        return mRetrofit;
    }

    public static IUserRestAPI getIUserRestAPI(){
        return getRetrofit().create(RetrofitHelper.IUserRestAPI.class);
    }

    public static IUserDetailRestAPI getIUserDetailRestAPI(){
        return getRetrofit().create(RetrofitHelper.IUserDetailRestAPI.class);
    }



    // Retrofit misc

    public interface IUserRestAPI{
        @GET("/mobile/testtask/index.json")
        Observable<UsersResponse> getUsers();
    }

    public interface IUserDetailRestAPI {
        @GET("/mobile/testtask/users/{user_id}/index.json")
        Observable<UserDetailResponse> getUserDetail(@Path("user_id") int user_base);
    }
}

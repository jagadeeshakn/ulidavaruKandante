package com.conflux.finflux.finflux.login.services;

import com.conflux.finflux.finflux.infrastructure.api.constants.APIEndPoint;
import com.conflux.finflux.finflux.login.data.User;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Praveen J U on 6/29/2016.
 */
public interface AuthService {
    @POST(APIEndPoint.AUTHENTICATION)
    Observable<User> authenticate(@Query("username") String username,
                                  @Query("password") String password);
}

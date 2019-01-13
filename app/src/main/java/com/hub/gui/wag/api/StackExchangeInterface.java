package com.hub.gui.wag.api;

import com.hub.gui.wag.model.Error;
import com.hub.gui.wag.model.ResponseWrapper;
import com.hub.gui.wag.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The StackExchange API Endpoints V 2.2
 * @see <a href= "https://api.stackexchange.com/docs">Online Documentation</a>
 */
public interface StackExchangeInterface {

    @GET("/users")
    Call<ResponseWrapper<User>> getUsers(@Query("site") String site,@Query("pagesize") int pageSize);

    @GET("/errors")
    Call<ResponseWrapper<Error>> getErrors();

}

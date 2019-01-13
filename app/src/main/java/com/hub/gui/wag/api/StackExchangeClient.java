package com.hub.gui.wag.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.hub.gui.wag.BuildConfig;

import java.lang.reflect.Type;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The StackExchange Client using Retrofit and Gson for deserialization
 */
public class StackExchangeClient {

    public static String BASE_URL = "https://api.stackexchange.com/2.2/";

    private static StackExchangeInterface sApiService;


    private static void buildStackExchangeClient(){

        //to parse the long to date
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsLong() * 1000);
            }
        });
        Gson gson = gsonBuilder.create();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if(BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        else{
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        sApiService = retrofit.create(StackExchangeInterface.class);
    }

    public static StackExchangeInterface getService(){
        if(sApiService == null)
            buildStackExchangeClient();

        return sApiService;
    }

}

package com.demo.retrofitwithrecyclerview.retrofit;
import com.demo.retrofitwithrecyclerview.utils.Utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String TAG = RetrofitClient.class.getSimpleName();
    private static RetrofitClient instance;
    private static RetrofitInterface apiService;

    /**
     * Get Instance of RetrofitClient
     *
     * @return active getInstance
     */
    public static RetrofitClient instance() {
        if (instance == null) {
            instance = new RetrofitClient();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
            Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(Utils.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
            apiService = mRetrofit.create(RetrofitInterface.class);
        }
        return instance;
    }

    /**
     * Access Interface method
     *
     * @return Interface's getInstance
     */
    public RetrofitInterface getAPIServiceInstance() {
        return apiService;
    }

}

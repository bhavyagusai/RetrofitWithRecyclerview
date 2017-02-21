package com.demo.retrofitwithrecyclerview.retrofit;

import com.demo.retrofitwithrecyclerview.model.DashboardResponseModel;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Android Dev on 2/1/2017.
 */

public interface RetrofitInterface {

    String API_PATH = "/phone_call/api/"; //Test server

    String dashboardAPIURL = API_PATH + "call_details";

    @FormUrlEncoded
    @POST(dashboardAPIURL)
    Call<DashboardResponseModel> dashboard(
            @FieldMap Map<String, String> keyValuesMap);


}

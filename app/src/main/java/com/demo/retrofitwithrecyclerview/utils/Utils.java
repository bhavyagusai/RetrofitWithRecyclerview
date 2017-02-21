package com.demo.retrofitwithrecyclerview.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;

import com.demo.retrofitwithrecyclerview.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Android Dev on 2/1/2017.
 */

public class Utils {
    public static final String SERVER_URL = "https://test1.rettest.com";
    public static final String INT_EXTRA_IS_FROM_LOGIN = "IsFromLogin";
    //public static final String SERVER_URL = "https://test1.rettest.com"; //live server
    public static final String AUTHORIZATION_VALUE = "";


    public static boolean isInternetAvailable(Context mContext) {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void displayNoInternetDialog(Context mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//        builder.setTitle(mContext.getString(R.string.dialog_error_no_internet_title));
        builder.setMessage(mContext.getString(R.string.dialog_error_no_internet_description));
        builder.setCancelable(false);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    public static boolean isSuccessResponse(String stringResponse) {
        try {
            JSONObject responseJSONObject = new JSONObject(stringResponse);
            if (responseJSONObject.getBoolean(API.KEY_STATUS)) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static <T> T fromJson(String json, Class<T> clas) throws Exception {
        T t = new Gson().fromJson(json, clas);
        return t;
    }

    /**
     * Convert Class object to Json string.
     * To json string.
     *
     * @param json the json
     * @return the string
     * @throws Exception the exception
     */
    public static String toJson(Object json) {
        return new Gson().toJson(json);
    }

    public static void displayToastMessage(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public static void displayServerErrorDialog(Context mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//        builder.setTitle(mContext.getString(R.string.dialog_error_server_title));
        builder.setMessage(mContext.getString(R.string.dialog_error_server_description));
        builder.setCancelable(false);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

}

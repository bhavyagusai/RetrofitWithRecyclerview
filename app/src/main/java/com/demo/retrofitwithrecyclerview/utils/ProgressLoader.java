package com.demo.retrofitwithrecyclerview.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Developer2 on 12/30/2016.
 */
public class ProgressLoader {
    private static ProgressLoader ourInstance = new ProgressLoader();
    private ProgressDialog mProgressDialog;


    public static ProgressLoader getInstance() {
        return ourInstance;
    }

    private ProgressLoader() {
    }

    /**
     * Show progress dialog.
     *
     * @param mContext the m context
     * @param message  the message
     */
    public void showProgressDialog(Context mContext, String message) {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage(message);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    /**
     * Dismiss progress dialog.
     */
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}

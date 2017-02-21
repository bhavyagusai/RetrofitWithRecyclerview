package com.demo.retrofitwithrecyclerview.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * Created by Chirag Prajapati on 01-Feb-17.
 */
public class Dialogs {
    private static Dialogs ourInstance = new Dialogs();

    public static Dialogs getInstance() {
        return ourInstance;
    }

    private AlertDialogListener alertDialogListener;
    private OnPositiveListener onPositiveListener;
    private DialogListener dialogListener;

    private Dialogs() {
    }


    public void alertDialog(Context context, String title, String message) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    /**
     * Show positive alert.
     *
     * @param context            the context
     * @param buttonString       the butoon string
     * @param title              the title
     * @param messasge           the messasge
     * @param onPositiveListener the on positive call back
     */
    public void showPositiveAlert(Context context, String buttonString, String title, String messasge, final OnPositiveListener onPositiveListener) {
        this.onPositiveListener = onPositiveListener;
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setTitle(title);
        builder.setMessage(messasge);
        builder.setCancelable(false);
        builder.setPositiveButton(buttonString, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                onPositiveListener.setPositiveButtonClick();
            }
        });
        builder.show();
    }


    public void showBothAlert(Context context, String posButton, String negButton, String title, String messasge, final DialogListener dialogListener) {
        this.dialogListener = dialogListener;
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setTitle(title);
        builder.setMessage(messasge);
        builder.setCancelable(false);
        builder.setPositiveButton(posButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialogListener.onPositiveClick();
            }
        });
        builder.setNegativeButton(negButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onNegativeClick();
            }
        });
        builder.show();
    }

    public interface OnPositiveListener {
        void setPositiveButtonClick();
    }

    public interface AlertDialogListener {
        void onClick();
    }

    public interface DialogListener {
        void onPositiveClick();

        void onNegativeClick();
    }

}

package com.murat.moviedbapp.Utils;


import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class DialogUtil {

/*    public static void showSweetAlertDialogWithFinish(final Context context, String title, String message, String btnText, int type) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, type)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText(btnText)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        ((Activity) context).finish();
                    }
                });
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showSweetAlertDialog(final Context context, String title, String message, String btnText, int type) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, type)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText(btnText);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showSweetAlertDialogWithListener(final Context context, String title, String message, String btnText, int type, SweetAlertDialog.OnSweetClickListener onSweetClickListener) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, type)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText(btnText)
                .setConfirmClickListener(onSweetClickListener);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showYesNoSweetAlertDialog(final Context context, String title, String message, String confirmButtonText, String cancelButtonText, SweetAlertDialog.OnSweetClickListener confirmClickListener, SweetAlertDialog.OnSweetClickListener cancelClickListener, int type) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, type)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText(confirmButtonText)
                .setCancelText(cancelButtonText)
                .setConfirmClickListener(confirmClickListener)
                .setCancelClickListener(cancelClickListener);
        dialog.setCancelable(false);
        dialog.show();
    }*/

    public static SweetAlertDialog showProgressDialog(Context context, String text, int colorResId) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(ContextCompat.getColor(context, colorResId));
        pDialog.setTitleText(text);
        pDialog.setCancelable(false);
        pDialog.show();

        return pDialog;
    }
}

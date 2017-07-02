package com.br.buscacep.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;

/**
 * Created by ezequ on 01/07/2017.
 */

public class LoadingUtil extends DialogFragment {
    private static ProgressDialog dialog;

    public static void show(Context context) {
        show(context, null, null);
    }

    public static void show(Context context, String title) {
        show(context, title, null);
    }

    public static void show(Context context, String title, String message) {
        dialog = new ProgressDialog(context);
        dialog.setMessage(title != null ? title : "Carregando...");
        dialog.setTitle(message != null ? message : "Por favor, aguarde.");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void hide() {
        dialog.dismiss();
    }
}

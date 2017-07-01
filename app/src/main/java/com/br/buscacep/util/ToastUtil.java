package com.br.buscacep.util;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by ezequ on 01/07/2017.
 */

public class ToastUtil {
    public static void show(AppCompatActivity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }
}

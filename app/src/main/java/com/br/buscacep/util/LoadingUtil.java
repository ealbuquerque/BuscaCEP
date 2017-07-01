package com.br.buscacep.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by ezequ on 01/07/2017.
 */

public class LoadingUtil extends DialogFragment {
    Dialog loading = null;

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        loading = new ProgressDialog(getActivity());
        loading.setTitle("Por favor, aguarde...");
        loading.setCanceledOnTouchOutside(false);
        loading.setCancelable(false);
        return loading;
    }
}

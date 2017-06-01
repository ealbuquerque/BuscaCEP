package com.br.buscacep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.br.buscacep.util.CustomMasks;

public class MainActivity extends AppCompatActivity {
    private EditText textCEP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCEP = (EditText) findViewById(R.id.editTextCEP);
        textCEP.addTextChangedListener(CustomMasks.insert(CustomMasks.CEP_MASK, textCEP));
    }
}

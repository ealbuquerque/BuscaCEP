package com.br.buscacep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by ezequ on 13/06/2017.
 */

public class SearchCepActivity extends AppCompatActivity {
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_cep_activity);

        mTvResult = (TextView) findViewById(R.id.textViewCep);

        Bundle args = getIntent().getExtras();
        String toScreen = "CEP: " + args.getString("cep") + "\n"
                +"Logradouro: " + args.getString("street") + "\n"
                +"Bairro: " + args.getString("neighborhood")+ "\n"
                +"Cidade: " + args.getString("city")+ "\n"
                +"Estado: " + args.getString("state");
        mTvResult.setText(toScreen);
    }
}
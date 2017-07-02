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
        mTvResult.setText(args.getString("address"));
    }
}
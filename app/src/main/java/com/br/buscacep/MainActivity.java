package com.br.buscacep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.br.buscacep.util.CustomMasks;

public class MainActivity extends AppCompatActivity {
    private EditText textCEP;
    private Button buttonSearch;
    private Button buttonHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCEP = (EditText) findViewById(R.id.editTextCEP);
        textCEP.addTextChangedListener(CustomMasks.insert(CustomMasks.CEP_MASK, textCEP));

        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchCepActivity.class);
                startActivity(intent);
            }
        });

        buttonHistory = (Button) findViewById(R.id.buttonHistory);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}

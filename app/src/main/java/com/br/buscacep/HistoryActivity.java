package com.br.buscacep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by ezequ on 13/06/2017.
 */

public class HistoryActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        listView = (ListView) findViewById(R.id.listView);
//        List<Address> addresses = AddressDAO.

    }
}

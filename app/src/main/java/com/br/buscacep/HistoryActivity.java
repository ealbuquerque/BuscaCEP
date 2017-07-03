package com.br.buscacep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.br.buscacep.db.AddressDAO;
import com.br.buscacep.model.Address;

import java.util.List;

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

        AddressDAO addressDAO = new AddressDAO();

        List<Address> addresses = addressDAO.getAllAddresses();

        ArrayAdapter<Address> adapter = new ArrayAdapter<Address>(this, android.R.layout.simple_list_item_1, addresses);

        listView.setAdapter(adapter);

    }
}

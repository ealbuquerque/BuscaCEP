package com.br.buscacep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
    List<Address> addresses;
    ArrayAdapter<Address> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        listView = (ListView) findViewById(R.id.listView);
        addresses = AddressDAO.getAllAddresses();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addresses);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goSearchCepResult((Address) adapterView.getItemAtPosition(i));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        addresses = AddressDAO.getAllAddresses();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addresses);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void goSearchCepResult(Address address) {
        Intent intent = new Intent(HistoryActivity.this, SearchCepActivity.class);

        Bundle params = new Bundle();
        params.putString("address", address.toString());
        intent.putExtras(params);

        startActivityForResult(intent, 0);
    }
}

package com.br.buscacep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.br.buscacep.db.AddressDAO;
import com.br.buscacep.model.Address;
import com.br.buscacep.util.ListViewAdapter;

import java.util.List;

/**
 * Created by ezequ on 13/06/2017.
 */

public class HistoryActivity extends AppCompatActivity {
    ListView listView;
    List<Address> addresses;
    ListViewAdapter listviewadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        addresses = AddressDAO.getAllAddresses();

        listView = (ListView) findViewById(R.id.listView);
        addresses = AddressDAO.getAllAddresses();
        listviewadapter =  new ListViewAdapter(this, android.R.layout.simple_list_item_1, addresses);
        listView.setAdapter(listviewadapter);
        listviewadapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goSearchCepResult((Address) adapterView.getItemAtPosition(i));
            }
        });

        // Binds the Adapter to the ListView
        listView.setAdapter(listviewadapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // Capture ListView item click
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = listView.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                listviewadapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = listviewadapter
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Address selecteditem = listviewadapter
                                        .getItem(selected.keyAt(i));
                                // Remove selected items following the ids
                                listviewadapter.remove(selecteditem);
                            }
                        }
                        // Close CAB
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.activity_main, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
                listviewadapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        addresses = AddressDAO.getAllAddresses();
//        listviewadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addresses);
//        listView.setAdapter(listviewadapter);
//        listviewadapter.notifyDataSetChanged();
    }

    private void goSearchCepResult(Address address) {
        Intent intent = new Intent(HistoryActivity.this, SearchCepActivity.class);

        Bundle params = new Bundle();
        params.putString("address", address.toString());
        intent.putExtras(params);

        startActivityForResult(intent, 0);
    }
}

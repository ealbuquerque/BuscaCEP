package com.br.buscacep.util;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import com.br.buscacep.model.Address;

import java.util.List;

/**
 * Created by Maurício on 04/07/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Address> {
    Context context;
    LayoutInflater inflater;
    List<Address> addressList;
    private SparseBooleanArray mSelectedItemsIds;

    public ListViewAdapter(Context context, int resourceId,
                           List<Address> addressList) {
        super(context, resourceId, addressList);
        mSelectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.addressList = addressList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void remove(Address object) {
        addressList.remove(object);
        notifyDataSetChanged();
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}

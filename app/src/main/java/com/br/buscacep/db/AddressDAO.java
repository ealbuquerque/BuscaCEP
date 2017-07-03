package com.br.buscacep.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.buscacep.model.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ezequ on 02/07/2017.
 */

public class AddressDAO {
    private List<Address> addressList;
    private int position;

    public AddressDAO() {
        addressList = new ArrayList<Address>();
        reset();
    }

    public void loadDB() {
        clear();
        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            Cursor result = conn.query("address", null, null, null, null, null, "id");
            while (result.moveToNext()) {
                addressList.add(new Address(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(Address address) {
        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            String query = "insert into address (cep, street, neighborhood, city, state) values ('";
            query += address.getCep() + "', '";
            query += address.getStreet() + "', '";
            query += address.getNeighborhood() + "', '";
            query += address.getCity() + "', '";
            query += address.getState() + "');";
            conn.execSQL(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Address get(int id) {
        for (int i = 0; i < addressList.size(); i++) {
            Address address = addressList.get(i);
            if (address.getId() == id) {
                return address;
            }
        }
        return null;
    }

    public Address getByCep(String cep) {
        for (int i = 0; i < addressList.size(); i++) {
            Address address = addressList.get(i);
            if (cep.equals(address.getCep())) {
                return address;
            }
        }
        return null;
    }

        public List<Address> getAllAddresses (){
            List<Address> retorno = new ArrayList<>();
            try{
                SQLiteDatabase conn = DatabaseConnection.getConnection();
                String query = "select * from address";
                Cursor cursor = conn.rawQuery(query, null);

                retorno = new ArrayList<>();
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        Address address = new Address(cursor);
                        retorno.add(address);
                        cursor.moveToNext();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return retorno;
        }


    public void update(Address address) {
        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            String query = "update address set cep='" + address.getCep();
            query += "', street='" + address.getStreet();
            query += "', neighborhood='" + address.getNeighborhood();
            query += "', city='" + address.getCity();
            query += "', state='" + address.getState();
            query += "' where id='" + address.getId() + "';";
            conn.execSQL(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Address address) {
        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            String query = "delete from address where id='" + address.getId() + "';";
            conn.execSQL(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            String query = "delete from address;";
            conn.execSQL(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Address next() {
        Address address = null;
        if (position < addressList.size()) {
            address = addressList.get(position);
            position++;
        }
        return address;
    }

    public void reset() {
        position = 0;
    }

    public int size() {
        return addressList.size();
    }

    public void clear() {
        addressList.clear();
        deleteAll();
        reset();
    }
}
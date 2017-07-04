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
    public static void insert(Address address) {
        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            String query = "insert into address (cep, street, neighborhood, city, state) values (";
            query += "'" + address.getCep() + "',";
            query += "'" + address.getStreet() + "',";
            query += "'" + address.getNeighborhood() + "',";
            query += "'" + address.getCity() + "',";
            query += "'" + address.getState() + "');";
            conn.execSQL(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Address getByCep(String cep) {
        Address result = null;

        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            Cursor cursor = conn.rawQuery("SELECT * FROM address WHERE cep = '" + cep + "';", null);

            if (cursor.moveToFirst()) {
                result = new Address(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Address> getAllAddresses() {
        List<Address> result = new ArrayList<>();

        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            Cursor cursor = conn.rawQuery("SELECT * FROM address;", null);

            while (cursor.moveToNext()) {
                Address address = new Address(cursor);
                result.add(address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public static void delete(Address address) {
        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            String query = "DELETE FROM address WHERE id=" + address.getId() + ";";
            conn.execSQL(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAll() {
        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            String query = "DELETE FROM address;";
            conn.execSQL(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int size() {
        int result = 0;

        try {
            SQLiteDatabase conn = DatabaseConnection.getConnection();
            Cursor cursor = conn.rawQuery("SELECT count(*) FROM address;", null);
            cursor.moveToFirst();
            result = cursor.getInt(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
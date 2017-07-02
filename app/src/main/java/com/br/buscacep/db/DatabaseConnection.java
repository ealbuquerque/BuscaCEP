package com.br.buscacep.db;

/**
 * Created by ezequ on 27/06/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnection {
    static private SQLiteDatabase connection = null;
    static private String database = "";
    static private Context context;

    public static void setConfig(String db, Context c) {
        database = db;
        context = c;
    }

    public static SQLiteDatabase getConnection() {
        String table = "create table if not exists address (";
        table += "id integer not null primary key autoincrement,";
        table += "cep varchar(9),";
        table += "street varchar(100), ";
        table += "neighborhood varchar(100),";
        table += "city varchar(100),";
        table += "state varchar(100));";

        if (connection == null) {
            try {
                connection = context.openOrCreateDatabase(database, 0, null);
                connection.execSQL(table);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return connection;
    }
}
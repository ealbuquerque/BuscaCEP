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
    static private String table = "create table if not exists address ("
            + "id integer not null primary key autoincrement,"
            + "cep varchar(100), "
            + "street varchar(100), "
            + "neighborhood varchar(100), "
            + "city varchar(100), "
            + "state varchar(100);";

    public static void setConfig(String db, Context c) {
        database = db;
        context = c;
    }

    public static SQLiteDatabase getConnection() {
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
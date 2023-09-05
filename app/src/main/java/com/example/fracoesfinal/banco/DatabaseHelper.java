package com.example.fracoesfinal.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pergunta.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pergunta (id INTEGER PRIMARY KEY AUTOINCREMENT, perguntaPrincipal TEXT, parte1 TEXT, parte2 TEXT, parte3 TEXT, resposta_correta TEXT, resposta_parte1 TEXT, resposta_parte2 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table
        db.execSQL("DROP TABLE IF EXISTS pergunta");

        // Create a new table
        onCreate(db);
    }
}
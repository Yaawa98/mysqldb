package com.example.mydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tView = findViewById(R.id.tView);

        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Phone", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS contacts(name VARCHAR, number INT(10))");
            myDatabase.execSQL("INSERT INTO contacts(name, number) VALUES ('Charles', 0249766734)");
            myDatabase.execSQL("INSERT INTO contacts(name, number) VALUES ('Gifty', 0249103392)");
            myDatabase.execSQL("INSERT INTO contacts(name, number) VALUES ('Anita', 0249103392)");

            Cursor display = myDatabase.rawQuery("SELECT * FROM contacts", null);
            int contactIndex = display.getColumnIndex("name");
            int numberIndex = display.getColumnIndex("number");

            display.moveToFirst();
            while (display!=null){
                tView.setText(display.getString(contactIndex));
                Log.i("name", display.getString(contactIndex));
                Log.i("number", Integer.toString(display.getInt(numberIndex)));

                display.moveToNext();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

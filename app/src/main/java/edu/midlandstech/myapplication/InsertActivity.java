package edu.midlandstech.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }
    public void insert(View v) {
        EditText unitET = findViewById(R.id.etUnit);
        EditText factorET = findViewById(R.id.etFactor);
        String unit = unitET.getText().toString();
        double factor = Double.parseDouble(factorET.getText().toString());
        CurrencyDB db = new CurrencyDB(this);
        db.insertMeasure(unit,factor);
    }
    public void close(View v) {
        this.finish();
    }
}
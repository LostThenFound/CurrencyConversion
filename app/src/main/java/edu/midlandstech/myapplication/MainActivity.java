package edu.midlandstech.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CurrencyDB db;  // the db object
    protected static Currency fromCurrency;
    protected static Currency toCurrency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new CurrencyDB(this);
        ArrayList<Currency> list = db.selectAll();
        fromCurrency = list.get(3);
        toCurrency = list.get(3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id==R.id.from){

            Intent fromActivity = new Intent(this, FromActivity.class);
            this.startActivity(fromActivity);
            return true;
        }
        if (id==R.id.to){

            Intent toActivity = new Intent(this, ToActivity.class);
            this.startActivity(toActivity);
            return true;
        }
        if (id==R.id.add) {
            Intent addActivity = new Intent(this,InsertActivity.class);
            this.startActivity(addActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void convert(View v) {

        DecimalFormat df = new DecimalFormat("#,###.0");

        TextView answer = findViewById(R.id.answer);

        EditText amountStr = findViewById(R.id.etAmount);

        double amount = Double.parseDouble(amountStr.getText().toString());

        double result = toCurrency.fromUSD(fromCurrency.toUSD(amount));

        answer.setText(df.format(amount) + " " + fromCurrency.getUnit() + " is "
                + df.format(result) + " " + toCurrency.getUnit()
        );
    }
}

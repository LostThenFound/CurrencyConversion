package edu.midlandstech.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class CurrencyDB extends SQLiteOpenHelper {
    public static final String QUERY1 = "create table unit ( id integer primary key autoincrement, unit text, factor real)";
    public static final String QUERY2 = "insert into unit (unit, factor) values ('EUR',1.1019), ('GBP', 1.288), ('INR', .01388), ('USD', 1), ('CAD', .7546);";
    public CurrencyDB(Context context){
        super(context, "CurrencyDB", null, 3);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(QUERY1);

        sqLiteDatabase.execSQL(QUERY2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists unit;");
        onCreate(sqLiteDatabase);
    }

    public void insertMeasure(String unit, double factor)  {
        String query = "insert into unit (unit, factor) values ('"
                + unit + "' ," + factor + ");";
        SQLiteDatabase db = this.getWritableDatabase( );
        db.execSQL(query);
        Log.w("MainActivity",query);
    }

    public ArrayList<Currency> selectAll( ) {
        String sqlQuery = "select * from unit";

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Currency> currencies = new ArrayList<>( );

        while( cursor.moveToNext( ) ) {
            Currency currentCurrency
                    = new Currency( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getDouble( 2 ) );
            currencies.add(currentCurrency);
        }
        db.close( );
        return currencies;
    }
}

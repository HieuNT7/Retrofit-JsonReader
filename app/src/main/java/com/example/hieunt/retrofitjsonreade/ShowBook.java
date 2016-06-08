package com.example.hieunt.retrofitjsonreade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowBook extends AppCompatActivity {
    private TextView textViewBookId, textViewBookName, textViewBookPrice, textViewBookQuantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);

        textViewBookId = (TextView) findViewById(R.id.textViewId);
        textViewBookName = (TextView) findViewById(R.id.textViewName);
        textViewBookPrice = (TextView) findViewById(R.id.textViewPrice);
        textViewBookQuantity = (TextView) findViewById(R.id.textViewQuantity);

        Intent intent = getIntent();
        int x = intent.getIntExtra(MainActivity.KEY_BOOK_ID,0);

        textViewBookId.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_BOOK_ID,0)));
        textViewBookName.setText(intent.getStringExtra(MainActivity.KEY_BOOK_NAME));
        textViewBookPrice.setText(intent.getStringExtra(MainActivity.KEY_BOOK_PRICE));
        textViewBookQuantity.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_BOOK_QUANTITY,0)));
    }
}

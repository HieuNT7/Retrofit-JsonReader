package com.example.hieunt.retrofitjsonreade;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    //http://www.json-generator.com/api/json/get/bSebvpSyPS?indent=2
    //http://www.objgen.com/json/models/c1R
    public static final String ROOT_URL = "http://www.json-generator.com";


    public static final String KEY_BOOK_ID = "key_book_id";
    public static final String KEY_BOOK_NAME = "key_book_name";
    public static final String KEY_BOOK_PRICE = "key_book_price";
    public static final String KEY_BOOK_QUANTITY = "key_book_quantity";

    private ListView listView;
    private List<Book> lst_books;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getcontrol();
        getBooks();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ShowBook.class);
                Book book = lst_books.get(position);

                intent.putExtra(KEY_BOOK_ID, book.getId());
                intent.putExtra(KEY_BOOK_NAME, book.getName());
                intent.putExtra(KEY_BOOK_PRICE, book.getPrice());
                intent.putExtra(KEY_BOOK_QUANTITY, book.getQuantity());

                startActivity(intent);
            }
        });
    }

    private void getBooks() {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading data", "Wait some seconds...", false, false);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ROOT_URL).build();

        BookAPI api = adapter.create(BookAPI.class);

        api.getBooks(new Callback<List<Book>>() {
            @Override
            public void success(List<Book> books, Response response) {
                loading.dismiss();

                lst_books = books;

                showList();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.wtf(error.toString(), error);
            }
        });
    }

    private void showList() {
        String[] items = new String[lst_books.size()];

        for(int i=0; i<lst_books.size(); i++)
        {
            items[i] = lst_books.get(i).getName();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_book_list, items);
        listView.setAdapter(adapter);
    }


    private void getcontrol() {
        listView = (ListView) findViewById(R.id.listViewBooks);
    }
}

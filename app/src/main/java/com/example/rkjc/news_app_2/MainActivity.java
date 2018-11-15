package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView; // Recycler view
    private NewsRecyclerViewAdapter adapter;
    private ArrayList<NewsItem> newsItems = new ArrayList<>();// ArrayList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connecting object with Recycler view XML
        recyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);

        // Populating adapter with arrayList(newsItems)
        adapter = new NewsRecyclerViewAdapter(newsItems, this);




    }

    // Viewing Recycler View - Parsing JSON file.
    public void populateRecyclerView(String searchResults){
        newsItems = JsonUtils.parseNews(searchResults);
        adapter.newsItemsList.addAll(newsItems); // Adding items of array to adapter.
        adapter.notifyDataSetChanged();
    }


    // Inflating Menu Items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override //Takes care of refresh click. This Starts the query task
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        // If clicked, build URL and start Async. Passing URL to Async
        if (itemThatWasClickedId == R.id.action_search) {
            URL url = NetworkUtils.buildUrl();
            new NewsQueryTask().execute(url);
        }
        return super.onOptionsItemSelected(item);
    }

    public class NewsQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        // Returns URL
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String articleResults = "";

            try {
               articleResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return articleResults;

        }

        @Override
        protected void onPostExecute(String articleResults) {
            if (articleResults != null && !articleResults.equals("")) {
               populateRecyclerView(articleResults);
            }
        }
    }





}
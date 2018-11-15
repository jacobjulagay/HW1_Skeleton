package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<NewsItem> parseNews(String JSONString){
        ArrayList<NewsItem> newsList = new ArrayList<>(); // Array List of newsItems

        try{
            // Initialize JSONObject
            JSONObject mainJSONObject = new JSONObject(JSONString);

            //Array of articles in JSON file
            JSONArray items = mainJSONObject.getJSONArray("articles");

            // Going through Array of JSON
            for(int i = 0; i < items.length(); i++){
                JSONObject item = items.getJSONObject(i); // Creating JSONObject to get single user Data.

                // Populate ArrayList with fields
                newsList.add(new NewsItem(item.getString("author"), item.getString("title"), item.getString("description")
                        , item.getString("url"), item.getString("urlToImage"), item.getString("publishedAt")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newsList; // Returning ArrayList of NewsItes

    }

}



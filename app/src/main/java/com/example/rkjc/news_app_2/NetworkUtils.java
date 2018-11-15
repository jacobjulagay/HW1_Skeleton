package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

    public class NetworkUtils {

        //https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=b76de96df47d4c1f911fcf651957fbb6

        // News api
        final static String news_api_key = "apiKey";
        final static String GITHUB_BASE_URL =
                "https://newsapi.org/v1/articles";

        final static String PARAM_QUERY = "source";

        final static String PARAM_SORT = "sortBy";
        final static String sortBy = "latest";


        // Task 2
        public static URL buildUrl() {
            Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                    .appendQueryParameter(PARAM_QUERY, "the-next-web")
                    .appendQueryParameter(PARAM_SORT, sortBy)
                    .appendQueryParameter(news_api_key, "b76de96df47d4c1f911fcf651957fbb6")
                    .build();

            URL url = null;
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return url;
        }


        // Task 2
        public static String getResponseFromHttpUrl(URL url) throws IOException{

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream in = urlConnection.getInputStream();

                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    return scanner.next();
                } else {
                    return null;
                }
            }finally{
                    urlConnection.disconnect();
                }

            }




        }



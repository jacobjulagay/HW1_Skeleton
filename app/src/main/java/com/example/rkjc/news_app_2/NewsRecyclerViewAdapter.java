package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {


    ArrayList<NewsItem> newsItemsList; // ArrayList only taking NewsItems
    Context context;

    // Constructor for ArrayList and Context
    public NewsRecyclerViewAdapter(ArrayList<NewsItem> newsItemsList, Context context) {
        this.newsItemsList = newsItemsList;
        this.context = context;
    }


    @NonNull
    @Override // Creating View Holder which consists of news_items.xml
    public NewsRecyclerViewAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        // Layout from XML news_item
        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override //Bind data to the position of the data.
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    //Returning arrayList size
    public int getItemCount() {
        return newsItemsList.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // From news_item.xml
        TextView title;
        TextView description;
        TextView date;

        // Creating a connection ViewHolder with news_item.XML
        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
        }
        // Bind data
        void bind(final int listIndex) {
            title.setText(newsItemsList.get(listIndex).getTitle());
            description.setText(newsItemsList.get(listIndex).getDescription());
            date.setText(newsItemsList.get(listIndex).getPublishedAt());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String urlString = newsItemsList.get(getAdapterPosition()).getUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
            context.startActivity(intent);
        }
    }

}

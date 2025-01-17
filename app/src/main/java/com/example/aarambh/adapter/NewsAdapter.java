package com.example.aarambh.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aarambh.R;
import com.example.aarambh.activities.NewsDetailsActivity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final List<News> newsList;

    public NewsAdapter(List<News> newsList){
        this.newsList=newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.news,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.tvNewsName.setText("Tet exam will conducted on 12 dec.");
        holder.tvNewsDate.setText("2 days ago");
        holder.imgProduct1.setImageResource(R.drawable.place_holder_icon);
        holder.imgProduct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), NewsDetailsActivity.class);
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView tvNewsName,tvNewsDate;
        ImageView imgProduct1;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNewsName=itemView.findViewById(R.id.tvNewsName);
            tvNewsDate=itemView.findViewById(R.id.tvNewsDate);
            imgProduct1=itemView.findViewById(R.id.img_product1);
        }
    }
    // News model class (can be moved to a separate file)
    public static class News {
        private final String title;
        private final String date;
        private final int imageResId;

        public News(String title, String date, int imageResId) {
            this.title = title;
            this.date = date;
            this.imageResId = imageResId;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

        public int getImageResId() {
            return imageResId;
        }
    }
}


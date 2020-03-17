package com.example.sellerspage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Profile> profiles;

    public  MyAdapter(Context context,ArrayList<Profile> profiles) {
        this.context = context;
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.listview,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.bookName.setText(profiles.get(position).getBookName());
        holder.authorName.setText(profiles.get(position).getImage());
        holder.bookName.setText(profiles.get(position).getBookName());
        Picasso.get().load(profiles.get(position).getImage()).into(holder.imgShow);
        holder.onClick(context,position);
        //Toast.makeText(context,profiles.get(position).getImage(),Toast.LENGTH_SHORT).show();
        //Picasso.with(this).load(/* url of image */).into(/*your imageview id*/);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookName,authorName;
        ImageView imgShow;
        Button btnShowMore;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            bookName = itemView.findViewById(R.id.txtBookName);
            authorName = itemView.findViewById(R.id.txtAuthorName);
            imgShow = itemView.findViewById(R.id.imgShow);
            btnShowMore = itemView.findViewById(R.id.btnShowMore);
        }

        public  void onClick(final Context context,int position) {
            btnShowMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater.from(context)
                            .inflate(R.layout.viewlayout,null);
                }
            });
        }








    }




}

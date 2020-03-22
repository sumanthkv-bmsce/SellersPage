package com.example.sellerspage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;

class testing extends RecyclerView.Adapter<testing.MyViewHolder>{

    Context context;
    static ArrayList<Profile> profiles;
    Fragment fragment;
    static  int pos = -1;

    public  testing(Context context,ArrayList<Profile> profiles,Fragment fragment) {
        this.context = context;
        this.profiles = profiles;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.listview,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Picasso.get().load(profiles.get(position).getImage1()).into(holder.imgShow);
        holder.authorName.setText(profiles.get(position).getAuthorName());
        holder.bookName.setText(profiles.get(position).getBookName());
        Uri uri = Uri.parse(profiles.get(position).getImage1());
        holder.imgShow.setImageURI(uri);

        holder.btnShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlankFragment fragment1 = new BlankFragment();
                Bundle args = new Bundle();
                args.putString("Key", String.valueOf(position));
                fragment1.setArguments(args);
                pos = position;

                NavHostFragment.findNavController(fragment)
                        .navigate(R.id.action_listFragment_to_blankFragment);
            }
        });

    }



    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookName,authorName;
        ImageView imgShow;
        Button btnShowMore;
        TextView txtAuthorName;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            bookName = itemView.findViewById(R.id.txtBookName);
            authorName = itemView.findViewById(R.id.txtAuthorName);
            imgShow = itemView.findViewById(R.id.imgShow);
            txtAuthorName = itemView.findViewById(R.id.txtAuthorName);
            btnShowMore = itemView.findViewById(R.id.btnShowMore);

        }
    }



}

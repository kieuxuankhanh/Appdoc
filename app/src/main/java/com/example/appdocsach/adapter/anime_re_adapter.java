package com.example.appdocsach.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.appdocsach.R;
import com.example.appdocsach.object.anime_re;

import java.util.ArrayList;

public class anime_re_adapter extends RecyclerView.Adapter<anime_re_adapter.Viewholder> {
    ArrayList<anime_re> items;
    Context context;

    public anime_re_adapter(ArrayList<anime_re> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public anime_re_adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_anime_adapter,parent,false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull anime_re_adapter.Viewholder holder, int position) {
        holder.txttentruyen.setText(items.get(position).getTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(30));

        Glide.with(context)
                .load(items.get(position).getPoster())
                .apply(requestOptions)
                .into(holder.imagetruyen);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView txttentruyen,txttenchap;
        ImageView imagetruyen;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txttentruyen = itemView.findViewById(R.id.txttentruyen);
            txttenchap = itemView.findViewById(R.id.txttenchap);
            imagetruyen = itemView.findViewById(R.id.imagetruyen);
        }
    }
}

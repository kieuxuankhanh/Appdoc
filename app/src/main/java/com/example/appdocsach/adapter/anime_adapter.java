package com.example.appdocsach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.appdocsach.R;
import com.example.appdocsach.object.anime;

import java.util.ArrayList;
import java.util.List;

public class anime_adapter extends ArrayAdapter<anime> {
    private Context ct;
    private ArrayList<anime> arr;

    public anime_adapter(@NonNull Context context, int resource, @NonNull List<anime> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_anime_adapter, null);
        }
        if (arr.size() > 0) {
            anime truyenTranh = this.arr.get(position);

            TextView tenTruyen = convertView.findViewById(R.id.txttentruyen);
            TextView tenChap = convertView.findViewById(R.id.txttenchap);
            ImageView imgTruyen = convertView.findViewById(R.id.imagetruyen);

            tenTruyen.setText(truyenTranh.getTentruyen());
            tenChap.setText(truyenTranh.getTenchap());
            Glide.with(this.ct).load(truyenTranh.getLinkanh()).into(imgTruyen);

        }

        return convertView;
    }
}

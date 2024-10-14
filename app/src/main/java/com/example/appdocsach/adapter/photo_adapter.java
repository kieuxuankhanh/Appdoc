package com.example.appdocsach.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appdocsach.R;
import com.example.appdocsach.object.photo;

import java.util.List;

public class photo_adapter extends PagerAdapter {
    private List<photo> Listphoto;

    public photo_adapter(List<photo> listphoto) {
        Listphoto = listphoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.image_item, container,false);
        ImageView imgphoto = view.findViewById(R.id.ImageView);
        photo PhoTo = Listphoto.get(position);
        imgphoto.setImageResource(PhoTo.getResourceID());

        //them view
        container.addView(view);

        return view;
    }
    @Override
    public int getCount() {
        if (Listphoto != null)
        {
            return Listphoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

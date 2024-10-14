package com.example.appdocsach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.appdocsach.R;
import com.example.appdocsach.object.Slider_items;

import java.io.File;
import java.util.List;

public class Slider_Adapter extends RecyclerView.Adapter<Slider_Adapter.SliderViewHolder> {
    private List<Slider_items> sliderItems;
    private ViewPager2 viewPager2;
    private Context context;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };

    public Slider_Adapter(List<Slider_items> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public Slider_Adapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_viewholder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Slider_Adapter.SliderViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
        if (position==sliderItems.size()-2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagetruyen;
        private TextView txttentruyen,txttenchap,txttheloai;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imagetruyen = itemView.findViewById(R.id.imagetruyen);
            txttentruyen = itemView.findViewById(R.id.txttentruyen);
            txttenchap = itemView.findViewById(R.id.txttenchap);
            txttheloai = itemView.findViewById(R.id.txttheloai);
        }
        void setImage(Slider_items sliderItems){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(60));
            Glide.with(context)
                    .load(sliderItems.getImage())
                    .apply(requestOptions)
                    .into(imagetruyen);

            txttentruyen.setText(sliderItems.getName());
            txttheloai.setText(sliderItems.getGenre());
            txttenchap.setText(sliderItems.getChapter());
        }
    }
}

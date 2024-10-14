package com.example.appdocsach.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appdocsach.R;
import com.example.appdocsach.adapter.Slider_Adapter;
import com.example.appdocsach.adapter.anime_adapter;
import com.example.appdocsach.adapter.anime_re_adapter;
import com.example.appdocsach.adapter.photo_adapter;
import com.example.appdocsach.databinding.FragmentHomeBinding;
import com.example.appdocsach.object.Slider_items;
import com.example.appdocsach.object.anime;
import com.example.appdocsach.object.anime_re;
import com.example.appdocsach.object.photo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private anime_adapter apdapter;
    private anime_adapter apdapter1;
    private ArrayList<anime> truyentranhArrayList;
    private ArrayList<anime> truyendammyArrayList;
    private List<photo> listphoto;
    private FragmentHomeBinding binding;
    private FirebaseDatabase firebaseDatabase;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (binding.ViewPager.getCurrentItem() == listphoto.size() - 1) {
                binding.ViewPager.setCurrentItem(0);
            } else {
                binding.ViewPager.setCurrentItem(binding.ViewPager.getCurrentItem() + 1);
            }
            binding.viewpager2.setCurrentItem(binding.viewpager2.getCurrentItem() + 1);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();

        setupViewPager();
        init();
        setUp();
        initBanners();
        initTopMoving();
    }

    private void setupViewPager() {
        listphoto = getlistphoto();
        photo_adapter adapter = new photo_adapter(listphoto);
        binding.ViewPager.setAdapter(adapter);
        binding.circleIndicator.setViewPager(binding.ViewPager);

        handler.postDelayed(runnable, 4000);
        binding.ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTopMoving() {
        DatabaseReference databaseReference = firebaseDatabase.getReference("Item");
        binding.progressBar1.setVisibility(View.VISIBLE);
        ArrayList<anime_re> items = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        items.add(issue.getValue(anime_re.class));
                    }
                    if (!items.isEmpty()) {
                        binding.recycleview1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        binding.recycleview1.setAdapter(new anime_re_adapter(items));
                    }
                    binding.progressBar1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBar1.setVisibility(View.GONE);
            }
        });
    }

    private void initBanners() {
        DatabaseReference databaseReference = firebaseDatabase.getReference("Banners");
        binding.progressBar5.setVisibility(View.VISIBLE);
        ArrayList<Slider_items> items = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        items.add(issue.getValue(Slider_items.class));
                    }
                    setupBanners(items);
                    binding.progressBar5.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBar5.setVisibility(View.GONE);
            }
        });
    }

    private void setupBanners(ArrayList<Slider_items> items) {
        binding.viewpager2.setAdapter(new Slider_Adapter(items, binding.viewpager2));
        binding.viewpager2.setClipToPadding(false);
        binding.viewpager2.setClipChildren(false);
        binding.viewpager2.setOffscreenPageLimit(3);
        binding.viewpager2.getChildAt(0).setOverScrollMode(ViewPager2.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });
        binding.viewpager2.setPageTransformer(compositePageTransformer);
        binding.viewpager2.setCurrentItem(1);
        binding.viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
            }
        });
    }

    private List<photo> getlistphoto() {
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.logo));
        list.add(new photo(R.drawable.logo));
        list.add(new photo(R.drawable.logo));
        return list;
    }

    private void init() {
        truyentranhArrayList = new ArrayList<>();
        truyentranhArrayList.add(new anime("Shinju No Nectar", "Chapter 3", "https://tranh18.com/static/upload/cover/shinju_no_nectar_f2np1.jpg"));

        apdapter = new anime_adapter(getContext(), 0, truyentranhArrayList);
        binding.progressBar2.setVisibility(View.VISIBLE);
        truyendammyArrayList = new ArrayList<>();
//        truyendammyArrayList.add(new anime("Unmei No Makimodoshi", "Chapter 5.2", "https://i7.xem-truyen.com/manga/34/34885/33830139-3525-4506-8826-3f82cf7685ff.thumb_500x.jpg"));
        truyendammyArrayList.add(new anime("Dục vọng", "Chapter 57", "https://media.hacomics.xyz/images/fullsize/2021/03/13/duc-vong.jpg"));
        truyendammyArrayList.add(new anime("Sore wa rei no shiwaza desu", "Chapter 4", "https://i7.xem-truyen.com/manga/25/25278/c8cccbe1-020f-4de5-8b77-a0dc8e32ccae.thumb_500x.jpg"));
        truyendammyArrayList.add(new anime("Bà chị chủ nhà", "Chapter 138", "https://media.hacomics.xyz/images/fullsize/2021/11/02/ba-chi-chu-nha.jpg"));
        truyendammyArrayList.add(new anime("Bà chị chủ nhà", "Chapter 138", "https://media.hacomics.xyz/images/fullsize/2021/11/02/ba-chi-chu-nha.jpg"));
        binding.progressBar2.setVisibility(View.GONE);
        apdapter1 = new anime_adapter(getContext(), 0, truyendammyArrayList);
    }

    private void setUp() {
        binding.gdv1.setAdapter(apdapter);
        binding.gdv2.setAdapter(apdapter1);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
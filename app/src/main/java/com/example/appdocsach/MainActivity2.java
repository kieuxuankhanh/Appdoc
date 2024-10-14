package com.example.appdocsach;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appdocsach.Fragment.AccFragment;
import com.example.appdocsach.Fragment.BookFragment;
import com.example.appdocsach.Fragment.HomeFragment;
import com.example.appdocsach.Fragment.NotiFragment;
import com.example.appdocsach.adapter.Slider_Adapter;
import com.example.appdocsach.adapter.anime_adapter;
import com.example.appdocsach.adapter.anime_re_adapter;
import com.example.appdocsach.adapter.photo_adapter;
import com.example.appdocsach.databinding.ActivityMain2Binding;
import com.example.appdocsach.databinding.ActivityMainBinding;
import com.example.appdocsach.object.Slider_items;
import com.example.appdocsach.object.anime;
import com.example.appdocsach.object.anime_re;
import com.example.appdocsach.object.photo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity2 extends AppCompatActivity {
    private static final int MY_REQUEST_CODE =10;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    final private UserProfileActivity userProfileActivity = new UserProfileActivity();
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                if (intent == null)
                {
                    return;
                }
                Uri uri = intent.getData();
                userProfileActivity.setUri(uri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    userProfileActivity.setBitmapImageView(bitmap);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bottomNavigationView = findViewById(R.id.BottomNvi);
        frameLayout = findViewById(R.id.framelayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemID= item.getItemId();

                if (itemID == R.id.btnhome){
                    loadFragment(new HomeFragment(), false);
                }
                else if (itemID == R.id.btnbook){
                    loadFragment(new BookFragment(),false);
                }
                else if (itemID == R.id.btnnoti){
                    loadFragment(new NotiFragment(),false);
                }
                else if (itemID == R.id.btnperson){
                    loadFragment(new AccFragment(),false);
                }
                return true;
            }
        });
        loadFragment(new HomeFragment(), true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }
    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent,"chon anh"));
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitialized){
            fragmentTransaction.add(R.id.framelayout,fragment);
        }
        else {
            fragmentTransaction.replace(R.id.framelayout, fragment);
        }
        fragmentTransaction.commit();
    }
}



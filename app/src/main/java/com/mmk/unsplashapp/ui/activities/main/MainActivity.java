package com.mmk.unsplashapp.ui.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.mmk.unsplashapp.R;
import com.mmk.unsplashapp.ui.fragments.picturelist.PictureListFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        frameLayout = findViewById(R.id.frame_layout_main);
        fragmentManager = getSupportFragmentManager();
        changeFragment(new PictureListFragment());


    }

    public void changeFragment(Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(frameLayout.getId(),fragment)
                .commit();

    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 1)
            fragmentManager.popBackStack();
        else
            finish();
    }
}

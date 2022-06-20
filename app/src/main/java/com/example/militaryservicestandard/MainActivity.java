package com.example.militaryservicestandard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.militaryservicestandard.MainActivityFragments.BMIFragment;
import com.example.militaryservicestandard.MainActivityFragments.FootFragment;
import com.example.militaryservicestandard.MainActivityFragments.HearFragment;
import com.example.militaryservicestandard.MainActivityFragments.LookFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FindView();

        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(MainActivity.this);
        viewPager2.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) tab.setText("BMI");
                if (position == 1) tab.setText("畸形足");
                if (position == 2) tab.setText("視力");
                if (position == 3) tab.setText("聽力");
            }
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.setCurrentItem(0);

    }

    private void FindView(){
        tabLayout = findViewById(R.id.Main_TabLayout);
        viewPager2 = findViewById(R.id.Main_ViewPager2);
    }

    class ScreenSlidePagerAdapter extends FragmentStateAdapter{
        public ScreenSlidePagerAdapter(AppCompatActivity view){
            super(view);
        }

        @Override
        public Fragment createFragment(int position) {
            Fragment frag_new = null;
            if (position == 0) frag_new = new BMIFragment();
            if (position == 1) frag_new = new FootFragment();
            if (position == 2) frag_new = new LookFragment();
            if (position == 3) frag_new = new HearFragment();
            return frag_new;
        }

        @Override
        public int getItemCount() {
            return 4;
        }

    }
}
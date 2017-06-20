package com.example.eryk.e_vote;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eryk.e_vote.tabs.MyPagerAdapter;
import com.example.eryk.e_vote.tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbars();
        
    }


    public void initToolbars() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.mainSwapView);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),getApplicationContext())); // Set the viewPager's adapter to MyPagerAdapter
        viewPager.setOffscreenPageLimit(3);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);

        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.main_blue));
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.accent_material_dark));
        // https://www.youtube.com/watch?v=f6jh64jJDrk
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });
        slidingTabLayout.setViewPager(viewPager);



    }
}

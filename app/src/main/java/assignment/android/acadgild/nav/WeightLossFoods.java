package assignment.android.acadgild.nav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import assignment.android.acadgild.R;
/**
 * Created by DivyaVipin on 1/16/2017.
 */

public class WeightLossFoods extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weightloss_foods);
        //Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("1"));
        tabLayout.addTab(tabLayout.newTab().setText("2"));
        tabLayout.addTab(tabLayout.newTab().setText("3"));
        tabLayout.addTab(tabLayout.newTab().setText("4"));
        tabLayout.addTab(tabLayout.newTab().setText("5"));
        tabLayout.addTab(tabLayout.newTab().setText("6"));
        tabLayout.addTab(tabLayout.newTab().setText("7"));
        tabLayout.addTab(tabLayout.newTab().setText("8"));
        tabLayout.addTab(tabLayout.newTab().setText("9"));
        tabLayout.addTab(tabLayout.newTab().setText("10"));
        tabLayout.addTab(tabLayout.newTab().setText("11"));
        tabLayout.addTab(tabLayout.newTab().setText("12"));
        tabLayout.addTab(tabLayout.newTab().setText("13"));
        tabLayout.addTab(tabLayout.newTab().setText("14"));
        tabLayout.addTab(tabLayout.newTab().setText("15"));
        tabLayout.addTab(tabLayout.newTab().setText("16"));
        tabLayout.addTab(tabLayout.newTab().setText("17"));
        /*tabLayout.getTabAt(0).setIcon(R.drawable.wloss);
        tabLayout.getTabAt(1).setIcon(R.drawable.egg);
        tabLayout.getTabAt(2).setIcon(R.drawable.chia);
        tabLayout.getTabAt(3).setIcon(R.drawable.chilli);
        tabLayout.getTabAt(4).setIcon(R.drawable.chicken);
        tabLayout.getTabAt(5).setIcon(R.drawable.egg);
        tabLayout.getTabAt(6).setIcon(R.drawable.chia);
        tabLayout.getTabAt(7).setIcon(R.drawable.chilli);
        tabLayout.getTabAt(8).setIcon(R.drawable.chicken);
        tabLayout.getTabAt(9).setIcon(R.drawable.calculator);
        tabLayout.getTabAt(10).setIcon(R.drawable.and);
        tabLayout.getTabAt(11).setIcon(R.drawable.chilli);
        tabLayout.getTabAt(12).setIcon(R.drawable.chicken);
        tabLayout.getTabAt(13).setIcon(R.drawable.egg);
        tabLayout.getTabAt(14).setIcon(R.drawable.chia);
        tabLayout.getTabAt(15).setIcon(R.drawable.chilli);
        tabLayout.getTabAt(16).setIcon(R.drawable.chia);*/

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        Pager pager = new Pager(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

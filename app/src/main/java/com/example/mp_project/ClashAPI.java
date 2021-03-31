package com.example.mp_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ClashAPI extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    public ClashAPI()
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clashapi);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            String id = extras.getString("id");
        }

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("User Stats");
        arrayList.add("Clan Stats");

        prepareViewPager(viewPager,arrayList);

        tabLayout.setupWithViewPager(viewPager);


    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        UserFragment userFragment = new UserFragment();

        Bundle bundle = new Bundle();
        bundle.putString("title", arrayList.get(0));
        userFragment.setArguments(bundle);
        adapter.addFragment(userFragment,arrayList.get(0));

        ClanFragment clanFragment = new ClanFragment();

        Bundle bundle2 = new Bundle();
        bundle2.putString("title", arrayList.get(1));
        clanFragment.setArguments(bundle2);
        adapter.addFragment(clanFragment,arrayList.get(1));

        viewPager.setAdapter(adapter);

    }

    private class MainAdapter extends FragmentPagerAdapter {

        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        public void addFragment(Fragment fragment, String title)
        {
            arrayList.add(title);
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList.get(position);
        }
    }
}

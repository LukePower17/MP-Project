package com.example.mp_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TestClashAPI extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    public TestClashAPI()
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clashapi);

        //ParseJson parseJson = new ParseJson();
//        JSONObject json = null;
//        try {
//            json = readJsonFromUrl("https://graph.facebook.com/19292868552");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        if(json != null) {
//            Log.d("myTag", json.toString());
//            try {
//                Log.d("myTag2", json.get("id").toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }


//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//
//            String id = extras.getString("id");
//        }

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

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
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

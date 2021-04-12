package com.example.mp_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mp_project.cocmodels.clan.ClanModel;
import com.example.mp_project.cocmodels.player.Player;
import com.example.mp_project.cocmodels.player.Troop;
import com.example.mp_project.core.ClashAPI;
import com.example.mp_project.core.exception.ClashAPIException;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    String API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjNmNmJkMWM1LThkZmEtNDFiZC05ZDYyLTEwMWE3ZmU3MGM1ZSIsImlhdCI6MTYxMzA4MDg5Miwic3ViIjoiZGV2ZWxvcGVyLzVjNGFjZmQ4LWQ3MmQtNjcyZS1mNDFlLWIyY2ZiNzE0ZGZlOSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjY4LjM1LjI0OS4xNDkiXSwidHlwZSI6ImNsaWVudCJ9XX0.0SrYHSxi0wv85BxxmfWAispA73yRGUySAWcpvzw7XoQF_1WOj2ZPyrZXfCFH2Cy4YlZ7YOa49-ZyWzhSDmOStQ";
    String MyID = "#Y0GVVRGU";
    String GID = "#UPGPCGVU";
    String MyClan = "#PO2CUUUU";
    Player player = null;
    ClanModel clan = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        TextView exceptionView = (TextView) view.findViewById(R.id.exceptionTv);
        TextView ignView = (TextView) view.findViewById(R.id.ignTv);
        TextView trophiesView = (TextView) view.findViewById(R.id.trophiesTv);
        TextView maxTrophiesView = (TextView) view.findViewById(R.id.maxTrophiesTv);
        TextView clanTv = (TextView) view.findViewById(R.id.clanTv);
        TextView thTv = (TextView) view.findViewById(R.id.townhallTv);
        TextView roleTv = (TextView) view.findViewById(R.id.roleTv);
        TextView attackTv = (TextView) view.findViewById(R.id.atkTv);
        TextView defenseTv = (TextView) view.findViewById(R.id.defenseTv);
        TextView donationTv = (TextView) view.findViewById(R.id.donationTv);
        TextView donationRecTv = (TextView) view.findViewById(R.id.donationRTv);
        TextView levelView = (TextView) view.findViewById(R.id.levelTv);
        TextView warStarView = (TextView) view.findViewById(R.id.warStarTv);
        TextView bbLevelView = (TextView) view.findViewById(R.id.bbLevelTv);
        TextView bbWinsView = (TextView) view.findViewById(R.id.bbWinsTv);
        TextView bbMaxTrophiesView = (TextView) view.findViewById(R.id.bbMaxTrophiesTv);
        TextView bbTrophiesView = (TextView) view.findViewById(R.id.bbTrophiesTv);

        TextView tv2 = (TextView)view.findViewById(R.id.textView2);
        TextView tv3 = (TextView)view.findViewById(R.id.textView3);
        TextView tv4 = (TextView)view.findViewById(R.id.textView4);
        TextView tv5 = (TextView)view.findViewById(R.id.textView5);
        TextView tv6 = (TextView)view.findViewById(R.id.textView6);
        TextView tv7 = (TextView)view.findViewById(R.id.textView7);
        TextView tv8 = (TextView)view.findViewById(R.id.textView8);
        TextView tv9 = (TextView)view.findViewById(R.id.textView9);
        TextView tv10 = (TextView)view.findViewById(R.id.textView10);
        TextView tv11 = (TextView)view.findViewById(R.id.textView11);
        TextView tv12 = (TextView)view.findViewById(R.id.textView12);
        TextView tv13 = (TextView)view.findViewById(R.id.textView13);
        TextView tv14 = (TextView)view.findViewById(R.id.textView14);
        TextView tv15 = (TextView)view.findViewById(R.id.textView15);
        TextView tv16 = (TextView)view.findViewById(R.id.textView16);
        TextView tv17 = (TextView)view.findViewById(R.id.textView17);

        ignView.setVisibility(View.GONE);
        trophiesView.setVisibility(View.GONE);
        maxTrophiesView.setVisibility(View.GONE);
        clanTv.setVisibility(View.GONE);
        thTv.setVisibility(View.GONE);
        roleTv.setVisibility(View.GONE);
        attackTv.setVisibility(View.GONE);
        defenseTv.setVisibility(View.GONE);
        donationTv.setVisibility(View.GONE);
        donationRecTv.setVisibility(View.GONE);
        levelView.setVisibility(View.GONE);
        warStarView.setVisibility(View.GONE);
        bbLevelView.setVisibility(View.GONE);
        bbWinsView.setVisibility(View.GONE);
        bbMaxTrophiesView.setVisibility(View.GONE);
        bbTrophiesView.setVisibility(View.GONE);

        tv2.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);
        tv4.setVisibility(View.GONE);
        tv5.setVisibility(View.GONE);
        tv6.setVisibility(View.GONE);
        tv7.setVisibility(View.GONE);
        tv8.setVisibility(View.GONE);
        tv9.setVisibility(View.GONE);
        tv10.setVisibility(View.GONE);
        tv11.setVisibility(View.GONE);
        tv12.setVisibility(View.GONE);
        tv13.setVisibility(View.GONE);
        tv14.setVisibility(View.GONE);
        tv15.setVisibility(View.GONE);
        tv16.setVisibility(View.GONE);
        tv17.setVisibility(View.GONE);

        EditText idEt = (EditText) view.findViewById(R.id.userIdEt);


        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            ClashAPI clashAPI = new ClashAPI(API_TOKEN);

            // 2. And do the requests you need. Yes, it's as simple :)
//            Player player = null;
//            ClanModel clan = null;

            Button button = (Button) view.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        player = clashAPI.getPlayer(idEt.getText().toString());
                        clan = player.getClan();

                        String clanRole = player.getRole();
                        String ign = player.getName();
                        String clanName = clan.getName();
                        int townhallLevel = player.getTownHallLevel();
                        int trophies = player.getTrophies();
                        int maxTrophies = player.getBestTrophies();
                        int attackWins = player.getAttackWins();
                        int bbTrophies = player.getBuilderBaseTrophies();
                        int bbMaxTrophies = player.getBuilderBaseBestTrophies();
                        int bbWinCount = player.getBuilderBaseWinCount();
                        int bbLevel = player.getBuilderHallLevel();
                        int defenseWins = player.getDefenseWins();
                        int donations = player.getDonations();
                        int donationsReceived = player.getDonationsReceived();
                        int expLevel = player.getExpLevel();
                        int warStars = player.getWarStars();

                        ignView.setVisibility(View.VISIBLE);
                        trophiesView.setVisibility(View.VISIBLE);
                        maxTrophiesView.setVisibility(View.VISIBLE);
                        clanTv.setVisibility(View.VISIBLE);
                        thTv.setVisibility(View.VISIBLE);
                        roleTv.setVisibility(View.VISIBLE);
                        attackTv.setVisibility(View.VISIBLE);
                        defenseTv.setVisibility(View.VISIBLE);
                        donationTv.setVisibility(View.VISIBLE);
                        donationRecTv.setVisibility(View.VISIBLE);
                        levelView.setVisibility(View.VISIBLE);
                        warStarView.setVisibility(View.VISIBLE);
                        bbLevelView.setVisibility(View.VISIBLE);
                        bbWinsView.setVisibility(View.VISIBLE);
                        bbMaxTrophiesView.setVisibility(View.VISIBLE);
                        bbTrophiesView.setVisibility(View.VISIBLE);

                        tv2.setVisibility(View.VISIBLE);
                        tv3.setVisibility(View.VISIBLE);
                        tv4.setVisibility(View.VISIBLE);
                        tv5.setVisibility(View.VISIBLE);
                        tv6.setVisibility(View.VISIBLE);
                        tv7.setVisibility(View.VISIBLE);
                        tv8.setVisibility(View.VISIBLE);
                        tv9.setVisibility(View.VISIBLE);
                        tv10.setVisibility(View.VISIBLE);
                        tv11.setVisibility(View.VISIBLE);
                        tv12.setVisibility(View.VISIBLE);
                        tv13.setVisibility(View.VISIBLE);
                        tv14.setVisibility(View.VISIBLE);
                        tv15.setVisibility(View.VISIBLE);
                        tv16.setVisibility(View.VISIBLE);
                        tv17.setVisibility(View.VISIBLE);

                        ignView.setText("   " + ign);
                        trophiesView.setText("   " + trophies);
                        maxTrophiesView.setText("   " + maxTrophies);
                        clanTv.setText("   " + clanName);
                        thTv.setText("   " + townhallLevel);
                        roleTv.setText("   " + clanRole);
                        attackTv.setText("   " + attackWins);
                        defenseTv.setText("   " + defenseWins);
                        donationTv.setText("   " + donations);
                        donationRecTv.setText("   " + donationsReceived);
                        levelView.setText("   " + expLevel);
                        warStarView.setText("   " + warStars);
                        bbLevelView.setText("   " + bbLevel);
                        bbWinsView.setText("   " + bbWinCount);
                        bbMaxTrophiesView.setText("   " + bbMaxTrophies);
                        bbTrophiesView.setText("   " + bbTrophies);


                        List<Troop> heroes = player.getHeroes();
                    } catch (IOException e) {
                        e.printStackTrace();
                        exceptionView.setText("   " + e.toString());
                    } catch (ClashAPIException e) {
                        e.printStackTrace();
                        exceptionView.setText("   " + e.toString());
                    }
                }
            });


        }

        String sTitle = getArguments().getString("title");
        //textView.setText(sTitle);
        return view;
    }
}
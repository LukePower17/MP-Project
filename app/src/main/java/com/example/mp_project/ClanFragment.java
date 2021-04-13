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

import com.example.mp_project.cocmodels.clan.ClanModel;
import com.example.mp_project.cocmodels.player.Player;
import com.example.mp_project.cocmodels.player.Troop;
import com.example.mp_project.core.ClashAPI;
import com.example.mp_project.core.exception.ClashAPIException;

import java.io.IOException;
import java.util.List;

public class ClanFragment extends Fragment {

    String API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjNmNmJkMWM1LThkZmEtNDFiZC05ZDYyLTEwMWE3ZmU3MGM1ZSIsImlhdCI6MTYxMzA4MDg5Miwic3ViIjoiZGV2ZWxvcGVyLzVjNGFjZmQ4LWQ3MmQtNjcyZS1mNDFlLWIyY2ZiNzE0ZGZlOSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjY4LjM1LjI0OS4xNDkiXSwidHlwZSI6ImNsaWVudCJ9XX0.0SrYHSxi0wv85BxxmfWAispA73yRGUySAWcpvzw7XoQF_1WOj2ZPyrZXfCFH2Cy4YlZ7YOa49-ZyWzhSDmOStQ";
    ClanModel clan = null;

    public ClanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clan, container, false);
        EditText CidEt = (EditText) view.findViewById(R.id.CuserIdEt);

        TextView exceptionView = (TextView) view.findViewById(R.id.CexceptionTv);
        TextView clanLevelView = (TextView) view.findViewById(R.id.clanLevelTv);
        TextView clanPointsView = (TextView) view.findViewById(R.id.clanPointsTv);
        TextView clanVersusPointsView = (TextView) view.findViewById(R.id.clanVersusPointsTv);
        TextView clanCountView = (TextView) view.findViewById(R.id.clanMemberCountTv);
        TextView clanReqView = (TextView) view.findViewById(R.id.RequiredTrophiesTv);
        TextView clanWinsView = (TextView) view.findViewById(R.id.clanWinsTv);
        TextView clanWinStreakView = (TextView) view.findViewById(R.id.clanWinStreakTv);
        TextView clanDescriptionView = (TextView) view.findViewById(R.id.clanDescriptionTv);
        TextView clanLocationView = (TextView) view.findViewById(R.id.clanLocationTv);
        TextView clanNameView = (TextView) view.findViewById(R.id.clanNameTvC);
        TextView clanFrequencyView = (TextView) view.findViewById(R.id.clanFrequencyTv);

        TextView tv2 = (TextView)view.findViewById(R.id.CtextView2);
        TextView tv3 = (TextView)view.findViewById(R.id.CtextView3);
        TextView tv4 = (TextView)view.findViewById(R.id.CtextView4);
        TextView tv5 = (TextView)view.findViewById(R.id.CtextView5);
        TextView tv6 = (TextView)view.findViewById(R.id.CtextView6);
        TextView tv7 = (TextView)view.findViewById(R.id.CtextView7);
        TextView tv8 = (TextView)view.findViewById(R.id.CtextView8);
        TextView tv9 = (TextView)view.findViewById(R.id.CtextView9);
        TextView tv10 = (TextView)view.findViewById(R.id.CtextView10);
        TextView tv11 = (TextView)view.findViewById(R.id.CtextView11);
        TextView tv12 = (TextView)view.findViewById(R.id.CtextView12);

        clanLevelView.setVisibility(View.GONE);
        clanPointsView.setVisibility(View.GONE);
        clanVersusPointsView.setVisibility(View.GONE);
        clanCountView.setVisibility(View.GONE);
        clanReqView.setVisibility(View.GONE);
        clanWinsView.setVisibility(View.GONE);
        clanWinStreakView.setVisibility(View.GONE);
        clanDescriptionView.setVisibility(View.GONE);
        clanLocationView.setVisibility(View.GONE);
        clanNameView.setVisibility(View.GONE);
        clanFrequencyView.setVisibility(View.GONE);

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

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            ClashAPI clashAPI = new ClashAPI(API_TOKEN);

            Button button = (Button) view.findViewById(R.id.Cbutton);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        clan = clashAPI.getClan(CidEt.getText().toString());
                        int clanLevel = clan.getClanLevel();
                        int clanPoints = clan.getClanPoints();
                        int clanVPoints = clan.getClanVersusPoints();
                        int clanCount = clan.getMembers();
                        int clanReqTrophies = clan.getRequiredTrophies();
                        int clanWins = clan.getWarWins();
                        int clanWinStreak = clan.getWarWinStreak();
                        String clanDescriptions = clan.getDescription();
                        String clanLocation = clan.getLocation().getName();
                        String clanName = clan.getName();
                        String clanFrequency = clan.getWarFrequency();

                        clanLevelView.setVisibility(View.VISIBLE);
                        clanPointsView.setVisibility(View.VISIBLE);
                        clanVersusPointsView.setVisibility(View.VISIBLE);
                        clanCountView.setVisibility(View.VISIBLE);
                        clanReqView.setVisibility(View.VISIBLE);
                        clanWinsView.setVisibility(View.VISIBLE);
                        clanWinStreakView.setVisibility(View.VISIBLE);
                        clanDescriptionView.setVisibility(View.VISIBLE);
                        clanLocationView.setVisibility(View.VISIBLE);
                        clanNameView.setVisibility(View.VISIBLE);
                        clanFrequencyView.setVisibility(View.VISIBLE);

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

                        clanLevelView.setText("   " + clanLevel);
                        clanPointsView.setText("   " + clanPoints);
                        clanVersusPointsView.setText("   " + clanVPoints);
                        clanCountView .setText("   " + clanCount);
                        clanReqView.setText("   " + clanReqTrophies);
                        clanWinsView.setText("   " + clanWins);
                        clanWinStreakView.setText("   " + clanWinStreak);
                        clanDescriptionView.setText("   " + clanDescriptions);
                        clanLocationView.setText("   " + clanLocation);
                        clanNameView.setText("   " + clanName);
                        clanFrequencyView.setText("   " + clanFrequency);

                        exceptionView.setText("");

                    } catch (IOException e) {
                        e.printStackTrace();
                        exceptionView.setText("   An error has occurred! This ID is not valid or the API is under maintenance");
                    } catch (ClashAPIException e) {
                        e.printStackTrace();
                        exceptionView.setText("   An error has occurred! This ID is not valid or the API is under maintenance");
                    }
                }
            });


        }

        return view;
    }
}
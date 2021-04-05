package com.example.mp_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        TextView textView = view.findViewById(R.id.text_view);
        TextView ignView = (TextView) view.findViewById(R.id.ignTv);
        TextView trophiesView = (TextView) view.findViewById(R.id.trophiesTv);
        TextView maxTrophiesView = (TextView) view.findViewById(R.id.maxTrophiesTv);
        TextView clanTv = (TextView) view.findViewById(R.id.clanTv);
        TextView thTv = (TextView) view.findViewById(R.id.townhallTv);


        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            ClashAPI clashAPI = new ClashAPI(API_TOKEN);

            // 2. And do the requests you need. Yes, it's as simple :)
            Player player = null;
            ClanModel clan = null;

            try {
                player = clashAPI.getPlayer(GID);
                int townhallLevel = player.getTownHallLevel();
                String clanRole = player.getRole();
                String ign = player.getName();
                clan = player.getClan();
                String clanName = clan.getName();
                int trophies = player.getTrophies();
                int maxTrophies = player.getBestTrophies();


                ignView.setText(ign);
                trophiesView.setText(""+trophies);
                maxTrophiesView.setText(""+maxTrophies);
                clanTv.setText(clanName);
                thTv.setText(""+townhallLevel);

                List<Troop> heroes = player.getHeroes();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClashAPIException e) {
                e.printStackTrace();
            }
        }

//        Toast toast = Toast.makeText(context, clanRole, duration);
//        toast.show();

        String sTitle = getArguments().getString("title");
        textView.setText(sTitle);
        return view;
    }
}
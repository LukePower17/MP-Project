package com.example.mp_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mp_project.exception.ClashException;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //Token:
    String API_TOKEN = "eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjNmNmJkMWM1LThkZmEtNDFiZC05ZDYyLTEwMWE3ZmU3MGM1ZSIsImlhdCI6MTYxMzA4MDg5Miwic3ViIjoiZGV2ZWxvcGVyLzVjNGFjZmQ4LWQ3MmQtNjcyZS1mNDFlLWIyY2ZiNzE0ZGZlOSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjY4LjM1LjI0OS4xNDkiXSwidHlwZSI6ImNsaWVudCJ9XX0.0SrYHSxi0wv85BxxmfWAispA73yRGUySAWcpvzw7XoQF_1WOj2ZPyrZXfCFH2Cy4YlZ7YOa49-ZyWzhSDmOStQ";
    //Allowed IP:
    //68.35.249.149

    EditText userID;
    TextView idTV;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        userID = (EditText)findViewById(R.id.userId);
        submit = (Button)findViewById(R.id.submitBtn);
        idTV = (TextView)findViewById(R.id.idTextView);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userID.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(context, "Please enter a valid ID", duration);
                    toast.show();
                    idTV.setTextColor(Color.RED);
                }
                else
                {
//                    ClashAPI clashAPI = BTClashWrapper.getAPIInstance(API_TOKEN);
//
//                    // use ClashAPI here
                    Intent HomeActivityIntent = new Intent(MainActivity.this, TestClashAPI.class);
                    HomeActivityIntent.putExtra("id", userID.getText().toString());
                    startActivity(HomeActivityIntent);
                }
            }
        });



        //https://api.clashofclans.com/v1/players/%23YOGVVRGU
    }
}
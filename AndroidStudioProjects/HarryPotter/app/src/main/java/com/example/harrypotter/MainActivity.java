package com.example.harrypotter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView characterName, characterQuote, animeName;
    Button generateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characterName= findViewById(R.id.idCharacter);
        animeName= findViewById(R.id.animeName);
        characterQuote=findViewById(R.id.idQuote);
        generateBtn=findViewById(R.id.idGenerateBtn);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instantiate the Requestqueue
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url="https://animechan.vercel.app/api/random";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String anime_Name= response.getString("anime");
                                    String anime_Character= response.getString("character");
                                    String anime_Quote= response.getString("quote");

                                    animeName.setText(anime_Name);
                                    characterName.setText(anime_Character);
                                    characterQuote.setText(anime_Quote);

                                } catch (JSONException e) {
                                   e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });
                queue.add(jsonObjectRequest);
            }
        });
    }
}
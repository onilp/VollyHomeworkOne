package com.example.vollyhomeworkone;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyAdapter myAdapter;
    List<Movies> moviesList;
    ProgressDialog progressDialog;

    private static String url = "https://uniqueandrocode.000webhostapp.com/hiren/androidweb.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Downloading data...");
        progressDialog.show();
        moviesList = new ArrayList<>();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progressDialog.cancel();
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        String id = data.getString("id");
                        String name = data.getString("name");
                        String moviewname = data.getString("moviename");
                        String imageurl = data.getString("imageurl");
                        moviesList.add(new Movies(id,name,imageurl,moviewname));
                    }
                    myAdapter = new MyAdapter(getApplicationContext(),moviesList);
                    recyclerView.setAdapter(myAdapter);

                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}

package com.example.vollyhomeworkone;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<Movies> moviesList;

    public MyAdapter(Context context, List<Movies> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.MyViewHolder myViewHolder, int position) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Movies movies = moviesList.get(position);
        myViewHolder.textViewId.setText(movies.getId());
        myViewHolder.textViewName.setText(movies.getName());
        myViewHolder.textViewMovieName.setText(movies.getMoviename());

        ImageRequest imageRequest = new ImageRequest(movies.getImageurl(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                myViewHolder.imageView.setImageBitmap(response);

            }
        }, 250, 250, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(imageRequest);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewName, textViewMovieName;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewMovieName = itemView.findViewById(R.id.textViewMovieName);
            imageView = itemView.findViewById(R.id.imageView);


        }
    }
}

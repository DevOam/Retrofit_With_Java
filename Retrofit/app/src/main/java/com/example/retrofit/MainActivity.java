package com.example.retrofit;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.animation_move.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView PostTitleTV=findViewById(R.id.post_title);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        Call<Post> call =apiInterface.getPost(1);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                PostTitleTV.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                PostTitleTV.setText(t.getMessage());
            }
        });



    }
}
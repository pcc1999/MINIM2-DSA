package com.example.minim2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minim2.models.GithubRepo;
import com.example.minim2.models.GithubUser;
import com.example.minim2.utils.MyRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    APIInterface apiIface;
    String username;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        apiIface = APIClient.getClient().create(APIInterface.class);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        final Intent getUser_intent = getIntent();
        username = getUser_intent.getExtras().getString("user");

        Log.d("minim2TAG", username);
        getGithubUser(username);
        getGithubRepos(username);
    }

    public void getGithubUser(String username){
        Call<GithubUser> call = apiIface.getInfoUser(username);
        call.enqueue(new Callback<GithubUser>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                if(response.isSuccessful()){
                    GithubUser githubUser = response.body();
                    TextView txtUser = (TextView)findViewById(R.id.txtUser);
                    txtUser.setText(githubUser.getLogin());
                    ImageView imageUser = (ImageView)findViewById(R.id.imageView);
                    Picasso.get().load(githubUser.getAvatar_url()).into(imageUser);
                    TextView txtFollowers = (TextView)findViewById(R.id.txtFollowers);
                    txtFollowers.setText("Followers: " + githubUser.getFollowers());
                    TextView txtFollowing = (TextView)findViewById(R.id.txtFollowing);
                    txtFollowing.setText("Following: " + githubUser.getFollowing());
                } else {
                    Toast.makeText(getApplicationContext(), "Error when retreiving info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable throwable) {
                call.cancel();
            }
        });
    }

    public void getGithubRepos(String username){

        Call<ArrayList<GithubRepo>> call1 = apiIface.getInfoRepos(username);
        call1.enqueue(new Callback<ArrayList<GithubRepo>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ArrayList<GithubRepo>> call1, Response<ArrayList<GithubRepo>> response) {
                Log.d("minim2TAG", "onResponse: ");
                if(response.isSuccessful()){
                    Log.d("minim2TAG", response.body().getClass().toString());
                    List<GithubRepo> reposList = response.body();
                    RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new MyRecyclerViewAdapter(reposList));
                    progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getApplicationContext(), "Error when retreiving info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GithubRepo>> call, Throwable throwable) {
                Log.d("minim2TAG", "onFailure");
                Log.d("minim2TAG", throwable.getMessage());
                call.cancel();
            }
        });
    }
}
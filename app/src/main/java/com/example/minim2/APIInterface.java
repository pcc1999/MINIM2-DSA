package com.example.minim2;

import com.example.minim2.models.GithubRepo;
import com.example.minim2.models.GithubUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/users/{username}")
    Call<GithubUser> getInfoUser(@Path("username") String username);

    @GET("/users/{username}/repos")
    Call<ArrayList<GithubRepo>> getInfoRepos(@Path("username") String username);

}

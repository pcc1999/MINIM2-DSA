package com.example.minim2.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.minim2.R;
import com.example.minim2.models.GithubRepo;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    List<GithubRepo> githubRepos;


    public MyRecyclerViewAdapter(List<GithubRepo> githubRepos) {
        this.githubRepos = githubRepos;
    }

    @NotNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.txtNameRepo.setText(githubRepos.get(position).getName());
        viewHolder.txtLanguageRepo.setText(String.valueOf(githubRepos.get(position).getLanguage()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtNameRepo;
        public TextView txtLanguageRepo;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtNameRepo = (TextView) itemLayoutView.findViewById(R.id.numRepo);
            txtLanguageRepo = (TextView) itemLayoutView.findViewById(R.id.codeRepo);
        }
    }


    @Override
    public int getItemCount() {
        return githubRepos.size();
    }
}
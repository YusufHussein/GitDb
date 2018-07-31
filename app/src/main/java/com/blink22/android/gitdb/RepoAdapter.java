package com.blink22.android.gitdb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {
    private List<Repo> mRepos;

    public RepoAdapter(List<Repo> repos) {
        mRepos = repos;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_repo, parent, false);
        RepoViewHolder repoViewHolder = new RepoViewHolder(view);
        return repoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        Repo repo = mRepos.get(position);
        holder.bindData(repo);
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }
}

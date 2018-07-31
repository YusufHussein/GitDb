package com.blink22.android.gitdb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReposSearchResult {
    @SerializedName("items")
    private List<Repo> mRepos = null;

    public List<Repo> getRepos() {
        return mRepos;
    }

    public void setRepos(List<Repo> repos) {
        mRepos = repos;
    }
}

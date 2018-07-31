package com.blink22.android.gitdb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {
    @GET("search/repositories")
    Call<ReposSearchResult> searchRepos(@Query("q") String query);
}

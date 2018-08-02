package com.blink22.android.gitdb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitReposListSearchFragment extends Fragment {
    private static final String DEFAULT_SEARCH_QUERY = "android";
    @BindView(R.id.repos_recycler_view)
    RecyclerView mReposRecyclerView;
    private RepoAdapter mRepoAdapter;
    private Call<ReposSearchResult> mSearchReposCall;
    private Realm mRealm;

    public static GitReposListSearchFragment newInstance() {
        return new GitReposListSearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRealm = Realm.getDefaultInstance();
        View view = inflater.inflate(R.layout.fragment_git_repos_search, container, false);
        ButterKnife.bind(this, view);
        mReposRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RealmResults<Repo> repoRealmResults = mRealm.where(Repo.class).findAllAsync();
        mRepoAdapter = new RepoAdapter(mRealm.copyFromRealm(repoRealmResults));
        mReposRecyclerView.setAdapter(mRepoAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_git_repos_search, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        initializeSearchBar(searchView);
    }

    private void initializeSearchBar(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                QueryPreferences.setStoredQuery(getActivity(), query);
                fetchRepos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                QueryPreferences.setStoredQuery(getActivity(), null);
                fetchRepos(DEFAULT_SEARCH_QUERY);
                return false;
            }
        });
        String query = QueryPreferences.getStoredQuery(getActivity());
        if (query != null) {
            searchView.setQuery(query, false);
            searchView.setIconified(false);
            fetchRepos(query);
        } else fetchRepos(DEFAULT_SEARCH_QUERY);
    }

    private void fetchRepos(String query) {
        GitHubService gitHubService = GitHubApiClient.getClient().create(GitHubService.class);
        mSearchReposCall = gitHubService.searchRepos(query);
        mSearchReposCall.enqueue(new SearchReposCallbacks());
    }

    @Override
    public void onDestroyView() {
        if (mSearchReposCall != null && mSearchReposCall.isExecuted()) mSearchReposCall.cancel();
        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
        super.onDestroyView();
    }

    private class SearchReposCallbacks implements Callback<ReposSearchResult> {
        @Override
        public void onResponse(Call<ReposSearchResult> call, Response<ReposSearchResult> response) {
            ReposSearchResult reposSearchResult = response.body();
            List<Repo> newRepos = reposSearchResult.getRepos();
            mRepoAdapter.setRepos(newRepos);
            mRealm.executeTransaction(realm -> realm.insertOrUpdate(newRepos));
        }

        @Override
        public void onFailure(Call<ReposSearchResult> call, Throwable t) {
            Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
            call.cancel();
        }
    }
}

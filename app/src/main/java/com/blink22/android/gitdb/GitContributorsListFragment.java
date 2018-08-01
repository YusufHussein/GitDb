package com.blink22.android.gitdb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitContributorsListFragment extends Fragment {
    private static final String CONTRIBUTORS_URL = "CONTRIBUTORS_URL";
    private static final int NUMBER_OF_AVATARS_PER_LINE = 4;
    @BindView(R.id.contributors_recycler_view)
    RecyclerView mContributorsRecyclerView;
    private Call<List<GitHubUser>> mGetContributorsCall;

    public static GitContributorsListFragment newInstance(String contributorsUrl) {
        Bundle args = new Bundle();
        args.putString(CONTRIBUTORS_URL, contributorsUrl);
        GitContributorsListFragment fragment = new GitContributorsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_git_contributors_list, container, false);
        ButterKnife.bind(this, view);
        mContributorsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), NUMBER_OF_AVATARS_PER_LINE));
        String contributorsUrl = getArguments().getString(CONTRIBUTORS_URL);
        fetchContributors(contributorsUrl);
        return view;
    }

    private void fetchContributors(String contributorsUrl) {
        GitHubService gitHubService = GitHubApiClient.getClient().create(GitHubService.class);
        mGetContributorsCall = gitHubService.getContributors(contributorsUrl);
        mGetContributorsCall.enqueue(new GetContributorsCallbacks());
    }

    @Override
    public void onDestroyView() {
        if (mGetContributorsCall != null && mGetContributorsCall.isExecuted())
            mGetContributorsCall.cancel();
        super.onDestroyView();
    }

    private class GetContributorsCallbacks implements Callback<List<GitHubUser>> {
        @Override
        public void onResponse(Call<List<GitHubUser>> call, Response<List<GitHubUser>> response) {
            List<GitHubUser> gitHubUsers = response.body();
            GitHubUserAdapter gitHubUserAdapter = new GitHubUserAdapter(gitHubUsers);
            mContributorsRecyclerView.setAdapter(gitHubUserAdapter);
        }

        @Override
        public void onFailure(Call<List<GitHubUser>> call, Throwable t) {
            Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
            call.cancel();
        }
    }
}

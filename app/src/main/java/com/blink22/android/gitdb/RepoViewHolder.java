package com.blink22.android.gitdb;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepoViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.repo_desc_text_view)
    TextView mDescriptionTextView;
    @BindView(R.id.repo_name_text_view)
    TextView mNameTextView;
    @BindView(R.id.repo_language_text_view)
    TextView mLanguageTextView;
    @BindView(R.id.repo_stars_text_view)
    TextView mStarsTextView;
    @BindView(R.id.repo_forks_text_view)
    TextView mForksTextView;
    private Repo mRepo;

    public RepoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.repo_item_container)
    public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = GitContributorsListActivity.newIntent(view.getContext(),mRepo.getContributorsUrl());
        context.startActivity(intent);
    }

    public void bindData(final Repo repo) {
        mRepo = repo;
        mDescriptionTextView.setText(mRepo.getDescription());
        mNameTextView.setText(mRepo.getFullName());
        if (mRepo.getLanguage() != null && mRepo.getLanguage().length() > 0) {
            mLanguageTextView.setText(mRepo.getLanguage());
            mLanguageTextView.setVisibility(View.VISIBLE);
        } else mLanguageTextView.setVisibility(View.GONE);
        mStarsTextView.setText(mRepo.getStargazersCount().toString());
        mForksTextView.setText(mRepo.getForksCount().toString());
    }
}

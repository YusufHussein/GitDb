package com.blink22.android.gitdb;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    public RepoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final Repo repo) {
        mDescriptionTextView.setText(repo.getDescription());
        mNameTextView.setText(repo.getFullName());
        mLanguageTextView.setText(repo.getLanguage());
        mStarsTextView.setText(repo.getStargazersCount().toString());
        mForksTextView.setText(repo.getForksCount().toString());
    }
}

package com.blink22.android.gitdb;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitHubUserViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.user_avatar_imageView)
    ImageView mUserAvatarImageView;
    @BindView(R.id.user_name_text_view)
    TextView mNameTextView;
    @BindView(R.id.user_contributions_count_text_view)
    TextView mContributionsTextView;

    public GitHubUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final GitHubUser gitHubUser) {
        Picasso.get()
                .load(gitHubUser.getAvatarUrl())
                .transform(new RoundedCornersTransform())
                .into(mUserAvatarImageView);
        mNameTextView.setText(gitHubUser.getName());
        mContributionsTextView.setText(mContributionsTextView.getContext()
                .getString(R.string.contributions_format, gitHubUser.getContributionsCount()));
    }
}

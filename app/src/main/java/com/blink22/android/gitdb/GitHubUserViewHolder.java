package com.blink22.android.gitdb;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GitHubUserViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.user_avatar_imageView)
    ImageView mUserAvatarImageView;

    public GitHubUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final GitHubUser gitHubUser) {
        Picasso.get()
                .load(gitHubUser.getAvatarUrl())
                .resize(50, 50)
                .centerCrop()
                .into(mUserAvatarImageView);
    }
}

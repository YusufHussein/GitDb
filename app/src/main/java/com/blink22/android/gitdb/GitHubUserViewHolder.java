package com.blink22.android.gitdb;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GitHubUserViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.user_avatar_imageView)
    ImageView mUserAvatarImageView;
    @BindView(R.id.user_name_text_view)
    TextView mNameTextView;
    @BindView(R.id.user_contributions_count_text_view)
    TextView mContributionsTextView;
    private GitHubUser mUser;

    public GitHubUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final GitHubUser user) {
        mUser = user;
        Picasso.get()
                .load(mUser.getAvatarUrl())
                .placeholder(R.drawable.user_placeholder)
                .transform(new RoundedCornersTransform())
                .into(mUserAvatarImageView);
        mNameTextView.setText(mUser.getName());
        Resources res = mContributionsTextView.getContext().getResources();
        String quantityString = res.getQuantityString(R.plurals.contributions_plural_format
                , mUser.getContributionsCount()
                , mUser.getContributionsCount());
        mContributionsTextView.setText(quantityString);
    }

    @OnClick(R.id.user_item_container)
    public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(mUser.getProfileUrl()));
        context.startActivity(intent);
    }
}

package com.blink22.android.gitdb;

import com.google.gson.annotations.SerializedName;

public class GitHubUser {
    @SerializedName("login")
    private String mName;
    @SerializedName("id")
    private Integer mId;
    @SerializedName("avatar_url")
    private String mAvatarUrl;
    @SerializedName("html_url")
    private String mProfileUrl;
    @SerializedName("contributions")
    private Integer mContributionsCount;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getProfileUrl() {
        return mProfileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        mProfileUrl = profileUrl;
    }

    public Integer getContributionsCount() {
        return mContributionsCount;
    }

    public void setContributionsCount(Integer contributionsCount) {
        mContributionsCount = contributionsCount;
    }
}

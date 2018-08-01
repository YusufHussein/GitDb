package com.blink22.android.gitdb;

import com.google.gson.annotations.SerializedName;

public class Repo {
    @SerializedName("id")
    private Integer mId;
    @SerializedName("full_name")
    private String mFullName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("contributors_url")
    private String mContributorsUrl;
    @SerializedName("stargazers_count")
    private Integer mStargazersCount;
    @SerializedName("language")
    private String mLanguage;
    @SerializedName("forks_count")
    private Integer mForksCount;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getContributorsUrl() {
        return mContributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        mContributorsUrl = contributorsUrl;
    }

    public Integer getStargazersCount() {
        return mStargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        mStargazersCount = stargazersCount;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public Integer getForksCount() {
        return mForksCount;
    }

    public void setForksCount(Integer forksCount) {
        mForksCount = forksCount;
    }
}

package com.blink22.android.gitdb;

import com.google.gson.annotations.SerializedName;

public class Repo {
    @SerializedName("id")
    private Integer id;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("description")
    private String description;
    @SerializedName("contributors_url")
    private String contributorsUrl;
    @SerializedName("stargazers_count")
    private Integer stargazersCount;
    @SerializedName("language")
    private String language;
    @SerializedName("forks_count")
    private Integer forksCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }
}

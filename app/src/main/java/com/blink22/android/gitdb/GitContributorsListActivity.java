package com.blink22.android.gitdb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

public class GitContributorsListActivity extends AppCompatActivity {
    @InjectExtra
    String contributorsUrl;
    @InjectExtra
    String repoName;

    public static Intent newIntent(Context context, String repoName, String contributorsUrl) {
        Intent intent = Henson.with(context)
                .gotoGitContributorsListActivity()
                .contributorsUrl(contributorsUrl)
                .repoName(repoName)
                .build();
        return intent;
    }

    protected Fragment createFragment() {
        return GitContributorsListFragment.newInstance(contributorsUrl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dart.inject(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(repoName);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}

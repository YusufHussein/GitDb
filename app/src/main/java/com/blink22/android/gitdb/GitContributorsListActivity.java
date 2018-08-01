package com.blink22.android.gitdb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

public class GitContributorsListActivity extends AppCompatActivity {
    @InjectExtra
    String contributorsUrl;

    public static Intent newIntent(Context context, String contributorsUrl) {
        Intent intent = Henson.with(context).gotoGitContributorsListActivity().contributorsUrl(contributorsUrl).build();
        return intent;
    }

    protected Fragment createFragment() {

        return GitContributorsListFragment.newInstance(contributorsUrl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dart.inject(this);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}

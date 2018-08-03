package com.blink22.android.gitdb;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.realm.Realm;

public class GitReposListSearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return GitReposListSearchFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
    }
}

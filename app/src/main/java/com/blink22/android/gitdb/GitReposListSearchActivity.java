package com.blink22.android.gitdb;

import android.support.v4.app.Fragment;

public class GitReposListSearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return GitReposListSearchFragment.newInstance();
    }
}

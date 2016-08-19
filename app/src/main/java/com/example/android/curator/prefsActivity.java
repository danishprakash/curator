package com.example.android.curator;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Danish on 8/1/2016.
 */
public class prefsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new prefs())
                .commit();
    }
}

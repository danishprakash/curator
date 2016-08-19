package com.example.android.curator;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

/**
 * Created by Danish on 8/1/2016.
 */
public class prefs extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.prefs);

        SwitchPreference pref = (SwitchPreference) findPreference("dark_switch");
        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                getActivity().getApplicationContext().setTheme(R.style.CuratorDark);
                return true;
            }
        });

    }

}

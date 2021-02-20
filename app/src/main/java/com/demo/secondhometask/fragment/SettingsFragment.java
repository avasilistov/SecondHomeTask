package com.demo.secondhometask.fragment;

import android.os.Bundle;
import 	androidx.preference.PreferenceFragmentCompat;

import com.demo.secondhometask.R;

public  class SettingsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}
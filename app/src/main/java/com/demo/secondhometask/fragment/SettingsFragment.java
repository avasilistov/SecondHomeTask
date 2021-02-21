package com.demo.secondhometask.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import 	androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import com.demo.secondhometask.R;

public  class SettingsFragment extends PreferenceFragmentCompat  {
    EditTextPreference text_preference;
    ListPreference list_preference;
    SwitchPreferenceCompat sync_preference;
    SwitchPreferenceCompat attach_preference;
    SharedPreferences sp;


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        text_preference = findPreference("signature");
        list_preference = findPreference("reply");
        sync_preference = findPreference("sync");
        attach_preference = findPreference("attachment");
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
    }








}
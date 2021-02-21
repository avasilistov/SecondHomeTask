package com.demo.secondhometask.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import 	androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import com.demo.secondhometask.R;
import com.google.android.material.snackbar.Snackbar;


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
        sync_preference = findPreference("sync");
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        final Boolean[] result = {true};
        final String[] start_text = new String[1];
        final Boolean[] start_value = {true};

        // Сохраняем начальное значение свойсва
        text_preference.setOnPreferenceClickListener(preference -> {
            start_text[0] = sp.getString("signature", "");
            return false;
        });

        // Если свойство изменилось и пользователь нажал на SnackBar UNDO, изменения не сохряняются
        text_preference.setOnPreferenceChangeListener((preference, newValue) -> {
            String saved_text = sp.getString("signature", "");
            result[0] = true;
    // Сравниваем начальное знаение свойства и новое, если отличаются запускаем SnackBar, с возможностью отмены изменений
            if (!newValue.equals(start_text[0])){
                Snackbar snackbar = Snackbar.make(getView(),"Signature is changed", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Snackbar snackbar1 = Snackbar.make(getView(), "Signature is restored", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                                // Присваиваем result false для отмены сохранения изменений если нажали UNDO
                                result[0] = false;
                                if (!result[0]){
                                    text_preference.setText(start_text[0]);
                                    Log.i("xxx", text_preference.getText().toString());
                                }
                            }
                        });
                snackbar.show();
            }
            return result[0];
        });


        sync_preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                start_value[0] = sp.getBoolean("sync", true);
                return false;
            }
        });

        sync_preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                result[0] = true;
                if (start_value[0] != newValue){
                    Snackbar snackbar = Snackbar.make(getView(),"Sync Setting is changed", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    Snackbar snackbar1 = Snackbar.make(getView(), "Sync Setting is restored", Snackbar.LENGTH_SHORT);
                                    snackbar1.show();
                                    // Присваиваем result false для отмены сохранения изменений если нажали UNDO
                                    result[0] = false;
                                    if (!result[0]){
                                        sync_preference.setChecked(!start_value[0]);
                                    }
                                }
                            });
                    snackbar.show();
                }

                return result[0];
            }
        });

    }








}
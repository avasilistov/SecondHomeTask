package com.demo.secondhometask.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;
import com.demo.secondhometask.R;
import com.google.android.material.snackbar.Snackbar;


public class SettingsFragment extends PreferenceFragmentCompat {
    private EditTextPreference text_preference;
    private SwitchPreferenceCompat sync_preference;
    private SharedPreferences sp;
    private String sync_changed = "Sync Setting is changed";
    private String sync_restored = "Sync Setting is restored";
    private String sign_changed = "Signature is changed";
    private String sign_restored = "Signature is restored";
    private String sync = "sync";
    private String signature = "signature";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        text_preference = findPreference(signature);
        sync_preference = findPreference(sync);
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        final Boolean[] result = {true};
        final String[] start_text = new String[1];
        final Boolean[] start_value = {false};

        // Сохраняем начальное значение свойсва
        text_preference.setOnPreferenceClickListener(preference -> {
            start_text[0] = sp.getString(signature, "");
            return false;
        });

        // Если свойство "Your signature" изменилось и нажали на SnackBar UNDO, изменения не сохряняются
        text_preference.setOnPreferenceChangeListener((preference, newValue) -> {
            String saved_text = sp.getString(signature, "");
            result[0] = true;

            // Сравниваем начальное знаение свойства и новое, если отличаются запускаем SnackBar, с возможностью отмены изменений
            if (!newValue.equals(start_text[0])) {
                Snackbar snackbar = Snackbar.make(getView(), sign_changed, Snackbar.LENGTH_LONG)
                        .setAction("UNDO", v -> {
                            Snackbar snackbar1 = Snackbar.make(getView(), sign_restored, Snackbar.LENGTH_SHORT);
                            snackbar1.show();

                            // Присваиваем result false для отмены сохранения изменений если нажали UNDO
                            result[0] = false;
                            if (!result[0]) {
                                text_preference.setText(start_text[0]);
                            }
                        });
                snackbar.show();
            }
            return result[0];
        });

        // Если свойство "Sync email periodically" изменилось и было нажато на SnackBar UNDO, изменения не сохряняются
        sync_preference.setOnPreferenceClickListener(preference -> {
            start_value[0] = sp.getBoolean(sync, true);
            return false;
        });

        sync_preference.setOnPreferenceChangeListener((preference, newValue) -> {
            result[0] = true;
            if (start_value[0] != newValue) {

                Snackbar snackbar = Snackbar.make(getView(), sync_changed, Snackbar.LENGTH_LONG)
                        .setAction("UNDO", v -> {

                            Snackbar snackbar1 = Snackbar.make(getView(), sync_restored, Snackbar.LENGTH_SHORT);
                            snackbar1.show();

                            // Присваиваем result false для отмены сохранения изменений если нажали UNDO
                            result[0] = false;
                            if (!result[0]) {
                                sync_preference.setChecked(!start_value[0]);
                            }
                        });
                snackbar.show();
            }

            return result[0];
        });

    }


}
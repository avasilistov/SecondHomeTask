package com.demo.secondhometask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demo.secondhometask.fragment.AboutFragment;
import com.demo.secondhometask.fragment.HomeFragment;
import com.demo.secondhometask.fragment.HostFragment;
import com.demo.secondhometask.fragment.ProfileFragment;

import static com.demo.secondhometask.fragment.HostFragment.TAG_FRAGMENT_HOME;
import static com.demo.secondhometask.fragment.HostFragment.TAG_FRAGMENT_HOST;
import static com.demo.secondhometask.fragment.HostFragment.TAG_FRAGMENT_PROFILE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnHome;
    private Button btnProfile;
    public static final String RESULT = "result";


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main_activity_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_host, new HomeFragment())
                        .setReorderingAllowed(true).addToBackStack(TAG_FRAGMENT_HOME).commit();

                break;
            case R.id.button_main_activity_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_host, new ProfileFragment())
                        .setReorderingAllowed(true).addToBackStack(TAG_FRAGMENT_PROFILE).commit();

                break;
        }
    }

    public interface FragmentCallback {
        void passData(String data, String tag, Fragment fragment);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Устанавливаем меню
        Toolbar toolbar_menu = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar_menu);
        btnHome = findViewById(R.id.button_main_activity_home);
        btnProfile = findViewById(R.id.button_main_activity_profile);
        btnHome.setOnClickListener(this);
        btnProfile.setOnClickListener(this);

        // Добавляем начальный фрагмент, который будет host для остальных
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).addToBackStack(TAG_FRAGMENT_HOST)
                .add(R.id.frame_layout_main_activity, new HostFragment(), TAG_FRAGMENT_HOST).commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about_menu:
                onClickAbout();
                return true;
            case R.id.settings_menu:
                onClickSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Вызывается при нажатии на Settings в меню
    private void onClickSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, 1);
    }

    // Используем onActivityResult для получения данных из SettingsActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String message = data.getStringExtra(RESULT);
        showToast(message);
    }

    // Вызывается при нажатии на About в меню
    private void onClickAbout() {
        new AboutFragment().show(getSupportFragmentManager(), null);
    }

    // выводит Toast при нажатии на кнопку Send в меню About
    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

}
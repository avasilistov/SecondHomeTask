package com.demo.secondhometask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.demo.secondhometask.fragment.AboutFragment;
import com.demo.secondhometask.fragment.HomeFragment;
import com.demo.secondhometask.fragment.HostFragment;
import com.demo.secondhometask.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String MSG = "Message";
    private Button button_main_activity_home;
    private Button button_main_activity_profile;
    public static String home_tag = "home";
    public static String host_tag = "host";
    public static String profile_tag = "profile";


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main_activity_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_host, new HomeFragment())
                        .setReorderingAllowed(true).addToBackStack(home_tag).commit();
                break;
            case R.id.button_main_activity_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_host, new ProfileFragment())
                        .setReorderingAllowed(true).addToBackStack(profile_tag).commit();
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
        // Работаем с меню (Toolbar)
        Toolbar toolbar_menu = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar_menu);

        // Работаем с фрагментами
        button_main_activity_home = findViewById(R.id.button_main_activity_home);
        button_main_activity_profile = findViewById(R.id.button_main_activity_profile);
        button_main_activity_home.setOnClickListener(this);
        button_main_activity_profile.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).addToBackStack(host_tag)
                .add(R.id.frame_layout_main_activity, new HostFragment(), host_tag).commit();

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

    private void onClickSettings() {

    }

    private void onClickAbout() {
        new AboutFragment().showNow(getSupportFragmentManager(), null);
    }

    // выводит Toast при нажатии на кнопку Send в меню About
    public void showToast(String data){
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

}
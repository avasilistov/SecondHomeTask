package com.demo.secondhometask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    public static final String MSG = "Message";
    FrameLayout frameLayout;
    Button button_main_activity_home;
    Button button_main_activity_profile;
    public static String home_tag = "home";
    public static String profile_tag = "profile";

    public class Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //Находим tag верхнего элемента backStack

            String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager()
                    .getBackStackEntryCount() - 1).getName();

            switch (v.getId()) {
                case R.id.button_main_activity_home:
                    if (tag != home_tag) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_host, new HomeFragment())
                                .setReorderingAllowed(true).addToBackStack(home_tag).commit();
                    }
                    break;

                case R.id.button_main_activity_profile:

                    if (tag != profile_tag) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_host, new ProfileFragment())
                                .setReorderingAllowed(true).addToBackStack(profile_tag).commit();
                    }
                    break;
            }
        }
    }


    public interface FragmentCallback {
        void passData(String data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("XXX", "onCreate main activity ");
        setContentView(R.layout.activity_main);
        button_main_activity_home = findViewById(R.id.button_main_activity_home);
        button_main_activity_profile = findViewById(R.id.button_main_activity_profile);
        frameLayout = findViewById(R.id.frame_layout_main_activity);
        Listener listener = new Listener();
        button_main_activity_home.setOnClickListener(listener);
        button_main_activity_profile.setOnClickListener(listener);
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.frame_layout_main_activity, new HostFragment(), "host").commit();
    }


}
package com.demo.secondhometask.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.secondhometask.MainActivity;
import com.demo.secondhometask.R;


public class HostFragment extends Fragment implements MainActivity.FragmentCallback {
    public static final String TAG_FRAGMENT_HOME = "home";
    public static final String TAG_BUNDLE_MSG = "MSG";
    public static final String TAG_FRAGMENT_HOST = "host";
    public static final String TAG_FRAGMENT_PROFILE = "pofile";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = new Bundle();
        // Добавляем стартовый фрагмент во fragment_host
        String start = getResources().getString(R.string.start);
        bundle.putString(TAG_BUNDLE_MSG, start);
        getChildFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_host, HomeFragment.class, bundle).addToBackStack(TAG_FRAGMENT_HOME).commit();
        return inflater.inflate(R.layout.fragment_host, container, false);
    }

    @Override
    public void passData(String data, String tag, Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG_BUNDLE_MSG, data);
        getChildFragmentManager().beginTransaction().setReorderingAllowed(true)
                .addToBackStack(tag).replace(R.id.fragment_host, fragment.getClass(), bundle).commit();
        getChildFragmentManager().findFragmentById(R.id.fragment_host);

    }
}
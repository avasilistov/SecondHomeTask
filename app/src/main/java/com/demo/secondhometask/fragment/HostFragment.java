package com.demo.secondhometask.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.demo.secondhometask.MainActivity;
import com.demo.secondhometask.R;


public class HostFragment extends Fragment implements MainActivity.FragmentCallback {
    public static final String TAG_FRAGMENT_HOME = HomeFragment.class.getSimpleName();
    public static final String TAG_BUNDLE_MSG = "MSG";
    public static final String TAG_FRAGMENT_HOST = HostFragment.class.getSimpleName();
    public static final String TAG_FRAGMENT_DEFAULT = "default";
    public static final String TAG_FRAGMENT_PROFILE = ProfileFragment.class.getSimpleName();
    private boolean start_flag = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_host, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get current fragmet from SharedPreferences
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String currTag = sharedPref.getString(getString(R.string.current_fragment), TAG_FRAGMENT_DEFAULT);
        addChild(currTag, null);
        start_flag = false;
    }

    @Override
    public void passData(String message, String tag) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG_BUNDLE_MSG, message);
        addChild(tag, bundle);

    }

    public void addChild(String tag, Bundle bundle) {

        if (!start_flag) { // It's a start the app?
            String currTag = getChildFragmentManager().findFragmentById(R.id.fragment_host).getClass().getSimpleName();
            if (!currTag.equals(tag)) {

                if (tag.equals(TAG_FRAGMENT_HOME)) {
                    getChildFragmentManager().beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragment_host, HomeFragment.class, bundle).addToBackStack(TAG_FRAGMENT_HOME).commit();
                } else if (tag.equals(TAG_FRAGMENT_PROFILE)) {
                    getChildFragmentManager().beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragment_host, ProfileFragment.class, bundle).addToBackStack(TAG_FRAGMENT_PROFILE).commit();
                }
            }
        } else {
            if (tag.equals(TAG_FRAGMENT_HOME)) {
                getChildFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .add(R.id.fragment_host, HomeFragment.class, bundle).addToBackStack(TAG_FRAGMENT_HOME).commit();
            } else if (tag.equals(TAG_FRAGMENT_PROFILE)) {
                getChildFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .add(R.id.fragment_host, ProfileFragment.class, bundle).addToBackStack(TAG_FRAGMENT_PROFILE).commit();
            }
        }


    }

    @Override
    public void onStop() {

        // Set current fragment like last fragment
        String currTag = getChildFragmentManager().findFragmentById(R.id.fragment_host).getClass().getSimpleName();
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.current_fragment), currTag);
        editor.apply();

        super.onStop();
    }
}
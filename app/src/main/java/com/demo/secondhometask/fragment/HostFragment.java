package com.demo.secondhometask.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.demo.secondhometask.MainActivity;
import com.demo.secondhometask.R;


public class HostFragment extends Fragment implements MainActivity.FragmentCallback {
    private String home_tag = "home";
    private String bundle_tag = "MSG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = new Bundle();
        // Добавляем стартовый фрагмент во fragment_host
        bundle.putString(bundle_tag, "Start");
        getChildFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_host, HomeFragment.class, bundle).addToBackStack(home_tag).commit();
        return inflater.inflate(R.layout.fragment_host, container, false);
    }

    @Override
    public void passData(String data, String tag, Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString(bundle_tag, data);
        getChildFragmentManager().beginTransaction().setReorderingAllowed(true)
                .addToBackStack(tag).replace(R.id.fragment_host, fragment.getClass(), bundle).commit();
        getChildFragmentManager().findFragmentById(R.id.fragment_host);

    }
}
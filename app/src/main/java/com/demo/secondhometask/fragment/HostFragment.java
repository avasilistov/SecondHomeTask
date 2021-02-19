package com.demo.secondhometask.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.secondhometask.R;


public class HostFragment extends Fragment {
    String home_tag = "home";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Добавляем стартовый фрагмент во fragment_host
        getParentFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_host, HomeFragment.class, null).addToBackStack(home_tag).commit();
        return inflater.inflate(R.layout.fragment_host, container, false);
    }

}
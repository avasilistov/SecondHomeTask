package com.demo.secondhometask;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HostFragment extends Fragment {
    String home_tag = "home";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Добавляем стартовый фрагмент во fragment_host
        getActivity().getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_host, new HomeFragment()).addToBackStack(home_tag).commit();
        return inflater.inflate(R.layout.fragment_host, container, false);
    }

}
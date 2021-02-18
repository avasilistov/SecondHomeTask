package com.demo.secondhometask;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HostFragment extends Fragment {
    private View view;
    private String tag = "fragment_home";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("XXX", "onCreate host fragment ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_host, container, false);
//        Log.i("XXX", "onCreateView host fragment before getChild... ");
//        getChildFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragment_host, new HomeFragment())
//                .addToBackStack(null).commit();
//
//        Log.i("XXX", "onCreateView host fragment after getChild... ");

        return view;
    }

}
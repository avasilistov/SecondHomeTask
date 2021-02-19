package com.demo.secondhometask.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.secondhometask.MainActivity;
import com.demo.secondhometask.R;

import static com.demo.secondhometask.MainActivity.MSG;
import static com.demo.secondhometask.MainActivity.profile_tag;


public class HomeFragment extends Fragment implements MainActivity.FragmentCallback {
    private TextView textView_message;
    private EditText editText_text;
    private Bundle bundle;
    MainActivity.FragmentCallback callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            bundle = new Bundle();
        }else {
            bundle = savedInstanceState;
            textView_message = getActivity().findViewById(R.id.textView_fragment_home_text);
            textView_message.setText(requireArguments().getString(MSG));
        }
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        textView_message = getActivity().findViewById(R.id.textView_fragment_home_text);
        if (savedInstanceState != null) {
            textView_message.setText(savedInstanceState.getString(MSG));
        }
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //получаем ссылки на поле ввода и поле для отображения текста
        editText_text = getActivity().findViewById(R.id.editText_fragment_home_text);


        //находим кнопку, вешаем на нее обработчик который проверяет на принадлежность к FragmentCallback и вызывает callback

        getActivity().findViewById(R.id.button_fragment_home_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_text.length() > 0) {
                    bundle.putString(MSG, editText_text.getText().toString());
                    getParentFragmentManager().beginTransaction().replace(R.id.fragment_host, ProfileFragment.class, bundle)
                            .setReorderingAllowed(true).addToBackStack(profile_tag).commit();
                } else Toast.makeText(getContext(), "Enter a message", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MSG, editText_text.getText().toString());
    }

    @Override
    public void passData(String data) {
        textView_message.setText(data);
    }
}
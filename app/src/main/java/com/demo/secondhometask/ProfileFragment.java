package com.demo.secondhometask;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import static com.demo.secondhometask.MainActivity.MSG;
import static com.demo.secondhometask.MainActivity.home_tag;
import static com.demo.secondhometask.MainActivity.profile_tag;


public class ProfileFragment extends Fragment implements MainActivity.FragmentCallback {
    private TextView textView_message;
    private EditText editText_text;
    MainActivity.FragmentCallback callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Устанавливаем текст отправленный предидущим фрагментом
//        if (getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_host) instanceof MainActivity.FragmentCallback && savedInstanceState != null) {
//            callback = (MainActivity.FragmentCallback) getActivity().getSupportFragmentManager()
//                    .findFragmentById(R.id.fragment_host);
//            if (callback != null)
//                callback.passData(savedInstanceState.getString(MSG));
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        textView_message = getActivity().findViewById(R.id.textView_fragment_profile_text);
//        if (savedInstanceState!=null) {
//            textView_message.setText(savedInstanceState.getString(MSG));
//        }
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //получаем ссылки на поле ввода и поле для отображения текста
        editText_text = getActivity().findViewById(R.id.editText_fragment_profile_text);



        //находим кнопку, вешаем на нее обработчик который проверяет на принадлежность к FragmentCallback и вызывает callback
        getActivity().findViewById(R.id.button_fragment_home_send).setOnClickListener(t -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_host, new HomeFragment())
                    .setReorderingAllowed(true).addToBackStack(profile_tag).commit();

        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(MSG, editText_text.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void passData(String data) {
        textView_message.setText(data);
    }
}
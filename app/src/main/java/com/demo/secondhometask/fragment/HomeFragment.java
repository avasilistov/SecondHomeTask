package com.demo.secondhometask.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.demo.secondhometask.MainActivity;
import com.demo.secondhometask.R;
import static com.demo.secondhometask.fragment.HostFragment.TAG_BUNDLE_MSG;
import static com.demo.secondhometask.fragment.HostFragment.TAG_FRAGMENT_HOST;
import static com.demo.secondhometask.fragment.HostFragment.TAG_FRAGMENT_PROFILE;


public class HomeFragment extends Fragment  {
    private TextView text_message;
    private EditText edit_message;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        String toast_text = getResources().getString(R.string.toast_text);
        String message = "";
        if (this.getArguments()!=null) {
            message = requireArguments().getString(TAG_BUNDLE_MSG);
        }
        text_message = view.findViewById(R.id.textView_fragment_home_text);
        edit_message = view.findViewById(R.id.editText_fragment_home_text);
        text_message.setText(message);
        view.findViewById(R.id.button_fragment_home_send).setOnClickListener(v -> {
            if (edit_message.length() > 0) {
                Fragment fragment = new ProfileFragment();
                MainActivity.FragmentCallback parent = (MainActivity.FragmentCallback) getActivity()
                        .getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_HOST);
                parent.passData(edit_message.getText().toString(), TAG_FRAGMENT_PROFILE);
            } else Toast.makeText(getContext(), toast_text, Toast.LENGTH_SHORT).show();

        });
        return view;
    }


}
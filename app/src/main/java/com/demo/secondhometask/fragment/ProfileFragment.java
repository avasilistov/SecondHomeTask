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
import static com.demo.secondhometask.MainActivity.home_tag;


public class ProfileFragment extends Fragment {
    private TextView text_message;
    private EditText edit_message;
    private String bundle_tag = "MSG";
    private String host_tag = "host";
    private String toast_text = "Enter a message";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        String message = "";


        if (this.getArguments() != null) {
            message = requireArguments().getString(bundle_tag);
        }

        text_message = view.findViewById(R.id.textView_fragment_profile_text);
        edit_message = view.findViewById(R.id.editText_fragment_profile_text);
        text_message.setText(message);
        view.findViewById(R.id.button_fragment_profile_send).setOnClickListener(v -> {
            if (edit_message.length() > 0) {
                Fragment fragment = new HomeFragment();
                MainActivity.FragmentCallback parent = (MainActivity.FragmentCallback) getActivity()
                        .getSupportFragmentManager().findFragmentByTag(host_tag);

                parent.passData(edit_message.getText().toString(), home_tag, fragment);
            } else Toast.makeText(getContext(), toast_text, Toast.LENGTH_SHORT).show();
        });
        return view;
    }


}
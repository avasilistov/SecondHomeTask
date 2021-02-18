package com.demo.secondhometask;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.demo.secondhometask.MainActivity.MSG;


public class HomeFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    private TextView textView_message;
    private Button button_send;
    private EditText editText_text;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
//        textView_message = view.findViewById(R.id.textView_fragment_home_text);
//        button_send = view.findViewById(R.id.button_fragment_home_send);
//        editText_text = view.findViewById(R.id.editText_fragment_home_text);
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            String msg = bundle.getString(MSG);
//            if (msg != null) {
//                textView_message.setText(msg);
//            } else {
//                textView_message.setText("This Fragment added first");
//            }
//        }
//        button_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (editText_text.length() > 0) {
//                    getChildFragmentManager().beginTransaction().setReorderingAllowed(true)
//                            .replace(R.id.fragment_host, new ProfileFragment(), TAG).addToBackStack(TAG).commit();
//                } else {
//                    Toast.makeText(view.getContext(), "Enter text", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(MSG, editText_text.getText().toString());
        super.onSaveInstanceState(outState);
    }

}
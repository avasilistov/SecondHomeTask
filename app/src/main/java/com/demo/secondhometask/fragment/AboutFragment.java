package com.demo.secondhometask.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.demo.secondhometask.MainActivity;
import com.demo.secondhometask.R;


public class AboutFragment extends DialogFragment  {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setTitle(R.string.created_by_oleksandr_vasilistov).setView(inflater
                .inflate(R.layout.fragment_about, null))
                .setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText message = (EditText) AboutFragment.this.getDialog().findViewById(R.id.editText_dialog_message);
                String text = message.getText().toString();
                MainActivity activity = (MainActivity) getActivity();
                activity.showToast(text);

            }
        });

        return builder.show();


    }
}
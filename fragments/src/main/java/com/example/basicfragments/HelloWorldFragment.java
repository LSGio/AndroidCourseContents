package com.example.basicfragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HelloWorldFragment extends Fragment {

    private static final String TAG = "@@HelloWorldFragment";

    private TextView mTextView;
    private EditText mEditText;
    private Button mChangeTextButton;
    private OnSelectedItemListener mOnSelectedItemListener;

    public HelloWorldFragment() { }

    public interface OnSelectedItemListener {
        void onUpdateSelected(final String message);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: called");

        if (context instanceof OnSelectedItemListener) {
            mOnSelectedItemListener = (OnSelectedItemListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement HelloWorldFragment.OnSelectedItemListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called");
        return inflater.inflate(R.layout.fragment_hello, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: called");

        mTextView = view.findViewById(R.id.hello_world_textview);
        mEditText = view.findViewById(R.id.edittext);
        mChangeTextButton = view.findViewById(R.id.change_text_button);
        mChangeTextButton.setOnClickListener(v -> updatedChangeText());
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: called");

        mOnSelectedItemListener = null;
        super.onDetach();
    }

    private void updatedChangeText() {
        Log.d(TAG, "updatedChangeText: called");

        String currntTime = String.valueOf(System.currentTimeMillis());
        mOnSelectedItemListener.onUpdateSelected(currntTime + mEditText.getText().toString());
    }

    public void changeText(final String message) {
        mTextView.setText(message);
    }


}

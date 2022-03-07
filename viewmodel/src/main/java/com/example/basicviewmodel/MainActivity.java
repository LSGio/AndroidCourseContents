package com.example.basicviewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.basicviewmodel.model.CounterViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO : add shared logging library to be used by all modules

    private static final String TAG = "@@MainActivity";
    private Button mIncrementCounterButton;
    private TextView mCounterValueTextView;
    private CounterViewModel mCounterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: called");

        mIncrementCounterButton = findViewById(R.id.increment_counter_button);
        mCounterValueTextView = findViewById(R.id.counter_value_textview);
        mIncrementCounterButton.setOnClickListener(this);
        mCounterViewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        mCounterValueTextView.setText(String.valueOf(mCounterViewModel.getCounter()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: called");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called");
        mIncrementCounterButton.setOnClickListener(null);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: called");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: called");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.increment_counter_button:
                mCounterViewModel.setCounter(mCounterViewModel.getCounter() + 1);
                mCounterValueTextView.setText(String.valueOf(mCounterViewModel.getCounter()));
                break;
            default:
                Log.d(TAG, "onClick: case not implemented yet!");
        }
    }
}